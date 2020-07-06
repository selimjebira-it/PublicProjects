/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import blokus.Enum.Color;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class GameTest {
    
    final static ArrayList<Player> PLAYERS
            = new ArrayList<>(Arrays.asList(new Player[]{new Player("Player1", Color.RED,1),
        new Player("Player2", Color.BLUE,2), new Player("Player3", Color.YELLOW,3), new Player("Player4", Color.GREEN,4)}));

    
    public GameTest() {
    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() {
        
        System.out.println("getCurrentPlayer");
        Game instance = new Game();
        instance.newGame(PLAYERS);
        Player expResult = PLAYERS.get(0);
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of newGame method, of class Game.
     */
    @Test
    public void testNewGame() {
        System.out.println("newGame");
        ArrayList<Player> players = PLAYERS;
        Game instance = new Game();
        instance.newGame(players);
        
    }

    /**
     * Test of getActivePlayers method, of class Game.
     */
    @Test
    public void testGetActivePlayers() {
        
        System.out.println("getActivePlayers");
        Game instance = new Game();
        
        ArrayList<Player> result = instance.getActivePlayers();
        assertTrue(result.isEmpty());
    }

    /**
     * Test of getPlayers method, of class Game.
     */
    @Test
    public void testGetPlayers() {
        
        System.out.println("getPlayers");
        Game instance = new Game();
        
        ArrayList<Player> result = instance.getPlayers();
        assertEquals(0, result.size());
        
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOver() {
        
        System.out.println("isOver");
        Game instance = new Game();
        boolean expResult = true;
        boolean result = instance.isOver();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of play method, of class Game.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        
        int indexPiece = 0;
        int x = 0;
        int y = 0;
        Game instance = new Game();
        instance.newGame(PLAYERS);
        instance.play(indexPiece, x, y);
        
    }

    /**
     * Test of skip method, of class Game.
     */
    @Test
    public void testSkip() {
        System.out.println("skip");
        Game instance = new Game();
        instance.newGame(PLAYERS);
        instance.skip();
        assertTrue(instance.getPlayers().get(1).isCurrentPlayer());
        
    }

    /**
     * Test of leave method, of class Game.
     */
    @Test
    public void testLeave() {
        System.out.println("leave");
        Game instance = new Game();
        instance.newGame(PLAYERS);
        instance.leave();
        assertFalse(instance.getPlayers().get(0).isInGame());
       
    }

    /**
     * Test of mirorPiece method, of class Game.
     */
    @Test
    public void testMirorPiece() {
        
        System.out.println("mirorPiece");
        int index = 0;
        Game instance = new Game();
        instance.newGame(PLAYERS);
        instance.mirorPiece(index);
       
    }

    /**
     * Test of reversePiece method, of class Game.
     */
    @Test
    public void testReversePiece() {
        System.out.println("reversePiece");
        int index = 0;
        Game instance = new Game();
        instance.newGame(PLAYERS);
        instance.reversePiece(index);
    }

    /**
     * Test of mirorReversePiece method, of class Game.
     */
    @Test
    public void testMirorReversePiece() {
        System.out.println("mirorReversePiece");
        int index = 0;
        Game instance = new Game();
        instance.newGame(PLAYERS);
        instance.mirorReversePiece(index);
       
    }

    /**
     * Test of isOpen method, of class Game.
     */
    @Test
    public void testIsOpen() {
        System.out.println("isOpen");
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.isOpen();
        assertEquals(expResult, result);

    }

    /**
     * Test of getIndexOfCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetIndexOfCurrentPlayer() {
        System.out.println("getIndexOfCurrentPlayer");
        Game instance = new Game();
        instance.newGame(PLAYERS);
        int expResult = 0;
        int result = instance.getIndexOfCurrentPlayer();
        assertEquals(expResult, result);
       
    }
    
}
