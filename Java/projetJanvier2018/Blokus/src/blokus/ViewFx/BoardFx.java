/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.ViewFx;

import blokus.Model.BoardControl;
import blokus.Model.Position;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;

/**
 *
 * @author selim
 */
public class BoardFx {

    private final BoardControl board;
    private final GridPane grid;
    private final ArrayList<ArrayList<BoxFx>> boxes;

    public BoardFx(BoardControl board) {

        this.board = board;
        boxes = new ArrayList<>();
        grid = new GridPane();
        board.getGrid().stream().forEach(line -> {
            ArrayList<BoxFx> lineFx = new ArrayList<>();
            line.forEach(box -> {
                Position position = box.getPosition();
                BoxFx boxFx = new BoxFx(box);
                grid.add(boxFx.getRectangle(), position.getX(), position.getY());
                lineFx.add(boxFx);
            });
            boxes.add(lineFx);
        });
        grid.setGridLinesVisible(true);
        
    }
    


    public BoardControl getBoard() {
        return board;
    }

    public GridPane getGrid() {
        return grid;
    }

    public ArrayList<ArrayList<BoxFx>> getBoxes() {
        return boxes;
    }
    
    

}
