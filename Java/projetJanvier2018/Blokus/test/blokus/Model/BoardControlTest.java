/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import blokus.Enum.Color;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class BoardControlTest {
    static final String NAME = "Selim";
    static final Color COLOR = Color.RED;
    static final Player PLAYER  = new Player(NAME, COLOR,1);
    static final Piece PIECE = PLAYER.getPiece(0);
    static final Position POSITION = new Position(0, 0);
    static final BoardControl BOARD = new BoardControl(20, 20);
    
    public BoardControlTest() {
    }

    /**
     * Test of insert method, of class BoardControl.
     */
    @Test
    public void testInsert() throws Exception {
        
        System.out.println("insert");
        Player player = PLAYER;
        Piece piece = PIECE;
        Position pos = POSITION;
        BoardControl instance =  BOARD;
        instance.insert(player, piece, pos);
        
    }

    /**
     * Test of reset method, of class BoardControl.
     */
    @Test
    public void testReset() {
        
        System.out.println("reset");
        BoardControl instance = BOARD;
        instance.reset();
        assertEquals(BOARD.getBox(POSITION).getColor(), "WHITE");
    }

    /**
     * Test of canPlay method, of class BoardControl.
     */
    @Test
    public void testCanPlay() {
        
        System.out.println("canPlay");
        Player player = PLAYER;
        BoardControl instance = BOARD;
        boolean expResult = true;
        boolean result = instance.canPlay(player);
        assertEquals(expResult, result);
     
    }
    
}
