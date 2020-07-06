package g49853.diamond.model;

import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExplorerTest {

    /**
     * try the getter of pseudonymne
     */
    @Test
    public void getPseudonyme() {
        System.out.println("getPseudonyme");
        Explorer explorer = new Explorer("selim");
        assertEquals("selim", explorer.getPseudonyme());
    }

    /**
     * try the getter of the state
     */
    @Test
    public void getStateBeforeTakeDecisionToLeave() {
        System.out.println("StateStatus");
        Explorer explorer = new Explorer("selim");
        assertEquals(State.CAMPING, explorer.getState());
    }

    /**
     * try the getter of the state after a decision to leave the game
     */
    @Test
    public void getStateAftertakeDecisionToLeave() {
        System.out.println("takeDecisionToLeave");
        Explorer explorer = new Explorer("selim");
        explorer.takeDecisionToLeave();
        assertEquals(State.LEAVING, explorer.getState());
    }

    /**
     * try the getter of bag before an add of rubies
     */
    @Test
    public void getBagBeforAddingToBag() {
        System.out.println("getBag");
        Explorer explorer = new Explorer("selim");
        assertEquals(0, explorer.getBag().getValue());
    }

    /**
     * try the getter of a bag after 1 add of rubies
     */
    @Test
    public void addToBag1Time() {
        System.out.println("addTobag1time");
        Explorer explorer = new Explorer("Sdr");
        explorer.startExploration();
        explorer.addToBag(Gem.DIAMOND);
        assertEquals(5, explorer.getBag().getValue());
    }

    /**
     * try the getter of bag after 2 adds of rubies
     */
    @Test
    public void addToBag2Times() {
        System.out.println("AddtoBag2times");
        Explorer explorer = new Explorer("Sdr");
        explorer.startExploration();
        explorer.addToBag(Gem.DIAMOND);
        explorer.addToBag(Gem.RUBIS);
        assertEquals(6, explorer.getBag().getValue());
    }

    @Test
    /**
     * test of startExploration
     */
    public void startExploration() {
        System.out.println("reachCampTrue");
        Explorer e1 = new Explorer("pbt");
        Explorer e2 = new Explorer("sdr");
        e1.startExploration();
        assertTrue(e1.getState() == State.EXPLORING && e2.getState() == State.CAMPING);
    }

    /**
     * Test of reachCamp method, of class Explorer. when he is leaving
     */
    @Test
    public void testReachCampTrue() {
        System.out.println("reachCampTrue");
        Explorer e2 = new Explorer("sdr");
        e2.startExploration();
        e2.reachCamp();
        assertTrue(e2.getState() == State.CAMPING);
    }

    /**
     * Test of getFortune method, of class Explorer.
     */
    @Test
    public void testGetFortune() {
        System.out.println("getFortune");
        Explorer instance = new Explorer("sdr");
        int expResult = 0;
        int result = instance.getFortune();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFortune method, of class Explorer.
     */
    @Test
    public void testGetFortune1Diamond() {
        System.out.println("getFortune1Diamond");
        Explorer instance = new Explorer("sdr");
        instance.startExploration();
        instance.addToBag(Gem.DIAMOND);
        int expResult = 5;
        instance.reachCamp();
        int result = instance.getFortune();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFortune method, of class Explorer.
     */
    @Test
    public void testGetFortune1Gem() {
        System.out.println("getFortune");
        Explorer instance = new Explorer("sdr");
        instance.startExploration();
        Treasure treasure = new Treasure(1);
        treasure.explore(Arrays.asList(instance));
        instance.reachCamp();
        int expResult = 1;
        int result = instance.getFortune();
        assertEquals(expResult, result);
    }

    /**
     * Test of startExploration method, of class Explorer.
     */
    @Test
    public void testStartExploration() {
        System.out.println("startExploration");
        Explorer instance = new Explorer("selim");
        instance.startExploration();
        State exp = State.EXPLORING;
        State res = instance.getState();
        assertEquals(exp, res);
        ;
    }

    /**
     * Test of runAway method, of class Explorer.
     */
    @Test
    public void testRunAway() {
        System.out.println("runAway");
        Explorer instance = new Explorer("selim");
        instance.startExploration();
        instance.addToBag(Gem.DIAMOND);
        instance.runAway();
        boolean res = instance.getBag().getValue() == 0 && instance.getState() == State.CAMPING;
        assertTrue(res);
    }

    /**
     * try the equality between 2 explorers with the same name
     */
    @Test
    public void equalsTrue() {
        System.out.println("EqualsTrue");
        Explorer e1 = new Explorer("mcd");
        Explorer e2 = new Explorer("mcd");
        assertTrue(e1.equals(e2));
    }

    /**
     * try the equality between 2 explorers with different names
     */
    @Test
    public void equalsFalseDifferent() {
        System.out.println("EqualsFalseDifferent");
        Explorer e1 = new Explorer("mcd");
        Explorer e2 = new Explorer("pbt");
        assertFalse(e1.equals(e2));
    }

    /**
     * try the equality between 1 explorer and a string
     */
    @Test
    public void equalsFalseOtherObject() {
        System.out.println("equalsFalseOtherObject");
        Explorer e1 = new Explorer("mcd");
        String e2 = "mcd";
        assertFalse(e1.equals(e2));
    }

    /**
     * try the equality between an explorer and a null object
     */
    @Test
    public void equalsFalseNull() {
        System.out.println("equalsFalseNull");
        Explorer e1 = new Explorer("mcd");
        assertFalse(e1.equals(null));
    }

    /**
     * Test state of poisoned before being poisoned.
     */
    @Test
    public void testPoisonedNoPoisoned() {
        Explorer e1 = new Explorer("selim");
        assertFalse(e1.getPoisoned());
    }

    /**
     * Test state of poisoned when being poisoned.
     */
    @Test
    public void testPoisonedNoRuby() {
        Explorer e1 = new Explorer("selim");
        e1.poisonned();
        assertTrue(e1.getPoisoned());
    }

    /**
     * Test state of poisoned when poisoned and whit ruby.
     */
    @Test
    public void testPoisonedrubies() {
        Explorer e1 = new Explorer("selim");
        e1.addToBag(Gem.RUBIS);
        e1.poisonned();
        assertEquals(0, e1.getBag().getValue());
    }

    /**
     * Test of state of poisoned when poisoned and whit a chest with gems.
     */
    @Test
    public void testPoisonedRubiesInChest() {

        Explorer e1 = new Explorer("selim");
        e1.addToBag(Gem.RUBIS);
        e1.reachCamp();
        e1.poisonned();
        assertEquals(0, e1.getFortune());

    }

    /**
     * Test of state of poisoned.the explorer has to go back to camp.
     */
    @Test
    public void testPoisonedState() {

        Explorer e1 = new Explorer("selim");
        e1.poisonned();
        assertEquals(State.LEAVING, e1.getState());

    }

    /**
     * Test of state of poisoned.the explorer has to go back to camp.
     */
    @Test
    public void testHelpingState() {

        Explorer e1 = new Explorer("selim");
        e1.helping();
        assertEquals(State.LEAVING, e1.getState());

    }

}
