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
public class PlayerTest {

    static final Color COLOR = Color.RED;
    static final String NAME = "Sélim";
    static final Player PLAYER = new Player(NAME, COLOR,1);

    public PlayerTest() {
    }

    /**
     * Test of setCurrentPlayer method, of class Player.
     */
    @Test
    public void testSetCurrentPlayer() {
        
        System.out.println("setCurrentPlayer");
        boolean currentPlayer = true;
        Player instance = PLAYER;
        instance.setCurrentPlayer(currentPlayer);
        assertTrue(PLAYER.isCurrentPlayer());
    }

    /**
     * Test of updateScore method, of class Player.
     */
    @Test
    public void testUpdateScore() {
        
        System.out.println("updateScore");
        Player instance = PLAYER;
        instance.updateScore();
        assertEquals(PLAYER.getScore(), 0);
    }

    /**
     * Test of enterGame method, of class Player.
     */
    @Test
    public void testEnterGame() {
        System.out.println("enterGame");
        Player instance = PLAYER;
        instance.enterGame();
        assertTrue(PLAYER.isInGame());
    }

    /**
     * Test of leaveGame method, of class Player.
     */
    @Test
    public void testLeaveGame() {
        System.out.println("leaveGame");
        Player instance = new Player(NAME, COLOR,1);
        instance.leaveGame();
        assertFalse(instance.isInGame());
        
    }

    /**
     * Test of getPiece method, of class Player.
     */
    @Test
    public void testGetPiece() {
        System.out.println("getPiece");
        int index = 0;
        Player instance = PLAYER;
        Piece expResult = new Piece(Shape.ONE, COLOR);
        Piece result = instance.getPiece(index);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getActivePieces method, of class Player.
     */
    @Test
    public void testGetActivePieces() {
        System.out.println("getActivePieces");
        Player instance = PLAYER;
        int expResult =0;
        ArrayList<Piece> result = instance.getActivePieces();
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getInactivePieces method, of class Player.
     */
    @Test
    public void testGetInactivePieces() {
        System.out.println("getInactivePieces");
        Player instance = PLAYER;
        int expResult = Shape.values().length;
        ArrayList<Piece> result = instance.getInactivePieces();
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getBag method, of class Player.
     */
    @Test
    public void testGetBag() {
        System.out.println("getBag");
        Player instance = PLAYER;
        ArrayList<Piece> expResult = Arrays.asList(Shape.values()).stream().map(shape->{return new Piece(shape, COLOR);}).collect(toCollection(ArrayList::new));
        ArrayList<Piece> result = instance.getBag();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getNbPiecesLeft method, of class Player.
     */
    @Test
    public void testGetNbPiecesLeft() {
        System.out.println("getNbPiecesLeft");
        Player instance = PLAYER;
        int expResult = Shape.values().length;
        int result = instance.getNbPiecesLeft();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getScore method, of class Player.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Player instance = PLAYER;
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player instance =PLAYER;
        String expResult = NAME;
        String result = instance.getName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getColor method, of class Player.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Player instance = PLAYER;
        String expResult = COLOR.toString();
        String result = instance.getColor();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isInGame method, of class Player.
     */
    @Test
    public void testIsInGame() {
        System.out.println("isInGame");
        Player instance =new Player(NAME, COLOR,1);
        boolean expResult = false;
        boolean result = instance.isInGame();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isCurrentPlayer method, of class Player.
     */
    @Test
    public void testIsCurrentPlayer() {
        System.out.println("isCurrentPlayer");
        Player instance = new Player(NAME, COLOR,1);
        boolean expResult = false;
        boolean result = instance.isCurrentPlayer();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of hasFinished method, of class Player.
     */
    @Test
    public void testHasFinished() {
        System.out.println("hasFinished");
        Player instance = new Player(NAME, COLOR,1);
        instance.getBag().stream().forEach(piece->{piece.setFirst(new Position(0, 0));});
        boolean expResult = true;
        boolean result = instance.hasFinished();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getFirstMove method, of class Player.
     */
    @Test
    public void testGetFirstMove() {
        System.out.println("getFirstMove");
        Player instance = PLAYER;
        Position expResult = Color.RED.getFirstMove();
        Position result = instance.getFirstMove();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        System.out.println("toString not evaluated");
        
    }

}
