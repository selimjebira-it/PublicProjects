/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.ViewFx;

import blokus.Interface.Observer;
import blokus.Model.Piece;
import blokus.Model.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import static java.util.stream.Collectors.toCollection;
import java.util.stream.Stream;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author selim
 */
public class PieceFx extends VBox implements Observer {

    private final static int BOARD_SIZE = 5;
    private final static int RECT_SIZE = 10;
    private final static Paint DEFAULT_COLOR = Paint.valueOf("WHITE");
    private final static Paint COLOR_USED = Paint.valueOf("GREY");
    private final static Position[] POSES = {new Position(0, 0), new Position(0, BOARD_SIZE - 1),
        new Position(BOARD_SIZE - 1, BOARD_SIZE - 1), new Position(BOARD_SIZE - 1, 0), new Position(0, BOARD_SIZE / 2),
        new Position(BOARD_SIZE / 2, 0), new Position(BOARD_SIZE / 2, BOARD_SIZE / 2)};

    private final Piece piece;
    private final GridPane grid;
    private final ArrayList<ArrayList<Rectangle>> contentGrid;

    private ArrayList<Rectangle> positionsOfPiece;
    private final Paint color;

    public PieceFx(Piece piece) {

        this.piece = piece;
        contentGrid = new ArrayList<>();
        grid = initGrid();
        color = Paint.valueOf(piece.getColor());
        positionsOfPiece = findGoodPos(piece);
        piece.addObs(this);
        this.getChildren().add(grid);
    }

    private GridPane initGrid() {

        GridPane res = new GridPane();
        Stream.iterate(0, y -> y + 1).limit(BOARD_SIZE).forEach(y -> {
            ArrayList<Rectangle> lineRectangle = new ArrayList<>();
            Stream.iterate(0, x -> x + 1).limit(BOARD_SIZE).forEach(x -> {
                Rectangle rectangle = new Rectangle(RECT_SIZE, RECT_SIZE,Paint.valueOf("WHITE"));
                lineRectangle.add(rectangle);
                res.add(rectangle, x, y);
            });
            contentGrid.add(lineRectangle);
        });
        res.setGridLinesVisible(true);

        return res;

    }

    private Rectangle getRectangle(Position position) {

        try {
            return contentGrid.get(position.getY()).get(position.getX());
        } catch (IndexOutOfBoundsException iob) {
            return null;
        }
    }

    private boolean canbeInsert(ArrayList<Position> positions) {

        return positions.stream().map(pos -> {
            return getRectangle(pos);
        }).noneMatch(Objects::isNull);
    }

    private ArrayList<Rectangle> findGoodPos(Piece piece) {

        ArrayList<Position> res = Arrays.asList(POSES).stream().map(pos -> {
            return piece.getPositionsWithFirst(pos);
        }).filter((ArrayList<Position> positions) -> {
            return canbeInsert(positions);
        }).findFirst().orElse(null);

        if (res.isEmpty()) {
            throw new IllegalArgumentException("Piece is too big for the canvas");
        }
        return res.stream().map(pos -> {
            return getRectangle(pos);
        }).collect(toCollection(ArrayList::new));
    }

    @Override
    public void update() {

        positionsOfPiece = findGoodPos(piece);
        if (piece.isPlayed()) {
            setUsed();
        } else {
            setUnused();
        }

    }

    private void modifyColor(ArrayList<Rectangle> rectangles, Paint color) {

        rectangles.forEach((Rectangle rectangle) -> {
            rectangle.setFill(color);

        });
    }

    private void clear() {

        contentGrid.stream().flatMap(list -> {
            return list.stream();
        }).forEach(rectangle -> {
            rectangle.setFill(DEFAULT_COLOR);
        });

    }

    private void setUsed() {

        clear();
        modifyColor(positionsOfPiece, COLOR_USED);

    }

    private void setUnused() {

        clear();
        modifyColor(positionsOfPiece, color);

    }

    public ArrayList<Rectangle> getPositionsPiece() {

        return positionsOfPiece;
    }

    public Piece getPiece() {
        return piece;
    }

    
    
    
    
    
    
    

}
