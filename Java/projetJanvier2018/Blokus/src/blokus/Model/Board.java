/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import static java.util.stream.Collectors.toCollection;
import java.util.stream.Stream;

/**
 *
 * @author selim
 */
public class Board {

    private static final Position[] SIDES = {new Position(1, 0), new Position(-1, 0), new Position(0, 1), new Position(0, -1)};
    private static final Position[] CORNERS = {new Position(-1, -1), new Position(-1, 1), new Position(1, -1), new Position(1, 1)};

    private final int width;
    private final int height;
    protected final ArrayList<ArrayList<Box>> grid;

    /**
     * constructor of board
     * @param width the width of the board
     * @param height the height of the board
     */
    protected Board(int width, int height) {

        this.width = width;
        this.height = height;
        this.grid = Stream.iterate(0, y -> y + 1).limit(height).map(y -> {
            return Stream.iterate(0, x -> x + 1).limit(width).map(x -> {
                return new Box(new Position(x, y));
            }).collect(toCollection(ArrayList::new));
        }).collect(toCollection(ArrayList::new));
    }

    /**
     * method to get a box from a given position
     * @param position the position given
     * @return the box at the specified position
     */
    protected Box getBox(Position position) {

        try {
            return grid.get(position.getY()).get(position.getX());
        } catch (IndexOutOfBoundsException iob) {
            return null;
        }
    }

    /**
     * method to get the boxes at specified positions
     * @param positions the positions given
     * @return boxes at specified positions
     */
    protected ArrayList<Box> getBox(ArrayList<Position> positions) {
        
        return positions.stream().map(pos -> {
            return getBox(pos);
        }).collect(toCollection(ArrayList::new));
    }

    /**
     * method to get the boxes around a position by a specific translation
     * @param position the position we want to look around
     * @param specifiedPosition the positions around
     * @return the boxes around
     */
    protected ArrayList<Box> getBox(Position position, Position[] specifiedPosition) {

        return Arrays.asList(specifiedPosition).stream().map(pos -> {
            return new Position(position.getX() + pos.getX(), position.getY() + pos.getY());
        }).map(pos -> {
            return getBox(pos);
        }).filter(Objects::nonNull).collect(toCollection(ArrayList::new));
    }

    /**
     * method to get the corners around a box
     * @param box the specified box
     * @return boxes around
     */
    protected ArrayList<Box> getCorners(Box box) {

        Position position = box.getPosition();

        return getBox(position, CORNERS);
    }

    /**
     * get the corners around boxes
     * @param boxes the boxes we want to get corners of
     * @return all the corners
     */
    protected ArrayList<Box> getCorners(ArrayList<Box> boxes) {

        return boxes.stream().map(box -> {
            return getCorners(box);
        }).flatMap((ArrayList<Box> listBoxes) -> {
            return listBoxes.stream();
        }).distinct().collect(toCollection(ArrayList::new));
    }

    /**
     * method to get sides around a box
     * @param box the specified box
     * @return boxes on sides
     */
    protected ArrayList<Box> getSides(Box box) {

        Position position = box.getPosition();
        return getBox(position, SIDES);

    }

    /**
     * method to get all the sides of multiples boxes
     * @param boxes the specified boxes
     * @return all the sides around those boxes
     */
    protected ArrayList<Box> getSides(ArrayList<Box> boxes) {

        return boxes.stream().map(box -> {
            return getSides(box);
        }).flatMap((ArrayList<Box> listBoxes) -> {
            return listBoxes.stream();
        }).distinct().collect(toCollection(ArrayList::new));

    }

    /**
     * method to check if all the boxes given  are not null
     * @param boxes the boxes to check
     * @return true if all boxes are not null
     */
    protected boolean areNotNull(ArrayList<Box> boxes) {

        return boxes.stream().noneMatch(Objects::isNull);
    }

