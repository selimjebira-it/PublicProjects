/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import blokus.Enum.Color;
import blokus.Enum.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.stream.Collectors.toCollection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class BoardTest {

    static final Position POSITION = new Position(0, 0);
    static final Box [] CORNERS = {new Box(new Position(1,1))};
    static final Box BOX = new Box(POSITION);
    static final Board BOARD = new Board(20, 20);

    public BoardTest() {
    }

    /**
     * Test of getBox method, of class Board.
     */
    @Test
    public void testGetBox_Position() {

        System.out.println("getBox");
        Position position = POSITION;
        Board instance = BOARD;
        Box expResult = BOX;
        Box result = instance.getBox(position);
        assertEquals(expResult, result);

    }

    /**
     * Test of getBox method, of class Board.
     */
    @Test
    public void testGetBox_ArrayList() {
        
        System.out.println("getBox");
        Position[] blabla = new Position[]{new Position(1, 1), new Position(2, 2)};
        ArrayList<Position> positions = Arrays.asList(blabla).stream().collect(toCollection(ArrayList::new));
        Board instance = BOARD;
        ArrayList<Box> expResult = new ArrayList<>();
        for (Position pos : blabla) {
            expResult.add(new Box(pos));
        }
        ArrayList<Box> result = instance.getBox(positions);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of getBox method, of class Board.
     */
    @Test
    public void testGetBox_Position_PositionArr() {
        
        System.out.println("getBox");
        Position position = POSITION;
        Position[] specifiedPosition = {new Position(1,1)};
        Board instance = BOARD;
        ArrayList<Box> expResult = Arrays.asList(CORNERS).stream().collect(toCollection(ArrayList::new));
        ArrayList<Box> result = instance.getBox(position, specifiedPosition);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCorners method, of class Board.
     */
    @Test
    public void testGetCorners_Box() {
        
        System.out.println("getCorners");
        Box box = BOX;
        Board instance = BOARD;
        ArrayList<Box> expResult = Arrays.asList(CORNERS).stream().collect(toCollection(ArrayList::new));
        ArrayList<Box> result = instance.getCorners(box);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCorners method, of class Board.
     */
    @Test
    public void testGetCorners_ArrayList() {
        System.out.println("getCorners");
        Box[] boxesTab = {new Box(POSITION),new Box(POSITION)};
        ArrayList<Box> boxes = Arrays.asList(boxesTab).stream().collect(toCollection(ArrayList::new));
        Board instance = BOARD;
        Box [] expResultTab = {new Box(new Position(1,1))};
        ArrayList<Box> expResult = Arrays.asList(expResultTab).stream().collect(toCollection(ArrayList::new));
        ArrayList<Box> result = instance.getCorners(boxes);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getSides method, of class Board.
     */
    @Test
    public void testGetSides_Box() {
        
        System.out.println("getSides");
        Box box = BOX;
        Board instance = BOARD;
        ArrayList<Box> expResult = Arrays.asList(new Box[]{new Box(new Position(1,0)),new Box(new Position(0,1))}).stream().collect(toCollection(ArrayList::new));
        ArrayList<Box> result = instance.getSides(box);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getSides method, of class Board.
     */
    @Test
    public void testGetSides_ArrayList() {
        
        System.out.println("getSides");
        Box[] boxesTab = {new Box(POSITION),new Box(POSITION)};
        ArrayList<Box> boxes = Arrays.asList(boxesTab).stream().collect(toCollection(ArrayList::new));
        Board instance = BOARD;
        Box [] expResultTab = {new Box(new Position(1, 0)),new Box(new Position(0,1))};
        ArrayList<Box> expResult = Arrays.asList(expResultTab).stream().collect(toCollection(ArrayList::new));
        ArrayList<Box> result = instance.getSides(boxes);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of areNotNull method, of class Board.
     */
    @Test
    public void testAreNotNull() {
        
        System.out.println("areNotNull");
        Box[] boxesTab = {new Box(POSITION)};
        ArrayList<Box> boxes = Arrays.asList(boxesTab).stream().collect(toCollection(ArrayList::new));
        Board instance = BOARD;
        boolean expResult = true;
        boolean result = instance.areNotNull(boxes);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of areFree method, of class Board.
     */
    @Test
    public void testAreFree() {
        
        System.out.println("areFree");
        Box[] boxesTab = {new Box(POSITION),new Box(POSITION)};
        ArrayList<Box> boxes = Arrays.asList(boxesTab).stream().collect(toCollection(ArrayList::new));
        Board instance =BOARD;
        boolean expResult = true;
        boolean result = instance.areFree(boxes);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of sidesOk method, of class Board.
     */
    @Test
    public void testSidesOk() {
        
        System.out.println("sidesOk");
        Box[] boxesTab = {new Box(POSITION),new Box(POSITION)};
        ArrayList<Box> boxes = Arrays.asList(boxesTab).stream().collect(toCollection(ArrayList::new));
        String color = "Blue";
        Board instance = BOARD;
        boolean expResult = true;
        boolean result = instance.sidesOk(boxes, color);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of cornersOk method, of class Board.
     */
    @Test
    public void testCornersOk() {
        System.out.println("cornersOk");
        Box[] boxesTab = {new Box(POSITION),new Box(POSITION)};
        ArrayList<Box> boxes = Arrays.asList(boxesTab).stream().collect(toCollection(ArrayList::new));
        String color = "BLUE";
        Board instance =BOARD;
        boolean expResult = false;
        boolean result = instance.cornersOk(boxes, color);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of canBeInsertFirst method, of class Board.
     */
    @Test
    public void testCanBeInsertFirst() {
        System.out.println("canBeInsertFirst");
        Position[] poses = {new Position(0,0),new Position(1,1)};
        ArrayList<Position> positionsOfPiece = Arrays.asList(poses).stream().collect(toCollection(ArrayList::new));;
        Position first = new Position(0,0);
        Board instance = BOARD;
        boolean expResult = true;
        boolean result = instance.canBeInsertFirst(positionsOfPiece, first);
        assertEquals(expResult, result);
        
    }
    
      /**
     * Test of canBeInsertFirst method, of class Board.
     */
    @Test
    public void testCanBeInsertFirstFalse() {
        
        System.out.println("canBeInsertFirst False");
        Position[] poses = {new Position(0,0),new Position(1,1)};
        ArrayList<Position> positionsOfPiece = Arrays.asList(poses).stream().collect(toCollection(ArrayList::new));
        Position first = new Position(2,0);
        Board instance = BOARD;
        boolean expResult = false;
        boolean result = instance.canBeInsertFirst(positionsOfPiece, first);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of canBeInsert method, of class Board.
     */
    @Test
    public void testCanBeInsert_ArrayList_String() {
        
        System.out.println("canBeInsert");
        Position[] poses = {new Position(0,0),new Position(1,1)};
        ArrayList<Position> positionsOfPiece = Arrays.asList(poses).stream().collect(toCollection(ArrayList::new));
        String color = "Blue";
        Board instance = BOARD;
        boolean expResult = false;
        boolean result = instance.canBeInsert(positionsOfPiece, color);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of canBeInsert method, of class Board.
     */
    @Test
    public void testCanBeInsert_Piece_Box() {
        
        System.out.println("canBeInsert");
        Piece piece = new Piece(Shape.THREE, Color.BLUE);
        Box box = new Box(new Position(2,2));
        Board instance = BOARD;
        boolean expResult = false;
        boolean result = instance.canBeInsert(piece, box);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of allCornersFree method, of class Board.
     */
    @Test
    public void testAllCornersFree() {
        
        System.out.println("allCornersFree");
        ArrayList<Piece> pieces = new ArrayList<>();
        Board instance = BOARD;
        ArrayList<Box> expResult = new ArrayList<>();
        ArrayList<Box> result = instance.allCornersFree(pieces);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getWidth method, of class Board.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Board instance = BOARD;
        int expResult = 20;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getHeight method, of class Board.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Board instance = BOARD;
        int expResult = 20;
        int result = instance.getHeight();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getGrid method, of class Board.
     */
    @Test
    public void testGetGrid() {
        System.out.println("getGrid not evaluated");
        
    }

    /**
     * Test of toString method, of class Board.
     */
    @Test
    public void testToString() {
        System.out.println("toString not evaluated");
        
    }

}
