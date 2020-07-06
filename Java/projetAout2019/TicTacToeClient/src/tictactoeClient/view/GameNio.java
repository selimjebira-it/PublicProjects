package tictactoeClient.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import model.Data;
import model.DataPlaying;
import model.DataType;
import model.Profile;
import model.action.ActionType;
import model.action.DataAction;
import model.transfert.MsgType;
import tictactoeClient.model.ClientNio;

/**
 *
 * @author selim
 */
public class GameNio extends VBox implements Observer, Initializable {

    private final static String COLOR_ENTERED = "GREY";
    private final static String COLOR_EXITED = "WHITE";
    private final static String MY_JETON = "X";
    private final static String MY_COLOR = "GREEN";
    private final static String OPPONENT_COLOR = "RED";
    private final static String OPPONENT_JETON = "O";
    private final static int SIZE = 9;

    private List<Label> labels;
    private List<GridPane> grids;

    private int gridPicked;

    private int boxpicked;

    private boolean clickable;

    private boolean[] completed;

    private ClientAppNio app;

    private ClientNio clientNio;

    private LobbyNio lobby;

    private Profile profile;

    @FXML
    private GridPane mainGrid;

    public GameNio(ClientAppNio app, ClientNio clientNio, LobbyNio lobby, Profile profile) {

        initLoader();
        this.clientNio = clientNio;
        clientNio.addObserver(this);
        this.lobby = lobby;
        this.profile = profile;
        this.app = app;
        completed = new boolean[SIZE];

    }

    private void initLoader() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GameNio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof Data) {

            Data data = (Data) arg;
            if (data.getType() == DataType.PLAY) {

                updateGame((DataPlaying) data.getContent());
            } else if (data.getType() == DataType.ACTION) {

                switch ((ActionType) data.getContent()) {

                    case EXIT_GAME:

                        switchToLobby();
                        break;

                    case REPLAY:

                        app.createForm("WOULD YOU LIKE TO REPLAY ?");
                        break;

                }
            }

        }
    }

    private void switchToLobby() {

        Platform.runLater(() -> {
            app.setScene(lobby.getScene());
        });
    }

    /**
     * method to update the game
     *
     * @param playingInfo the playing informations
     */
    private void updateGame(DataPlaying playingInfo) {

        if (playingInfo.isFinished()) {

            if (playingInfo.getResult() == this.profile.getId()) { //GAME IS OVER

                app.createNotification(MsgType.SUCCES, "YOU WON");

            } else if (playingInfo.getResult() == 0) {

                app.createNotification(MsgType.INFO, "DRAW");
            } else {

                app.createNotification(MsgType.ERROR, "YOU LOST");
            }
        } else {//GAME IS NOT OVER

            int box = playingInfo.getLastmove() % SIZE;
            int position = playingInfo.getLastmove();
            int grid = playingInfo.getLastmove() / SIZE;
            boolean filledGrid = playingInfo.isLastMovefilledGrid();

            String jeton, color;
            if (playingInfo.getCurrentPlayer() == profile.getId()) {//MY TURN
                app.createNotification(MsgType.INFO, "YOUR TURN");
                jeton = MY_JETON;
                color = MY_COLOR;
                clickable = true;
            } else {//OPPONENT'S TURN
                app.createNotification(MsgType.INFO, "OPPONENT'S TURN");
                jeton = OPPONENT_JETON;
                color = OPPONENT_COLOR;
                clickable = false;
            }

            if (position != -1) {//FILLED THE BOX
                setJeton(position, jeton);
            }
            if (filledGrid) {//FILLED THE GRID

                setGrid(grid, color);

            }
        }

    }

    private void setJeton(int position, String content) {
        Platform.runLater(() -> {

            this.labels.get(position).setText(content);
        });

    }

    private void setGrid(int grid, String color) {
        Platform.runLater(() -> {
            this.grids.get(grid).setBackground(new Background(new BackgroundFill(Paint.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    private List<GridPane> getLittleGrid() {

        final List<GridPane> result = new ArrayList<>();
        mainGrid.getChildren().stream().filter((t) -> {
            return t instanceof AnchorPane;
        }).map(t -> {

            return ((AnchorPane) t);
        }).map((t) -> {
            return t.getChildren();
        }).forEach((listChild) -> {
            listChild.forEach((child) -> {
                if (child instanceof GridPane) {
                    result.add((GridPane) child);
                }
            });
        });
        return result;

    }

    private List<Label> getAllLabels() {
        final List<Label> result = new ArrayList<>();
        
        getLittleGrid().forEach((grid) -> {
            grid.getChildren().forEach((child) -> {
                if(child instanceof VBox){
                    ((VBox) child).getChildren().forEach((vBoxChildren) -> {
                        if(vBoxChildren instanceof Label)
                            result.add((Label)vBoxChildren);
                    });
                }
            });
        });
        
        return result;
    }

    @FXML
    private void clicked(MouseEvent event) {

        Object o = event.getSource();
        if (o instanceof VBox) {

            final VBox box = (VBox) o;
            boxpicked = box.getParent().getChildrenUnmodifiable().indexOf(box);
            gridPicked = mainGrid.getChildren().indexOf(box.getParent().getParent());
            int position = gridPicked * SIZE + boxpicked;
            try {
                clientNio.sendObject(new Data(DataType.PLAY, position));
            } catch (IOException ex) {
                app.createNotification(MsgType.ERROR, ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameNio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void entered(MouseEvent event) {

//        if (event.getSource() instanceof VBox) {
//            VBox source = (VBox) event.getSource();
//            source.setBackground(new Background(new BackgroundFill(Paint.valueOf(COLOR_ENTERED), CornerRadii.EMPTY, Insets.EMPTY)));
//        }

    }

    @FXML
    private void exited(MouseEvent event) {

//        if (event.getSource() instanceof VBox) {
//            VBox source = (VBox) event.getSource();
//            source.setBackground(new Background(new BackgroundFill(Paint.valueOf(COLOR_EXITED), CornerRadii.EMPTY, Insets.EMPTY)));
//        }
    }

    @FXML
    private void surrender() {

        try {
            clientNio.sendObject(new DataAction(ActionType.SURRENDER));
        } catch (IOException ex) {
            app.createNotification(MsgType.ERROR, ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameNio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void replay() {

        try {
            clientNio.sendObject(new DataAction(ActionType.REPLAY));
        } catch (IOException ex) {
            app.createNotification(MsgType.ERROR, ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameNio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void close() {

        try {
            clientNio.sendObject(new DataAction(ActionType.EXIT_GAME));
        } catch (IOException ex) {
            app.createNotification(MsgType.ERROR, ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameNio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void about() {

        //not implemented
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        grids = getLittleGrid();
        labels = getAllLabels();
        Platform.runLater(() -> {
            labels.forEach((t) -> {
                t.setText("");
            });
        });
    }

}
