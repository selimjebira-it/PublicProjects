/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.ViewFx;

import blokus.Interface.Observer;
import blokus.Model.Piece;
import blokus.Model.Player;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 *
 * @author selim
 */
public class PlayerFx implements Observer {

    private static final int NB_PIECE_LINE = 8;
    private static final int NB_LINES = 3;
    private static final Paint DEFAULT_COLOR_LABEL = Paint.valueOf("BLACK");

    private final VBox root;
    private final Player player;
    private final ArrayList<PieceFx> bagFx;
    private final Label label;
    private final GridPane grid;

    public PlayerFx(Player player) {

        this.player = player;
        this.label = new Label(player.toString());
        this.bagFx = new ArrayList<>();
        grid = new GridPane();
        root = new VBox(label, grid);
        initPlayer();
        player.addObs(this);
    }

    private void initPlayer() {

        initBag();
        initGrid();
    }

    private void initBag() {

        player.getBag().forEach(piece -> {
            PieceFx pieceFx = new PieceFx(piece);
            bagFx.add(pieceFx);
        });
    }

    private void initGrid() {

        for (int y = 0; y < NB_LINES; y++) {
            for (int x = 0; x < NB_PIECE_LINE; x++) {
                int indexElem = (y * NB_PIECE_LINE) + x;
                if(indexElem >= bagFx.size()){
                    break;
                }
                PieceFx pieceFx = bagFx.get(indexElem);
                grid.add(pieceFx, x, y);
            }
        }
        grid.setGridLinesVisible(true);
    }

    private void isCurrentPlayer() {

        label.setTextFill(Paint.valueOf(player.getColor()));
        enableDragAndClickS();

    }

    private void enableDragAndClickS() {
        
        try {
            
            File tempFile = File.createTempFile("Piece", ".temp");
            this.bagFx.stream().forEach(pieceFx -> {
                
                setOnDragDetected(pieceFx, tempFile);
                setOnMouseClicked(pieceFx);

            });
            this.bagFx.stream().forEach(pieceFx -> {
                
                setOnDrageDone(pieceFx, tempFile);

            });

        } catch (IOException ex) {
            Logger.getLogger(PlayerFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void writeObject(Object obj,File tempFile){
        
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(tempFile)))){
            out.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerFx.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayerFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setOnDragDetected(PieceFx pieceFx, File tempFile) {

        pieceFx.setOnDragDetected((event) -> {
            Piece piece = pieceFx.getPiece();
            if (piece.isNotPlayed()) {
                writeObject(piece, tempFile);
                Dragboard db = pieceFx.startDragAndDrop(TransferMode.ANY);
                ArrayList<File> listTempFiles = new ArrayList<>();
                listTempFiles.add(tempFile);
                ClipboardContent clip = new ClipboardContent();
                clip.putFiles(listTempFiles);
                db.setContent(clip);
                event.consume();
            }
        });
    }
    
    private void setOnMouseClicked(PieceFx pieceFx){
        Piece piece = pieceFx.getPiece();
        pieceFx.setOnMouseClicked((event) -> {
            MouseButton button = event.getButton();
            if(button == MouseButton.PRIMARY){
                
                piece.reverse();
            }
            if(button == MouseButton.SECONDARY){
                
                piece.miror();
            }
            
        });
    }

    private void setOnDrageDone(PieceFx pieceFx, File tempFile) {
        tempFile.delete();
    }

    private void disableDragAndRightCLick() {

        this.bagFx.stream().forEach(pieceFx -> {
            pieceFx.setOnDragDetected((event) -> {
                //nothing

            });
            pieceFx.setOnMouseClicked((event) -> {
                //nothing
            });

        });
    }

    private void isNotCurrentPlayer() {

        label.setTextFill(DEFAULT_COLOR_LABEL);
        disableDragAndRightCLick();
    }

    @Override
    public void update() {

        
        label.setText(player.toString());
        if (player.isCurrentPlayer()) {

            isCurrentPlayer();

        } else {

            isNotCurrentPlayer();
        }
    }

    public VBox getRoot() {

        return root;
    }

    public ArrayList<PieceFx> getBagFx() {

        return bagFx;
    }

}