    /**
     * method to check if all the boxes given are free so 
     * if the color of the boxes is the default color
     * @param boxes the boxes to check
     * @return true if all the boxes are free
     */
    protected boolean areFree(ArrayList<Box> boxes) {

        return boxes.stream().allMatch(Box::isFree);
    }

    /**
     * method to check if the sides are ok so if the color of the box
     * next does not have the same color (rule of the game)
     * @param boxes the boxes to check
     * @param color the color to evaluate
     * @return true if all the sides does not have the color given
     */
    protected boolean sidesOk(ArrayList<Box> boxes, String color) {

        return getSides(boxes).stream().filter((Box box)->{return !box.isFree();}).noneMatch((Box box) -> {
            return box.getColor().equals(color);
        });

    }

    /**
     * method to check if at least one corner of boxes does have the same color
     * (rule of the game)
     * @param boxes the boxes to check
     * @param color the color to evaluate
     * @return true if at least one corner does have the color given
     */
    protected boolean cornersOk(ArrayList<Box> boxes, String color) {

        return getCorners(boxes).stream().filter((Box box)->{return !box.isFree();}).anyMatch((Box box) -> {
            return box.getColor().equals(color);
        });
    }

    /**
     * method to check if the positions of the piece contains the specified position
     * @param positionsOfPiece the positions of the piece
     * @param first the position those positions should contains
     * @return true if at least one of these position equals the given position
     */
    protected boolean canBeInsertFirst(ArrayList<Position> positionsOfPiece, Position first) {

        
        if (positionsOfPiece.contains(first)) {
            ArrayList<Box> boxes = getBox(positionsOfPiece);
            if (areNotNull(boxes)) {
                if (areFree(boxes)) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * method to check if the positions given can be insert in the board by checking 
     * boxes linked to these positions should be : 
     * not null (outside of board)
     * free(not an onther piece placed on it)
     * sides does not have the same color
     * at least one corner have the same color
     * @param positionsOfPiece positions of the piece
     * @param color the specified color
     * @return 
     */
    protected boolean canBeInsert(ArrayList<Position> positionsOfPiece, String color) {

        ArrayList<Box> boxes = getBox(positionsOfPiece);
        if (areNotNull(boxes)) {
            if (areFree(boxes)) {
                if (sidesOk(boxes, color)) {
                    if (cornersOk(boxes, color)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * check if the piece can be insert
     * @param piece the piece to insert
     * @param box the box where we want to put the piece on
     * @return 
     */
    protected boolean canBeInsert(Piece piece, Box box) {

        return canBeInsert(piece.getPositionsWithFirst(box.getPosition()), piece.getColor());
    }

    /**
     * method to get all corners free from a list of pieces
     * @param pieces the pieces to get corners from
     * @return the arraylist of boxes which are the corners of those pieces
     */
    protected ArrayList<Box> allCornersFree(ArrayList<Piece> pieces) {

        return pieces.stream().map(piece -> {
            return piece.getPositionsWithFirst(piece.getFirst());
        }).map((ArrayList<Position> positions) -> {
            return getBox(positions);
        }).map((ArrayList<Box> boxes) -> {
            return boxes.stream().filter(Objects::nonNull).collect(toCollection(ArrayList::new));
        }).map((ArrayList<Box> boxes) -> {
            return getCorners(boxes);
        }).flatMap((ArrayList<Box> boxes) -> {
            return boxes.stream();
        }).filter(Box::isFree).collect(toCollection(ArrayList::new));
    }

    public int getWidth() {
        
        return width;
    }

    public int getHeight() {
        
        return height;
    }

    public ArrayList<ArrayList<Box>> getGrid() {
        
        return grid;
    }

    @Override
    public String toString() {
        
        StringBuilder buff = new StringBuilder();
        grid.stream().forEach(line->{
            line.stream().forEach(box->{
                buff.append(box.toString());
            });buff.append(System.getProperty("line.separator"));
        });
        return buff.toString();
    }
    

    
    

}
