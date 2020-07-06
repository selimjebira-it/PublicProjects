/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeServer.model.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import model.Data;
import model.DataPlaying;
import model.DataType;
import model.GameInfo;
import model.Profile;
import model.action.ActionType;
import model.action.DataAction;
import model.transfert.DataGame;
import model.transfert.DataMembers;
import model.transfert.DataMsg;
import model.transfert.DataProfile;
import model.transfert.MsgType;
import tictactoeServer.model.game.Game;
import tictactoeServer.model.game.GameException;
import tictactoeServer.model.game.User;

/**
 *
 * @author selim
 */
public final class ClientProcess implements Runnable, Observer {

    private final static String MSG_SUCCES_LOGIN = "WELCOME";
    private final static String MSG_SUCCES_WINNING = "YOU WON";

    private final static String MSG_INVITATION = "INVITATION FROM :";

    private final static String MSG_INFO_GAMEFINISHED = "THE GAME IS OVER";
    private final static String MSG_INFO_ACCEPTED = "MATCH ACCEPTED";
    private final static String MSG_INFO_DENIED = "MATCH DENIED";
    private final static String MSG_INFO_GAMEBEGIN = "THE GAME BEGINS";

    private final static String MSG_ERROR_LOGGED = "YOU ARE LOGGED";
    private final static String MSG_ERROR_LOGIN = "NAME OR PASSWORD INCORRECT";
    private final static String MSG_ERROR_GAME = "YOU FIRST NEED TO CREATE A GAME";
    private final static String MSG_ERROR_REGISTRATION = "THE NAME IS ALREADY TAKEN :";
    private final static String MSG_ERROR_LOSING = "YOU LOST";
    private final static String MSG_ERROR_CURRENTLY_NOT_YOU = "THIS IS NOT YOUR TURN";
    private final static String MSG_ERROR_ACTION = "ACTION UNRECOGNIZED";
    private final static String MSG_ERROR_GAME_ID = "NO GAME FOR THIS ID";
    private static final String MSG_ERROR_INVITATION = "INVITATION DENIED : ";

    private final Server server;
    private final Socket socketToClient;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private final ExecutorService service;

    private boolean authentified;
    private final AtomicBoolean listening;
    private boolean invitation;
    private boolean owner;

    private User user;
    private Profile userInfo;
    private List<Profile> users;
    private Game game;

    /**
     * constructor of a client Process
     *
     * @param server a reference to the server
     * @param socketToClient a reference to the socket linked to client
     * @throws IOException
     */
    public ClientProcess(Server server, Socket socketToClient) throws IOException {

        this.server = server;
        this.socketToClient = socketToClient;
        this.out = new ObjectOutputStream(new BufferedOutputStream(socketToClient.getOutputStream()));
        this.out.flush();
        this.in = new ObjectInputStream(new BufferedInputStream(socketToClient.getInputStream()));
        this.listening = new AtomicBoolean(false);
        this.service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    }

