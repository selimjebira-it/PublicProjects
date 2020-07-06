/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeClient.view;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author selim
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.RecapInfo;

public class ClassementNio extends AnchorPane {

    private ClientAppNio app;
    private LobbyNio lobby;

    @FXML
    private VBox container;

    @FXML
    private Button btnBack;

    public ClassementNio(ClientAppNio app, LobbyNio lobby) {

        initLoader();
        this.app = app;
        this.lobby = lobby;

    }

    private void initLoader() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("classement.fxml"));
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
    
    public void update(List<RecapInfo> recaps){
    
//        final List<RecapInfo> recapsSorted = recaps.stream().
//                    sorted((o1, o2) -> (o2.getVictories() + (o2.getDraws() / 2)) - (o1.getVictories() + (o1.getDraws() / 2)))
//                    .collect(Collectors.toList()); //mise à jour des recap des joueurs dans l'ordre
//        
        Platform.runLater(() -> {
            container.getChildren().clear();
            container.getChildren().add(new ClassementRecap());
            recaps.forEach((t) -> {
                ClassementRecap recap = new ClassementRecap();
                recap.update(t);
                container.getChildren().add(recap);
            });
        });
        
    }

}
