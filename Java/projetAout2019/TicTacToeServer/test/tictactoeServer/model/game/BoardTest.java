/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeServer.model.game;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class BoardTest {
    
    
    static final Board BOARD = new Board();
    
    public BoardTest() {
    }

    /**
     * Test of reset method, of class Board.
     */
    @Test
    public void testReset() {
        Board board = new Board();
        board.insert(0, 0, Jeton.JETON_X);
        board.reset();
        assertNull(board.getValue(0, 0));
    }

    /**
     * Test of insert method, of class Board.
     */
    @Test
    public void testInsert() {
        BOARD.reset();
        BOARD.insert(0, 0, Jeton.JETON_X);
        assertEquals(BOARD.getValue(0, 0), Jeton.JETON_X);
        
    }

    /**
     * Test of getValue method, of class Board.
     */
    @Test
    public void testGetValue_int_int() {
        BOARD.reset();
        BOARD.insert(0, 0, Jeton.JETON_X);
        assertEquals(BOARD.getValue(0, 0), Jeton.JETON_X);
        
    }

    /**
     * Test of getValue method, of class Board.
     */
    @Test
    public void testGetValue_int() {
        
        BOARD.reset();
        BOARD.insert(0, 0, Jeton.JETON_X);
        BOARD.insert(0, 1, Jeton.JETON_X);
        BOARD.insert(0, 2, Jeton.JETON_X);
        assertEquals(BOARD.getValue(0), Jeton.JETON_X);
    }

    /**
     * Test of isComplete method, of class Board.
     */
    @Test
    public void testIsComplete_int() {
        BOARD.reset();
        BOARD.insert(0, 0, Jeton.JETON_X);
        BOARD.insert(0, 1, Jeton.JETON_X);
        BOARD.insert(0, 2, Jeton.JETON_X);
        assertTrue(BOARD.isComplete(0));
    }
   
    
    /**
     * Test of isComplete method, of class Board.
     */
    @Test
    public void testIsComplete_intBis() {
        BOARD.reset();
        BOARD.insert(0, 0, Jeton.JETON_X);
        BOARD.insert(0,3, Jeton.JETON_X);
        BOARD.insert(0,6, Jeton.JETON_X);
        assertTrue(BOARD.isComplete(0));
    }
    /**
     * Test of isComplete method, of class Board.
     */
    @Test
    public void testIsComplete_intFalse() {
        
        BOARD.reset();
        BOARD.insert(0, 0, Jeton.JETON_X);
        BOARD.insert(0, 5, Jeton.JETON_X);
        BOARD.insert(0, 2, Jeton.JETON_X);
        assertFalse(BOARD.isComplete(0));
    }

    /**
     * Test of isComplete method, of class Board.
     */
    @Test
    public void testIsComplete_0args() {
        
        BOARD.reset();
        BOARD.insert(0, 0, Jeton.JETON_X);
        BOARD.insert(0, 1, Jeton.JETON_X);
        BOARD.insert(0, 2, Jeton.JETON_X);
        BOARD.insert(1, 0, Jeton.JETON_X);
        BOARD.insert(1, 1, Jeton.JETON_X);
        BOARD.insert(1, 2, Jeton.JETON_X);
        BOARD.insert(2, 0, Jeton.JETON_X);
        BOARD.insert(2, 1, Jeton.JETON_X);
        BOARD.insert(2, 2, Jeton.JETON_X);
        assertTrue(BOARD.isComplete());
    }
    /**
     * Test of isComplete method, of class Board.
     */
    @Test
    public void testIsComplete_0argsFalse() {
        
        BOARD.reset();
        BOARD.insert(0, 0, Jeton.JETON_X);
        BOARD.insert(0, 1, Jeton.JETON_X);
        BOARD.insert(0, 2, Jeton.JETON_X);
        BOARD.insert(1, 0, Jeton.JETON_X);
        BOARD.insert(1, 1, Jeton.JETON_X);
        BOARD.insert(1, 2, Jeton.JETON_X);
        BOARD.insert(2, 0, Jeton.JETON_X);
        BOARD.insert(2, 3, Jeton.JETON_X);
        BOARD.insert(2, 2, Jeton.JETON_X);
        assertFalse(BOARD.isComplete());
    }

    /**
     * Test of getResult method, of class Board.
     */
    @Test
    public void testGetResult() {
    }
    
}
