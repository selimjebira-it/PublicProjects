package g49853.diamond.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TreasureTest {

    /**
     * try to construct a Treasure whitout attribut and verify the value of
     * rubies, init rubies and the amount maximum of rubies.
     */
    @Test
    public void contructorWithoutAttribut() {
        System.out.println("ConstructWithoutAtt");
        Treasure treasure = new Treasure();
        int initNbRubies = treasure.getInitNbGems();
        int rubies = Integer.parseInt(treasure.getValue());
        boolean rubiesEqualsInitNbRubies;
        rubiesEqualsInitNbRubies = initNbRubies == rubies;
        boolean inBoundaries = (1 <= rubies && rubies <= 17);
        assertTrue(rubiesEqualsInitNbRubies && inBoundaries);
    }

    /**
     * try to construct a Treasure whit attribut and verify the amount of rubies
     * and init rubies.
     */
    @Test
    public void contructorWithAttribut() {
        System.out.println("ConstructWithAtt");
        Treasure treasure = new Treasure(42);
        int initNbRubies = treasure.getInitNbGems();
        int rubies = Integer.parseInt(treasure.getValue());

        assertTrue(initNbRubies == rubies && rubies == 42);
    }

    /**
     * try to explore a cave if there is no explorer.
     */
    @Test(expected = IllegalArgumentException.class)
    public void exploredByNoExplorer() {
        System.out.println("ExploredByNoExplorer");
        Treasure treasure = new Treasure(5);
        treasure.explore(Arrays.asList());
    }

    /**
     * try to explore a cave if explorers list is null.
     */
    @Test(expected = NullPointerException.class)
    public void exploredByNoExplorerNull() {
        System.out.println("ExploredByNoExplorer");
        Treasure treasure = new Treasure(5);
        treasure.explore(null);
    }

    /**
     * try to explorer whit one explorer
     */
    @Test
    public void exploredBy1Explorer() {
        System.out.println("exploredBy1Explorer");
        Explorer e1 = new Explorer("e1");
        Treasure treasure = new Treasure(4);
        treasure.explore(Arrays.asList(e1));
        assertEquals(0, treasure.getValueOfGems());
    }

    /**
     * try to explore whit twoo explorers
     */
    @Test
    public void exploredBy2Explorers() {
        System.out.println("exploredBy2Explorers");
        Explorer e1 = new Explorer("e1");
        Explorer e2 = new Explorer("e2");
        Treasure treasure = new Treasure(3);
        treasure.explore(Arrays.asList(e1, e2));
        assertEquals(1, treasure.getValueOfGems());
    }

    /**
     * Test of getRubies method, of class Treasure.
     */
    @Test
    public void testGetValue() {
        System.out.println("getRubies");
        int expRes = 3;
        Treasure instance = new Treasure(expRes);
        int result = Integer.parseInt(instance.getValue());
        assertEquals(expRes, result);
    }

    /**
     * Test of restore method, of class Treasure.
     */
    @Test
    public void testRestore() {
        System.out.println("restore");
        Treasure instance = new Treasure(5);
        Explorer e1 = new Explorer("Selim");
        Explorer e2 = new Explorer("Jebira");
        instance.explore(Arrays.asList(e1, e2));
        instance.restore();
        assertEquals(5, instance.getValueOfGems());

    }

    /**
     * Test of getGems method, of class Treasure.
     */
    @Test
    public void testGetGems() {
        System.out.println("getGems");
        Treasure instance = new Treasure(5);
        List<Gem> result = instance.getGems();
        assertEquals(5, result.size());
    }

    /**
     * Test of getInitNbGems method, of class Treasure.
     */
    @Test
    public void testGetInitNbGems() {
        System.out.println("getInitNbGems");
        Treasure instance = new Treasure(7);
        int result = instance.getInitNbGems();
        assertEquals(7, result);
    }

    /**
     * Test of transferGemsFrom method, of class Treasure.
     */
    @Test
    public void testTransferGemsFrom() {
        System.out.println("transferGemsFrom");
        Treasure o = new Treasure(5);
        Treasure instance = new Treasure(5);
        instance.transferGemsFrom(o);
        assertEquals(10, instance.getValueOfGems());
    }

}
