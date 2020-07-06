/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeServer.model.db;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RecapInfo;
import org.junit.Test;
import static org.junit.Assert.*;
import tictactoeServer.model.game.Game;
import tictactoeServer.model.game.GameException;
import tictactoeServer.model.game.User;

/**
 *
 * @author selim
 */
public class DataBaseTest {

    static final DataBase DATABASE = new DataBase();

    public DataBaseTest() {
        DATABASE.connect();
    }

    /**
     * Test of createProfile method, of class DataBase.
     */
    @Test
    public void testCreateProfile() {

        String name = "SelimTest" + (Math.random() * 100000);
        String mdp = "SelimTest";
        assertTrue(DATABASE.createProfile(name, mdp));

    }

    /**
     * Test of createGame method, of class DataBase.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateGame() {

        int player1 = 42;
        int player2 = 42;
        int result = 42;
        assertTrue(DATABASE.createGame(player1, player2, result));

    }

    /**
     * Test of createGame method, of class DataBase.
     */
    @Test
    public void testCreateGameOkValue() {

        int player1 = 42;
        int player2 = 21;
        int result = 42;
        assertTrue(DATABASE.createGame(player1, player2, result)); //executed once

    }

    /**
     * Test of readProfile method, of class DataBase.
     */
    @Test
    public void testReadProfile() {

        String name = "bob";
        String mdp = "bob";
        assertNotNull(DATABASE.readProfile(name, mdp));
    }

    /**
     * Test of retrieveGames method, of class DataBase.
     */
    @Test
    public void testRetrieveGames() {
        assertNotNull(DATABASE.retrieveGames(42));
    }

    @Test
    public void testRegisterGame() {
        Game game;
        try {
            game = new Game(new User(27, "josh", 3, 3, 3), new User(24, "bob", 10, 15, 30));
            assertTrue(DATABASE.updateProfile(game.getPlayer1()));
        } catch (GameException ex) {
            Logger.getLogger(DataBaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Test
    public void testGetAllRecap(){
    
        List<RecapInfo> recaps = DATABASE.getAllRecap();
        assertFalse(recaps.isEmpty());
    }

}
