package tictactoeClient.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.Data;
import model.DataType;
import model.GameInfo;
import model.Profile;
import model.RecapInfo;
import model.action.ActionType;
import model.action.DataAction;
import model.transfert.DataGame;
import model.transfert.DataMembers;
import model.transfert.MsgType;
import tictactoeClient.model.ClientNio;

/**
 *
 * @author selim
 */
public class LobbyNio extends AnchorPane implements Observer {

    public static final String VICTORY = "VICTORY";
    public static final String DEFEAT = "DEFEAT";
    public static final String DRAW = "DRAW";

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnStat;

    @FXML
    private Button btnClassement;

    @FXML
    private VBox UsersContainer;

    @FXML
    private VBox GameContainer;

    private ClientAppNio app;

    private GameNio game;

    private ClientNio client;

    private final List<PlayerNio> listPlayers;

    private Profile profile;

    private final StatNio stat;
    
    private final ClassementNio classement;
    
    

    private int idPicked;

    /**
     * constructor of lobbyNio
     *
     * @param app the main app
     * @param client
     * @param profile the main profile
     */
    public LobbyNio(ClientAppNio app, ClientNio client, Profile profile) {

        initLoader();
        this.app = app;
        this.listPlayers = new ArrayList<>();
        this.profile = profile;
        this.client = client;
        this.stat = new StatNio(app, this);
        this.classement = new ClassementNio(app, this);
        this.game = new GameNio(app, client, this, profile);
        new Scene(game);
        new Scene(stat);
        new Scene(classement);
        idPicked = -1;
    }

    private void initLoader() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lobby.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LobbyNio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * method to update the stat of the lobby
     *
     * @param profile
     */
    public void updateStat(Profile profile) {

        stat.updateProfile(profile);
    }

    @FXML
    private void createGame(ActionEvent event) {

        if (idPicked == -1 || !listOfAvaiablePlayers().contains(idPicked)) {
            app.createNotification(MsgType.ERROR, "YOU MUST CHOSE A PLAYER IN THE LIST");
        } else {
            try {
                client.sendObject(new Data(DataType.INVITATION, idPicked));
            } catch (IOException ex) {
                app.createNotification(MsgType.ERROR, ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LobbyNio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void getStatistics(ActionEvent event) {

        app.setScene(stat.getScene());

    }

    @FXML
    private void refresh(ActionEvent event) {

        try {
            client.sendObject(new DataMembers(null));
            client.sendObject(new DataGame(this.profile.getId(), null));
            client.sendObject(new DataAction(ActionType.SHOWGAMES));
            client.sendObject(new Data(DataType.RECAP,null));
        } catch (IOException | ClassNotFoundException ex) {
            app.createNotification(MsgType.ERROR, ex.getMessage());

        }

    }

    @FXML
    private void getClassement(ActionEvent event) {

        app.setScene(classement.getScene());
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof Data) {
            Data data = (Data) arg;
            switch (data.getType()) {

                case MEMBERS:

                    List<Profile> users = (List) data.getContent();
                    updateUsers(users);

                    break;

                case GAME:

                    List<GameInfo> games = ((Pair<Integer, List<GameInfo>>) data.getContent()).getValue();
                    updateGames(games);

                    break;
                case PROFIL:
                    this.profile = (Profile) data.getContent();
                    stat.updateProfile(profile);
                    break;
                case RECAP:
                    System.out.println("recap");
                    classement.update((List<RecapInfo>) data.getContent());
                    System.out.println("fin recap");
                    
                    break;
                case ACTION:
                    ActionType type = (ActionType) data.getContent();
                    if (type == ActionType.ACCEPT) {
                        Platform.runLater(() -> {
                            app.setScene(game.getScene());
                        });
                    }

            }
        }
    }

    private void updateGames(List<GameInfo> games) {

        Platform.runLater(() -> {
            GameContainer.getChildren().clear();
            games.forEach((game) -> {

                GameRecapNio recap = new GameRecapNio();
                recap.update(game, profile);

                GameContainer.getChildren().add(recap);

            });
        });

    }

    private void updateUsers(List<Profile> users) {

        Platform.runLater(() -> {
            listPlayers.clear();
            UsersContainer.getChildren().clear();
            users.forEach((user) -> {
                if (user.getId() != this.profile.getId()) {
                    PlayerNio player = new PlayerNio();
                    player.updateProfile(user);
                    player.setOnMouseClicked((event) -> {

                        playerPicked(player.getPlayerId());

                    });

                    UsersContainer.getChildren().add(player);

                    listPlayers.add(player);
                }
            });

        });

    }

    private void playerPicked(int id) {

        idPicked = id;
        listPlayers.stream().filter((t) -> {
            return t.getPlayerId() != id;
        }).forEach((t) -> {

            t.resetPicked();
        });

    }

    private List<Integer> listOfAvaiablePlayers() {

        return listPlayers.stream().map((t) -> {
            return t.getPlayerId();
        }).collect(Collectors.toCollection(ArrayList::new));
    }

}
