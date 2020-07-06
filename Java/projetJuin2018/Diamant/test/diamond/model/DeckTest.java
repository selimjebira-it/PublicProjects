/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49853.diamond.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class DeckTest {

    public DeckTest() {
    }

    /**
     * Test of getTile method, of class Deck.
     */
    @Test
    public void testGetTile() {
        System.out.println("getTile");
        Deck instance = new Deck();
        int expResult = 37;
        instance.getTile();
        assertEquals(expResult, instance.tiles.size());
    }

    /**
     * Test if objects are different and a modification on one trap doesn't
     * change the other equal traps
     */
    @Test
    public void testDiffHazard() {
        System.out.println("differents objects");
        Deck instance = new Deck();
        Hazard tile1;
        tile1 = (Hazard) instance.tiles.get(15);
        tile1.escape();
        Hazard tile2;
        tile2 = (Hazard) instance.tiles.get(16);
        boolean res = tile2.exlorersEscapeReason == false;
        assertTrue(res && tile1.exlorersEscapeReason == true);

    }

    /**
     * Test of random method, of class Deck.
     */
    @Test
    public void testRandom() {
        System.out.println("random");
        int min = 0;
        int max = 0;
        Deck instance = new Deck();
        int expResult = 0;
        int result = instance.random(min, max);
        assertEquals(expResult, result);
    }

    /**
     * Test of putBack method, of class Deck.
     */
    @Test
    public void testPutBack() {
        System.out.println("putBack");
        Tile tile = new Treasure(3);
        Deck instance = new Deck();
        instance.putBack(tile);
        int exp = 39;
        int res = instance.tiles.size();
        assertEquals(exp, res);
    }

    /**
     * Test of putBack method, of class Deck.
     */
    @Test
    public void testPutBack1Relic() {
        System.out.println("putBack");
        Tile tile = new Relic();
        Deck instance = new Deck();
        instance.putBack(tile);
        int exp = 38;
        int res = instance.tiles.size();
        assertEquals(exp, res);
    }

    /**
     * Test of putBack method, of class Deck.
     */
    @Test
    public void testPutBack1Danger() {
        System.out.println("putBack");
        Hazard tile = new Hazard(HazardType.BATTERING_RAM);
        tile.escape();
        Deck instance = new Deck();
        instance.putBack(tile);
        int exp = 38;
        int res = instance.tiles.size();
        assertEquals(exp, res);
    }

    /**
     * Test of putBack method, of class Deck.
     */
    @Test
    public void testPutBack1DangerNoReasonToLeave() {
        System.out.println("putBack");
        Hazard tile = new Hazard(HazardType.BATTERING_RAM);
        Deck instance = new Deck();
        instance.putBack(tile);
        int exp = 39;
        int res = instance.tiles.size();
        assertEquals(exp, res);
    }

}