    private void sendObject(Object o) {
        try {
            out.writeObject(o);
            out.flush();
        } catch (IOException ex) {
            //Logger.getLogger(ClientProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void login(String name, String mdp) {

        Profile info;
        if ((info = server.login(name, mdp)) != null) {

            user = new User(info.getId(), info.getName(), info.getVictories(), info.getDefeats(), info.getDraws());
            authentified = true;
            sendObject(new DataProfile(userInfo = info));
            server.updateOthers();

        } else {

            sendObject(new DataMsg(MsgType.ERROR, MSG_ERROR_LOGIN));

        }
    }

    private void register(String name, String mdp) {

        if (server.register(name, mdp)) {
            login(name, mdp);
        } else {
            sendObject(new DataMsg(MsgType.ERROR, MSG_ERROR_REGISTRATION));
        }
    }

    private void listen() throws IOException, ClassNotFoundException {

        Object o = in.readObject();
        if (o instanceof Data) {
            service.submit(() -> {
                handleData((Data) o);
            });
        }

    }

    private void handleAction(ActionType action) {

        switch (action) {

            case CLOSE:

                close();
                break;

            case SHOWGAMES:

                sendObject(new DataProfile(userInfo));
                break;

            case ACCEPT:

                invitation = true;
                break;

            case REPLAY:

                //TODO
                break;

            case SHOWPLAYERS:

                sendObject(new DataMembers(users));
                break;

            case EXIT_GAME:
            case SURRENDER: {
                try {
                    game.surrender(this.user.getId());
                } catch (GameException ex) {
                    Logger.getLogger(ClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
                
            
            default:
                sendObject(new DataMsg(MsgType.ERROR, MSG_ERROR_ACTION));
        }
    }

    private void handleData(Data data) {

        Pair<String, char[]> identification;
        Pair<Integer, List<GameInfo>> gameInfos;

        switch (data.getType()) {

            case REGISTER:

                identification = (Pair) data.getContent();
                register(identification.getKey(), new String(identification.getValue()));
                break;

            case LOGIN:

                identification = (Pair) data.getContent();
                login(identification.getKey(), new String(identification.getValue()));
                break;

            case ACTION:

                handleAction((ActionType) data.getContent());
                break;

            case GAME:

                gameInfos = (Pair<Integer, List<GameInfo>>) data.getContent();
                int idPlayer = gameInfos.getKey();
                List<GameInfo> games = server.getGames(idPlayer);
                sendObject(new DataGame(idPlayer, games));

                break;

            case MEMBERS:

                sendObject(new DataMembers(users));
                break;

            case INVITATION:

                int idInvited = (int) data.getContent();

                handleInvitation(idInvited);

                break;

            case PLAY:
                
                if(game == null)return;
                int pos = (int) data.getContent();
                if (game.getCurrentId() != this.user.getId()) {

                    sendObject(new DataMsg(MsgType.ERROR, MSG_ERROR_CURRENTLY_NOT_YOU));
                } else {
                    try {
                        game.play(pos);
                    } catch (GameException ex) {
                        sendObject(new DataMsg(MsgType.ERROR, ex.getMessage()));
                    }
                }
                break;
                
            case RECAP:
                
                 sendObject(new Data(DataType.RECAP,server.getRecaps()));
                 break;
            default:

                System.out.println("cette data n'est pas géré dans client process");

        }

    }

    private void handleInvitation(int idInvited) {

        ClientProcess invited = server.getClientProcess(idInvited);

        try {
            if (invited.sendInvitation(this.user.getName())) {

                owner = true;
                game = new Game(invited.user, this.user);
                game.addObserver(this);
                game.addObserver(invited);
                game.start();
                sendObject(new DataAction(ActionType.ACCEPT));
                invited.sendObject(new DataAction(ActionType.ACCEPT));

            } else {
                sendObject(new DataMsg(MsgType.ERROR, MSG_ERROR_INVITATION));
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GameException ex) {
            sendObject(new DataMsg(MsgType.ERROR, ex.getMessage()));
        }

    }

    @Override
    public void run() {

        listening.set(true);
        service.submit(() -> {
            try {
                while (listening.get()) {

                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }

                }
                service.shutdown();
                server.removeProc(this);
                out.close();
                in.close();
                socketToClient.close();
            } catch (IOException ex) {
                //Erreur à la fermeture socket déjà fermé 
                //Logger.getLogger(ClientProcess.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

        service.submit(() -> {
            while (listening.get()) {
                try {
                    listen();
                } catch (IOException | ClassNotFoundException ex) {
                    //Logger.getLogger(ClientProcess.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }

        });

    }

    /**
     * method to close the socket
     */
    public void close() {

        if (game != null) {
            try {
                game.surrender(this.user.getId());
            } catch (GameException ex) {
                Logger.getLogger(ClientProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sendObject(new DataAction(ActionType.CLOSE));
        listening.set(false);
    }

    /**
     * method to send an invitation to the client
     *
     * @param host the name of the host
     * @return response of the user
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean sendInvitation(String host) throws IOException, ClassNotFoundException {

        sendObject(new Data(DataType.INVITATION, host));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean result = invitation;
        invitation = false;
        return result;

    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof Game) {
            game = (Game) o;
            if (arg == null) {

                if (game.isFinished()) {

                    sendObject(new Data(DataType.PLAY, new DataPlaying(0, 0, false, true, game.getWinner().getId())));
                    o.deleteObserver(this);
                    this.user = (User) (game.getPlayer1().getId() == this.user.getId() ? game.getPlayer1() : game.getPlayer2());
                    this.userInfo = new Profile(user.getId(), user.getName(), user.getVictories(), user.getDefeats(), user.getDraws());

                    if (this.game != null && owner) { //owner of the game
                        endGame();
                    }
                    sendObject(new DataAction(ActionType.EXIT_GAME));
                    game = null;

                }
            } else {
                Pair<Integer, Boolean> pair = (Pair) arg;
                sendObject(new Data(DataType.PLAY, new DataPlaying(game.getCurrentId(), pair.getKey(), pair.getValue(), false, -1)));
            }

        }

    }

    private void endGame() {

        server.registerGame(game);
        owner = false;
    }

    /**
     * method to update client with the new list of client
     *
     * @param users
     */
    public void updateMembers(List<Profile> users) {

        this.users = users;
        sendObject(new DataMembers(users));
    }

    /**
     * method to get the user
     *
     * @return
     */
    public User getUser() {

        return user;
    }

    /**
     * methode to get the user informations
     *
     * @return
     */
    public Profile getUserInfo() {

        return userInfo;
    }

    /**
     * method to know if a process client is authentified
     *
     * @return
     */
    public boolean isAuthentified() {

        return authentified;
    }

    /**
     * method to know if a process is in game
     *
     * @return
     */
    public boolean isInGame() {

        return user.isInGame();
    }

}
