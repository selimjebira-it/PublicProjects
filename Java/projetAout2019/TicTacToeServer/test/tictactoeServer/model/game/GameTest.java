/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeServer.model.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class GameTest {

    public GameTest() {
    }
    
    @Test
    public void testPlayerStateTransitive() throws GameException{
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game = new Game(USER1, USER2);
        assertTrue(USER1.isInGame());
       
    }
    
    @Test
    public void testPlayerStateTransitiveWhenFinished() throws GameException{
    
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game = new Game(USER1, USER2);
        game.surrender(USER1.getId());
        game.getPlayer1().updatePlayer(game);
        assertFalse(USER1.isInGame());
    }

    /**
     * Test of play method, of class Game.
     */
    @Test
    public void testPlay() throws Exception {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game = new Game(USER1, USER2);
        game.play(10);
        game.surrender(USER1.defeats);
        assertEquals(game.getLastPositionPicked(), 10);

    }
    
    /**
     * Test of play method, of class Game.
     */
    @Test
    public void testPlayError() throws Exception {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game = new Game(USER1, USER2);
        game.play(0);
        game.surrender(USER1.defeats);
        assertEquals(game.getLastPositionPicked(), 10);

    }

    /**
     * Test of play method, of class Game.
     */
    @Test(expected = GameException.class)
    public void testPlayExceptionUser() throws Exception {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game = new Game(USER1, USER1);
        game.play(10);

    }

    /**
     * Test of play method, of class Game.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlayExceptionIndex() throws Exception {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game = new Game(USER1, USER2);
        game.play(500);

    }

    /**
     * Test of replay method, of class Game.
     */
    @Test
    public void testReplay() throws Exception {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game = new Game(USER1, USER2);
        game.play(10);
        game.surrender(USER1.getId());
        game.replay();
        assertEquals(-1, game.getLastPositionPicked());
    }

    /**
     * Test of replay method, of class Game.
     */
    @Test(expected = GameException.class)
    public void testReplay_EXCEPTION() throws Exception {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game = new Game(USER1, USER2);
        game.play(10);
        game.replay();
        assertEquals(-1, game.getLastPositionPicked());
    }

    /**
     * Test of surrender method, of class Game.
     */
    @Test
    public void testSurrender() throws Exception {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);

        Game game = new Game(USER1, USER2);
        game.surrender(USER1.getId());
        assertTrue(game.getWinner().getId() == USER2.getId());

    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() throws GameException {

        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;
        game = new Game(USER1, USER2);
        game.play(10);
        assertTrue(game.getCurrentPlayer().getId() == USER2.getId());

    }

    /**
     * Test of getCurrentId method, of class Game.
     */
    @Test
    public void testGetCurrentId() throws GameException {

        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;
        game = new Game(USER1, USER2);
        game.play(10);
        assertTrue(game.getCurrentId() == USER2.getId());
    }

    /**
     * Test of getCount method, of class Game.
     */
    @Test
    public void testGetCount() throws GameException {

        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;

        game = new Game(USER1, USER2);
        game.play(0);
        game.play(4);
        assertEquals(game.getCount(), 2);

    }

    /**
     * Test of getWinner method, of class Game.
     */
    @Test
    public void testGetWinner() throws GameException {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;

        game = new Game(USER1, USER2);
        game.surrender(USER1.getId());

        assertEquals(game.getWinner().getId(), USER2.getId());

    }

    /**
     * Test of getPlayer1 method, of class Game.
     */
    @Test
    public void testGetPlayer1() throws GameException {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;
        game = new Game(USER1, USER2);
        assertEquals(game.getPlayer1().getId(), USER1.getId());
    }

    /**
     * Test of getPlayer2 method, of class Game.
     */
    @Test
    public void testGetPlayer2() throws GameException {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;

        game = new Game(USER1, USER2);
        assertEquals(game.getPlayer2().getId(), USER2.getId());

    }

    /**
     * Test of isFinished method, of class Game.
     */
    @Test
    public void testIsFinished() throws GameException {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;

        game = new Game(USER1, USER2);
        game.surrender(USER1.getId());
        assertTrue(game.isFinished());

    }

    /**
     * Test of isAborted method, of class Game.
     */
    @Test
    public void testIsAborted() throws GameException {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;

        game = new Game(USER1, USER2);
        game.surrender(USER1.getId());
        assertTrue(game.isAborted());

    }

    /**
     * Test of getLastPositionPicked method, of class Game.
     */
    @Test
    public void testGetLastPositionPicked() throws GameException {
        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;
        game = new Game(USER1, USER2);
        game.play(10);
        assertEquals(game.getLastPositionPicked(), 10);
    }

}
