/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import blokus.Enum.Color;
import blokus.Enum.Shape;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class PieceTest {
    
    static final Shape SHAPE = Shape.ONE;
    static final Color COLOR = Color.BLUE;
    static final Position POSITION = new Position(0, 0);
    static final Piece PIECE = new Piece(SHAPE,COLOR);
    
    public PieceTest() {
    }

    /**
     * Test of reset method, of class Piece.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Piece instance = PIECE;
        instance.reset();
        
    }

    /**
     * Test of getPositionsWithFirst method, of class Piece.
     */
    @Test
    public void testGetPositionsWithFirst() {
        
        System.out.println("getPositionsWithFirst");
        Position position = POSITION;
        Piece instance = PIECE;
        ArrayList<Position> expResult = new ArrayList<>();
        expResult.add(position);
        ArrayList<Position> result = instance.getPositionsWithFirst(position);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of miror method, of class Piece.
     */
    @Test
    public void testMiror() {
        System.out.println("miror");
        Piece instance = PIECE;
        instance.miror();
        
    }

    /**
     * Test of reverse method, of class Piece.
     */
    @Test
    public void testReverse() {
        System.out.println("reverse");
        Piece instance = PIECE;
        instance.reverse();
     
    }

    /**
     * Test of mirorReverse method, of class Piece.
     */
    @Test
    public void testMirorReverse() {
        System.out.println("mirorReverse");
        Piece instance = PIECE;
        instance.mirorReverse();
        
    }

    /**
     * Test of setFirst method, of class Piece.
     */
    @Test
    public void testSetFirst() {
        
        System.out.println("setFirst");
        Position first = POSITION;
        Piece instance = PIECE;
        instance.setFirst(first);
        
    }

    /**
     * Test of getFirst method, of class Piece.
     */
    @Test
    public void testGetFirst() {
        
        System.out.println("getFirst");
        Piece instance = PIECE;
        Position expResult = null;
        Position result = instance.getFirst();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPositions method, of class Piece.
     */
    @Test
    public void testGetPositions() {
        System.out.println("getPositions");
        Piece instance =PIECE;
        ArrayList<Position> expResult = new ArrayList<>();
        expResult.add(POSITION);
        ArrayList<Position> result = instance.getPositions();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getColor method, of class Piece.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Piece instance = PIECE;
        String expResult = "BLUE";
        String result = instance.getColor();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isPlayed method, of class Piece.
     */
    @Test
    public void testIsPlayed() {
        
        System.out.println("isPlayed");
        Piece instance = PIECE;
        boolean expResult = false;
        boolean result = instance.isPlayed();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isNotPlayed method, of class Piece.
     */
    @Test
    public void testIsNotPlayed() {
        System.out.println("isNotPlayed");
        Piece instance = PIECE;
        boolean expResult =true;
        boolean result = instance.isNotPlayed();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPoints method, of class Piece.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        Piece instance = PIECE;
        int expResult = 1;
        int result = instance.getPoints();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of toString method, of class Piece.
     */
    @Test
    public void testToString() {
        System.out.println("toString not evaluated");
        
    }
    
}
