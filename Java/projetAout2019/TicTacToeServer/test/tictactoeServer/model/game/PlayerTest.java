/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeServer.model.game;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author selim
 */
public class PlayerTest {

    public PlayerTest() {
    }

    /**
     * Test of getJeton method, of class Player.
     */
    @Test
    public void testGetJeton() {
        User USER1 = new User(2, "marc", 2, 2, 2);
        Player player = new Player(USER1,Jeton.JETON_X);
        assertEquals(player.getJeton(), Jeton.JETON_X);
    }

    /**
     * Test of updatePlayer method, of class Player.
     */
    @Test
    public void testUpdatePlayer() throws GameException {

        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;
        game = new Game(USER1, USER2);
        game.surrender(USER1.getId());
        assertEquals(USER2.getVictories(), 1);
    }
    
    /**
     * Test of updatePlayer method, of class Player.
     */
    @Test
    public void testUpdatePlayerTransitive() throws GameException {

        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;
        game = new Game(USER1, USER2);
        game.surrender(USER1.getId());
        assertEquals(USER2.getVictories(), 1);
    }
    
    /**
     * Test of updatePlayer method, of class Player.
     */
    @Test
    public void testUpdatePlayerInGame() throws GameException {

        User USER1 = new User(2, "marc", 2, 2, 2);
        User USER2 = new User(1, "Bob", 0, 0, 0);
        Game game;
        game = new Game(USER1, USER2);
        game.surrender(USER1.getId());
        assertFalse(USER2.isInGame());
    }

}
