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
public class UserTest {
    
    static final int ID = 42;
    static final String NAME ="bob";
    static final int VICTORIES = 4;
    static final int DEFEATS = 2;
    static final int DRAWS = 0;
    static final User USER = new User(ID, NAME, VICTORIES, DEFEATS, DRAWS);
    
    public UserTest() {
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        assertEquals(USER.getId(), ID);
        
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        
        assertEquals(USER.getName(), NAME);
    }

    /**
     * Test of getVictories method, of class User.
     */
    @Test
    public void testGetVictories() {
        
        assertEquals(USER.getVictories(), VICTORIES);
    }

    /**
     * Test of getDefeats method, of class User.
     */
    @Test
    public void testGetDefeats() {
        assertEquals(USER.getDefeats(), DEFEATS);
    }

    /**
     * Test of getDraws method, of class User.
     */
    @Test
    public void testGetDraws() {
        assertEquals(USER.getDraws(), DRAWS);
    }

    /**
     * Test of isConnected method, of class User.
     */
    @Test
    public void testIsConnected() {
        assertTrue(USER.isConnected());
    }

    /**
     * Test of isInGame method, of class User.
     */
    @Test
    public void testIsInGame() {
    
        assertFalse(USER.isInGame());
    
    }


    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        assertEquals(USER, new User(ID,NAME,123049495,43,0));
    }
    
}
