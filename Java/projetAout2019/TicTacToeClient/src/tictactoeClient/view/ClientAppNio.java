package tictactoeClient.view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Data;
import model.Profile;
import model.action.ActionType;
import model.action.DataAction;
import model.transfert.DataLogin;
import model.transfert.DataRegistration;
import model.transfert.MsgType;
import tictactoeClient.model.ClientNio;

/**
 *
 * @author selim
 */
public final class ClientAppNio extends Application implements Observer {

    private Stage mainStage;
    private ClientNio clientNio;

    private Profile profile;
    private LobbyNio lobby;

    @Override
    public void start(Stage primaryStage) {

        this.mainStage = primaryStage;
        LoginNio login = new LoginNio(this);
        primaryStage.setScene(new Scene(login));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Application");
        primaryStage.setOnCloseRequest((event) -> {

            try {
                clientNio.sendObject(new DataAction(ActionType.CLOSE));
                clientNio.stopService();
            } catch (NullPointerException nullptr) {
                //Nothing to do
            } catch (IOException ex) {
                createNotification(MsgType.ERROR, ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientAppNio.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        primaryStage.show();

    }

    /**
     * method to create a notification
     *
     * @param type type of notification
     * @param msg content of the notification as plain text
     */
    public synchronized void createNotification(MsgType type, String msg) {

        Popup popup = new Popup();
        Label label = new Label(msg);
        label.setTextFill(type == MsgType.ERROR ? Color.RED : type == MsgType.INFO ? Color.BLACK : type == MsgType.QUESTION ? Color.BLUE : Color.GREEN);
        label.setFont(new Font("Verdana Bold", 24));
        popup.getContent().add(label);
        popup.setAutoHide(true);
        Platform.runLater(() -> {
            popup.show(mainStage);

        });

    }

    /**
     * method to create a form with 2 buttons
     *
     * @param question the question
     */
    public synchronized void createForm(String question) {

        Popup popup = new Popup();
        Label label = new Label(question);
        label.setTextFill(Color.BLUE);
        Button accept, denied;
        accept = new Button("Accept");
        denied = new Button("Refuse");
        accept.setOnAction((event) -> {
            try {
                clientNio.sendObject(new DataAction(ActionType.ACCEPT));
            } catch (IOException ex) {
                createNotification(MsgType.ERROR, ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientAppNio.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        denied.setOnAction((event) -> {
            try {
                clientNio.sendObject(new DataAction(ActionType.REFUSE));
            } catch (IOException ex) {
                createNotification(MsgType.ERROR, ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientAppNio.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        VBox container = new VBox(label, new HBox(accept, denied));
        popup.getContent().addAll(container);
        popup.setAutoHide(true);

        Platform.runLater(() -> {
            popup.show(mainStage);
        });
    }

    /**
     * method to establish connection to the server by specifying intentions
     *
     * @param host the host to contact
     * @param port the port
     * @param name the name of the user
     * @param password password of the user
     * @param login define a login or a registration
     */
    public void sendConfiguration(String host, int port, String name, char[] password, boolean login) {

        try {

            clientNio = new ClientNio(host, port);
            clientNio.addObserver(this);
            ExecutorService connection = Executors.newSingleThreadExecutor();
            connection.submit(clientNio);
            connection.shutdown(); //pas besoin d'autre connection
            if (login) {
                clientNio.sendObject(new DataLogin(name, password));
            } else {
                clientNio.sendObject(new DataRegistration(name, password));
            }
        } catch (IOException e) {
            createNotification(MsgType.ERROR, e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientAppNio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * method to switch from one scene to an other
     *
     * @param scene the scene to switch
     */
    public void setScene(Scene scene) {

        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Data) {

            handleData((Data) arg);

        }
    }

    private void handleData(Data data) {

        switch (data.getType()) {

            case PROFIL:

                this.profile = (Profile) data.getContent();
                refreshLobby();
                break;

            case INVITATION:

                createForm("INVITATION FROM : " + (String) data.getContent());
                break;

            case MESSAGE:

                Pair<MsgType, String> pair = (Pair) data.getContent();
                createNotification(pair.getKey(), pair.getValue());
                break;

            case ACTION:
                handleAction((ActionType) data.getContent());

        }
    }

    private void handleAction(ActionType actionType) {

        switch (actionType) {

            case CLOSE:
                createNotification(MsgType.INFO, "CONNEXION WITH SERVER ABORT");
                Platform.runLater(() -> {
                    this.clientNio.stopService();
                    this.clientNio.deleteObserver(this);
                    clientNio = null;
                    mainStage.close();
                });
                break;
            case ACCEPT:
                createNotification(MsgType.INFO, "THE GAME STARTED");

        }

    }

    private void refreshLobby() {

        if (lobby == null) {
            Platform.runLater(() -> {
                lobby = new LobbyNio(this, clientNio, profile);
                new Scene(lobby);
                clientNio.addObserver(lobby);
                setScene(lobby.getScene());
                createNotification(MsgType.INFO, "CONNECTED");

            });
        }
    }

}
