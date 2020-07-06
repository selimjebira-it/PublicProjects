package tictactoeClient.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.GameInfo;
import model.Profile;
import static tictactoeClient.view.LobbyNio.DEFEAT;
import static tictactoeClient.view.LobbyNio.DRAW;
import static tictactoeClient.view.LobbyNio.VICTORY;

/**
 *
 * @author selim
 */
public class GameRecapNio extends AnchorPane {

    public static final String COLOR_DEFEAT = "#fc791c";

    @FXML
    private GridPane grid;
    @FXML
    private Label nameOne;
    @FXML
    private Label nameTwoo;
    @FXML
    private Label result;

    public GameRecapNio() {

        initLoader();
        
    }

    private void initLoader() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gamerecap.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GameRecapNio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setNameOne(String name) {

        this.nameOne.setText(name);
    }

    private void setNameTwoo(String name) {

        this.nameTwoo.setText(name);
    }

    private void setResult(String result) {

        this.result.setText(result);
        switch (result) {
            case LobbyNio.DEFEAT:
                this.grid.setStyle(PlayerNio.BACKGROUND + COLOR_DEFEAT);
                break;
            case LobbyNio.VICTORY:
                this.grid.setStyle(PlayerNio.BACKGROUND + PlayerNio.COLOR_PICKED);
                break;
            default:
                this.grid.setStyle(PlayerNio.BACKGROUND + PlayerNio.COLOR_UNPICKED);
        }
    }

    /**
     * method to update the recap of the game
     * @param game the game
     * @param profile the profile
     */
    public void update(GameInfo game, Profile profile) {

        String namePlayerOne = game.getIdPlayer1() == profile.getId() ? profile.getName() : "PLAYER";
        String namePlayerTwoo = game.getIdPlayer2() == profile.getId() ? profile.getName() : "PLAYER";
        String resultGame = game.getResult() == profile.getId() ? VICTORY : game.getResult() == 0 ? DRAW : DEFEAT;

        setNameOne(namePlayerOne);
        setNameTwoo(namePlayerTwoo);
        setResult(resultGame);

    }

}
