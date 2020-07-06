package tictactoeClient.view;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Profile;

/**
 *
 * @author selim
 */
public class StatNio extends AnchorPane {

    private final static String HEADER_DEFAULT = "Information About : ";
    private final static String VICTORIES_DEFAULT = "Victories : ";
    private final static String DEFEATS_DEFAULT = "Defeats : ";
    private final static String DRAWS_DEFAULT = "Draws : ";
    private final static String TOTAL_DEFAULT = "Total Games : ";
    private final static String SCORE_DEFAULT = "Score : ";

    @FXML
    private Label labelVictories;
    @FXML
    private Label labelDefeats;
    @FXML
    private Label labelDraws;
    @FXML
    private Label labelName;
    @FXML
    private Label labelNbOfGames;
    @FXML
    private Label LabelScore;
    @FXML
    private Button btnBack;

    private final ClientAppNio app;
    private final LobbyNio lobby;

    public StatNio(ClientAppNio app, LobbyNio lobby) {

        initLoader();
        this.app = app;
        this.lobby = lobby;

    }

    private void initLoader() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(StatNio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void backToLobby(ActionEvent event) {
        app.setScene(lobby.getScene());
    }

    /**
     * method to update the profile of the stat view;
     *
     * @param profile
     */
    public void updateProfile(Profile profile) {
        
        Platform.runLater(() -> {
            labelName.setText(HEADER_DEFAULT + profile.getName());
            labelDraws.setText(DRAWS_DEFAULT + profile.getDraws());
            labelDefeats.setText(DEFEATS_DEFAULT + profile.getDefeats());
            labelVictories.setText(VICTORIES_DEFAULT + profile.getVictories());
            labelNbOfGames.setText(TOTAL_DEFAULT + profile.getTotalGames());
            LabelScore.setText(SCORE_DEFAULT + new DecimalFormat("##.##").format(profile.getScore()) + "%");
        });

    }

}
