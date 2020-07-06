package tictactoeClient.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Data;

/**
 *
 * @author selim
 */
public class ClientNio extends Observable implements Runnable {

    private final Socket sockeToServer;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private final ExecutorService service;
    private final AtomicBoolean running;

    /**
     * constructor of a client
     *
     * @param host the adresse of the server
     * @param port the port of the server
     * @throws IOException
     */
    public ClientNio(String host, int port) throws IOException {

        sockeToServer = new Socket(host, port);
        out = new ObjectOutputStream(new BufferedOutputStream(sockeToServer.getOutputStream()));
        out.flush();
        in = new ObjectInputStream(new BufferedInputStream(sockeToServer.getInputStream()));
        service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        running = new AtomicBoolean(false);

    }

    /**
     * method to stop the service
     *
     */
    public void stopService() {

        running.set(false);

    }

    /**
     * mehtod to send data throug the socket
     *
     * @param data the data to send
     * @throws IOException
     */
    public void sendObject(Data data) throws IOException, ClassNotFoundException {
        out.writeObject(data);
        out.flush();

    }

    @Override
    public void run() {

        running.set(true);
        service.submit(() -> {

            try {
                while (running.get()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ClientNio.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }

                }
                //STOP SERVICE
                service.shutdown();
                in.close();
                out.close();
                sockeToServer.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientNio.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.submit(() -> {
            while (running.get()) {
                try {
                    final Object o = in.readObject(); //bloquant
                    if (o instanceof Data) {
                        service.submit(() -> {
                            setChanged();
                            notifyObservers((Data) o);
                        });
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ClientNio.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }
        });

    }

}
