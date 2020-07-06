/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeClient.view;


/**
 *
 * @author selim
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.RecapInfo;

public class ClassementRecap extends AnchorPane {

    @FXML
    private Label namePlayerA;

    @FXML
    private Label namePlayerB;

    @FXML
    private Label victories;

    @FXML
    private Label defeats;

    @FXML
    private Label draws;
    
    public ClassementRecap() {

        initLoader();
        
    }

    private void initLoader() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recap.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GameRecapNio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void update(RecapInfo recap){
    
        Platform.runLater(() -> {
        
            namePlayerA.setText(recap.getNamePlayer1());
            namePlayerB.setText(recap.getNamePlayer2());
            victories.setText("" +recap.getVictories());
            defeats.setText("" + recap.getDefeats());
            draws.setText("" + recap.getDraws());
            
        });
    }
    
    

}
