package g49853.diamond.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class CaveTest {

    public CaveTest() {
    }

    /**
     * Test of getDeck method, of class Cave.
     */
    @Test
    public void testGetDeck() {
        System.out.println("getDeck");
        Cave instance = new Cave();
        instance.openNewEntrance();
        int expResult = 38;
        int result = instance.getDeck().tiles.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNbExploredEntrance method, of class Cave.
     */
    @Test
    public void testGetNbExploredEntranceNone() {
        System.out.println("getNbExploredEntrance");
        Cave instance = new Cave();
        int expResult = 0;
        int result = instance.getNbExploredEntrance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNbExploredEntrance method with one entrance, of class Cave.
     */
    @Test
    public void testGetNbExploredEntranceOne() {
        System.out.println("getNbExploredEntrance");
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.lockOutCurrentEntrance();
        int expResult = 1;
        int result = instance.getNbExploredEntrance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentEntrance method, of class Cave.
     */
    @Test(expected = GameException.class)
    public void testGetCurrentEntrance() {
        System.out.println("getCurrentEntrance");
        Cave instance = new Cave();
        instance.openNewEntrance();
        CaveEntrance instance1 = instance.getCurrentEntrance();
        CaveEntrance instance2 = new CaveEntrance(instance);
        assertTrue(instance2.equals(instance1));

    }

    /**
     * Test of hasNewEntranceToExplore method with 5 entrances, of class Cave.
     */
    @Test
    public void testHasNewEntranceToExploreFalse() {
        System.out.println("hasNewEntranceToExploreFalse");
        Cave instance = new Cave();
        for (int x = 0; x < 5; x++) {
            instance.openNewEntrance();
            instance.lockOutCurrentEntrance();
        }
        assertFalse(instance.hasNewEntranceToExplore());
    }

    /**
     * Test of hasNewEntranceToExplore method without any entrance , of class
     * Cave.
     */
    @Test
    public void testHasNewEntranceToExploreTrue() {
        System.out.println("hasNewEntranceToExploreTrue");
        Cave instance = new Cave();
        assertTrue(instance.hasNewEntranceToExplore());
    }

    /**
     * Test of openNewEntrance method, of class Cave.
     */
    @Test
    public void testOpenNewEntrance() {
        System.out.println("openNewEntrance");
        Cave instance = new Cave();
        instance.openNewEntrance();
        assertFalse(instance.getCurrentEntrance().isLockedOut());
    }

    /**
     * Test of lockOutCurrentEntrance method, of class Cave.
     */
    @Test
    public void testLockOutCurrentEntrance() {
        System.out.println("lockOutCurrentEntrance");
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.lockOutCurrentEntrance();
        assertTrue(instance.getCurrentEntrance().isLockedOut());
    }

    /**
     * Test of isLastEntranceUnsafe method, of class Cave.
     */
    @Test
    public void testIsLastEntranceUnsafe() {
        System.out.println("isLastEntranceUnsafe");
        Cave instance = new Cave();
        instance.openNewEntrance();
        assertFalse(instance.isLastEntranceUnsafe());

    }

    /**
     * Test of isLastEntranceUnsafe method, of class Cave.
     */
    @Test
    public void testIsLastEntranceUnsafeTrue() {
        System.out.println("isLastEntranceUnsafeTrue");
        Cave instance = new Cave();
        instance.openNewEntrance();
        instance.getCurrentEntrance().setStateToUnsafe();
        assertTrue(instance.isLastEntranceUnsafe());

    }

    /**
     * Test of getNbTakenRelics method, of class Cave.
     */
    @Test
    public void testGetNbTakenRelics() {
        System.out.println("getNbTakenRelics");
        Cave instance = new Cave();
        int expResult = 0;
        int result = instance.getNbTakenRelics();
        assertEquals(expResult, result);
    }

    /**
     * Test of incrementNbTakenRelics method, of class Cave.
     */
    @Test
    public void testIncrementNbTakenRelics() {
        System.out.println("incrementNbTakenRelics");
        Cave instance = new Cave();
        int exp = 1;
        instance.incrementNbTakenRelics();
        assertEquals(exp, instance.getNbTakenRelics());

    }
}
