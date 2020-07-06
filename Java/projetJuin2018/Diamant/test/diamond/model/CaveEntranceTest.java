package g49853.diamond.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CaveEntranceTest {

    /**
     * try the equality of the contents of the bags which are carry by
     * explorers.
     */
    @Test
    public void discoverNewTreasureSameSharing() {
        System.out.println("discoverNewTreasure");
        Cave instance = new Cave();
        Explorer e1 = new Explorer("e1");
        Explorer e2 = new Explorer("e2");
        instance.openNewEntrance();
        instance.getCurrentEntrance().discoverNewTile(Arrays.asList(e1, e2));
        assertTrue(e1.getBag().equals(e2.getBag()));
    }

    /**
     * Test of getLastDiscoveredTreasure method, of class CaveEntrance.
     */
    @Test(expected = GameException.class)
    public void testGetLastDiscoveredTile() {
        System.out.println("getLastDiscoveredTreasure");
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.getCurrentEntrance().getLastDiscoveredTile();
    }

    /**
     * Test of getLastDiscoveredTreasure method, of class CaveEntrance.
     */
    @Test
    public void testGetLastDiscoveredTreasureTile5() {
        System.out.println("getLastDiscoveredTreasure");
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.getCurrentEntrance().setLastDiscoveredTile(new Treasure(5));
        assertEquals(5, Integer.parseInt(instance.getCurrentEntrance().getLastDiscoveredTile().getValue()));
    }

    /**
     * Test of getPath method, of class CaveEntrance.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        Cave instance = new Cave();
        instance.openNewEntrance();
        int expResult = 0;
        int result = instance.getCurrentEntrance().getPath().size();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPath method, of class CaveEntrance.
     */
    @Test
    public void testGetPath1Tile() {
        System.out.println("getPath1Tile");
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.getCurrentEntrance().addTileToPath(new Treasure(1));
        int expResult = 1;
        int result = instance.getCurrentEntrance().getPath().size();
        assertEquals(expResult, result);

    }

    /**
     * Test of addTreasureToPath method, of class CaveEntrance.
     */
    @Test
    public void testAddTreasureToPath2Times() {
        System.out.println("addTreasureToPath");
        Treasure treasure = new Treasure(5);
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.getCurrentEntrance().addTileToPath(treasure);
        instance.getCurrentEntrance().addTileToPath(treasure);
        int expRes = 2;
        int result = instance.getCurrentEntrance().getPath().size();
        assertEquals(expRes, result);
    }

    /**
     * test of getLockOut method of class CaveEntrance.
     */
    @Test
    public void testGetLockOutFalse() {
        System.out.println("getLockOutFalse");
        Cave instance = new Cave();
        instance.openNewEntrance();
        assertFalse(instance.getCurrentEntrance().isLockedOut());
    }

    /**
     * test of LockOut method of class CaveEntrance when we lock the last
     * entrance.
     */
    @Test
    public void testLockOutTrue() {
        System.out.println("LockOutTrue");
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.getCurrentEntrance().lockOut();
        assertTrue(instance.getCurrentEntrance().isLockedOut());
    }

    /**
     * Test of discoverNewTile method, of class CaveEntrance.
     */
    @Test
    public void testDiscoverNewTile() {
        System.out.println("discoverNewTile");
        Cave instance = new Cave();
        instance.openNewEntrance();
        Explorer e1 = new Explorer("e1");
        Explorer e2 = new Explorer("e2");
        instance.getCurrentEntrance().discoverNewTile(Arrays.asList(e1, e2));
        int exp = 1;
        int res = instance.getCurrentEntrance().getPath().size();
        assertEquals(exp, res);
    }

    /**
     * Test of equals method, of class CaveEntrance.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Treasure treasure = new Treasure(6);
        Cave other = new Cave();
        other.openNewEntrance();
        other.getCurrentEntrance().setLastDiscoveredTile(treasure);
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.getCurrentEntrance().setLastDiscoveredTile(treasure);
        boolean result = instance.getCurrentEntrance().equals(other.getCurrentEntrance());
        assertTrue(result);

    }

    /**
     * Test of returnToCamp method, of class CaveEntrance.
     */
    @Test
    public void testReturnToCamp() {
        System.out.println("returnToCamp");
        Explorer e1 = new Explorer("e1");
        Explorer e2 = new Explorer("e2");
        List<Explorer> explorers = Arrays.asList(e1, e2);
        Cave instance = new Cave();
        instance.getCurrentEntrance().returnToCamp(explorers);
        State exp = State.CAMPING;
        State res = explorers.get(0).getState();
        assertEquals(exp, res);
    }

    /**
     * Test of setStateToUnsafe method, of class CaveEntrance.
     */
    @Test
    public void testSetStateToUnsafeFalse() {
        System.out.println("setStateToUnsafeFalse");
        Cave instance = new Cave();
        instance.openNewEntrance();
        assertFalse(instance.getCurrentEntrance().unSafe);

    }

    /**
     * Test of setStateToUnsafe method, of class CaveEntrance.
     */
    @Test
    public void testSetStateToUnsafeTrue() {
        System.out.println("setStateToUnsafeTrue");
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.getCurrentEntrance().setStateToUnsafe();
        assertTrue(instance.getCurrentEntrance().unSafe);

    }

    /**
     * Test of makeLastTileExplored method, of class CaveEntrance.
     */
    @Test
    public void testMakeLastTileExplored() {
        System.out.println("makeLastTileExplored");
        Cave instance = new Cave();
        Explorer e1 = new Explorer("Selim");
        Explorer e2 = new Explorer("Martin");
        List<Explorer> explorers = Arrays.asList(e1, e2);
        instance.openNewEntrance();
        Tile TreasureToAdd = new Treasure(5);
        TreasureToAdd.explore(explorers);
        instance.getCurrentEntrance().setFirstTreasureTile((Treasure) TreasureToAdd);
        instance.getCurrentEntrance().setTreasureFound(true);
        instance.getCurrentEntrance().addTileToPath(TreasureToAdd);
        Tile TreasureToAdd2 = new Treasure(7);
        TreasureToAdd2.explore(explorers);
        instance.getCurrentEntrance().setLastDiscoveredTile(TreasureToAdd2);
        instance.getCurrentEntrance().makeLastTileExplored();
        assertEquals(2, instance.getCurrentEntrance().getFirstTreasureTile().getValueOfGems());
    }
}
