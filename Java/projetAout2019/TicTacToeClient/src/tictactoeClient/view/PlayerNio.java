package tictactoeClient.view;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import model.Profile;

/**
 *
 * @author selim
 */
public class PlayerNio extends AnchorPane implements Initializable {

    @FXML
    private Label labelName;

    @FXML
    private Label labelScore;

    @FXML
    private GridPane grid;

    public static final String BACKGROUND = "-fx-background-color:";
    public static final String COLOR_PICKED = "#9bcc76";
    public static final String COLOR_UNPICKED = "WHITE";

    private Profile profile;

    public PlayerNio() {

        initLoader();

    }

    private void initLoader() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("player.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PlayerNio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.grid.setBackground(new Background(new BackgroundFill(Paint.valueOf(COLOR_UNPICKED), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    /**
     * method to update a profile
     *
     * @param profile
     */
    public void updateProfile(Profile profile) {

        this.profile = profile;
        labelScore.setText(new DecimalFormat("##.##").format(profile.getScore()) + "%");
        labelName.setText(profile.getName());

    }

    /**
     * method to notify the lobby that someone has been picked in the list
     */
    @FXML
    public void clicked() {

        grid.setBackground(new Background(new BackgroundFill(Paint.valueOf(COLOR_PICKED), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * method to reset the background of the player
     */
    public void resetPicked() {

        grid.setBackground(new Background(new BackgroundFill(Paint.valueOf(COLOR_UNPICKED), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * method to get the player id
     *
     * @return the id of the player
     */
    public int getPlayerId() {

        return profile.getId();
    }

}
