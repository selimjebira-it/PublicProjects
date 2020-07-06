package tictactoeServer.model.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import model.GameInfo;
import model.Profile;
import model.RecapInfo;
import tictactoeServer.model.db.DataBase;
import tictactoeServer.model.game.Game;

/**
 *
 * @author selim
 */
public class Server extends Observable {

    private static final int PORT = 12345;

    private static final String SERVER_NAME = "server Service";
    private static final String SERVER_START = "server is up";
    private static final String SERVER_STOP = "server is down";
    private static final String NEW_CONNECTION = "New Client:";

    private static final String ERROR = "an error as occur during the listening phase : ";
    private static final String ERROR_PROC = "Process already in the list ";
    private static final String ERROR_SERVER_DOWN = "server is not listening ";
    private static final String ERROR_SOCKET = "SOCKET EXCEPTION";
    private static final String LOGIN = "LOGIN OF :";
    private static final String DISCONNECTION = "DISCONNECTION OF :";
    private static final String REGISTRATION = "REGISTRATION OF :";

    private final List<ClientProcess> process;
    private final AtomicBoolean listening;
    private final DataBase database;
    private final ExecutorService service;

    private List<RecapInfo> recaps;

    public Server() {

        database = new DataBase();
        process = new LinkedList<>();
        listening = new AtomicBoolean();
        service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    }

    public void startService() throws IOException {

        listening.set(true);
        database.connect();

        service.submit(() -> {
            recaps = database.getAllRecap();
        });
        final ServerSocket ssocket = new ServerSocket(PORT);
        service.submit(() -> {
            setChanged();
            notifyObservers(SERVER_START);
            while (listening.get()) {

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
                    break;
                }
            }
            try {

                service.shutdown();
                process.forEach((clientProc) -> {
                    clientProc.close();
                });
                ssocket.close();
                database.disconnect();
                setChanged();
                notifyObservers(SERVER_STOP);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.submit(() -> {

            while (listening.get()) {

                try {

                    final Socket client = ssocket.accept();
                    final ClientProcess proc = new ClientProcess(this, client);
                    service.submit(proc);

                    setChanged();
                    notifyObservers(NEW_CONNECTION);
                    addProc(proc);

                } catch (SocketException ex) {

                    setChanged();
                    notifyObservers(ex.getMessage());

                } catch (IOException ex) {

                    setChanged();
                    notifyObservers(ERROR + ERROR_SOCKET);

                }
            }
        });

    }

    /**
     * method to stop the service
     */
    public void stopService() {

        listening.set(false);
    }

    /**
     * method to get the profile of the user
     *
     * @param name name of the user
     * @param mdp password of the user
     * @return a profile
     */
    public Profile login(String name, String mdp) {

        Profile info = database.readProfile(name, mdp);
        if (info != null) {
            setChanged();
            notifyObservers(LOGIN + name);
            verifyOther(info.getId());
        }

        return info;

    }

    private void verifyOther(int id) {

        ClientProcess other = process.stream().filter((t) -> {
            return t.isAuthentified();
        }).filter((t) -> {
            return t.getUserInfo().getId() == id;
        }).findFirst().orElse(null);

        if (other != null) {
            other.close();
        }

    }

    /**
     * method to register a new profile
     *
     * @param name name of the new user
     * @param mdp password of the new user
     * @return
     */
    public boolean register(String name, String mdp) {

        boolean created = database.createProfile(name, mdp);
        if (created) {
            setChanged();
            notifyObservers(REGISTRATION + name);
        }

        return created;
    }

    /**
     * get a client process from its id so he must be authentified to not get a
     * null
     *
     * @param id of the user
     * @return the client process
     */
    public synchronized ClientProcess getClientProcess(int id) {

        return process.stream().filter((t) -> {
            return t.isAuthentified() && t.getUserInfo().getId() == id;
        }).findFirst().orElse(null);

    }

    /**
     * method to update the other users
     */
    public synchronized void updateOthers() {

        //list process authentified
        List<ClientProcess> authentified = process.stream().filter((t) -> {
            return t.isAuthentified();
        }).collect(Collectors.toCollection(ArrayList::new));

        if (authentified.isEmpty()) {
            return;
        }
        //list profiles authentified
        List<Profile> users = authentified.stream().map((t) -> {
            return t.getUserInfo();
        }).collect(Collectors.toCollection(ArrayList::new));

        authentified.forEach((t) -> {

            t.updateMembers(users);
        });
    }

    /**
     * method to remove a process client from the list
     *
     * @param proc
     */
    public synchronized void removeProc(ClientProcess proc) {

        this.process.remove(proc);
        if (proc.isAuthentified()) {
            setChanged();
            notifyObservers(DISCONNECTION + proc.getUserInfo().getName());
            updateOthers();
        }
    }

    /**
     * method to register a game in the database
     *
     * @param game
     */
    public synchronized void registerGame(Game game) {

        database.registerGame(game);
        this.service.submit(() -> {

            recaps = database.getAllRecap();

        });

    }

    public List<RecapInfo> getRecaps() {

        return recaps;//no defensive copy , Server side
    }

    /**
     * method to get the list of games of a user
     *
     * @param idUser the id of the player
     * @return list of gameinfo
     */
    public List<GameInfo> getGames(int idUser) {

        return database.retrieveGames(idUser);
    }

    private void addProc(ClientProcess proc) {

        if (process.contains(proc)) {
            throw new IllegalArgumentException(ERROR_PROC); //methode privé , ceci n'est pas censé arriver et traduirait une corruption du server 
        }
        this.process.add(proc);

    }
}
