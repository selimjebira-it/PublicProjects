package tictactoeServer.view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoeServer.model.server.Server;

/**
 *
 * @author selim
 */
public class ServerView implements Observer {

    private final Server server;

    public ServerView(Server server) throws IOException, ClassNotFoundException {
        this.server = server;
    }

    private String readInput() {

        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public void start() {

        try {
            server.startService();
        } catch (IOException ex) {
            Logger.getLogger(ServerView.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        String msg= "";
        while (!msg.equals("close")) {
            System.out.println("type Close to stop the service");
            msg = readInput();
        }
       server.stopService();

    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof Server && (Server) o == this.server) {

            System.out.println((String) arg);
        }
    }

}
