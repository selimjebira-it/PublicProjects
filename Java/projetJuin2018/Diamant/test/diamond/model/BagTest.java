package g49853.diamond.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class BagTest {

    /**
     * method that test if the bag is created rightly
     */
    @Test
    public void getNbRubiesCaseNoRuby() {
        System.out.println("GetNbRubies");
        Bag bag = new Bag();
        assertEquals(0, bag.gems.size());
    }

    /**
     * method that test if the incrementation of rubies have been made by the
     * method addGem
     */
    @Test
    public void addGemsOneTime() {
        System.out.println("AddGemsOnetime");
        Bag bag = new Bag();
        bag.addGem(Gem.RUBIS);
        assertEquals(1, bag.getValue());
    }

    /**
     * method that test if the incrementation of rubies have been made by the
     * method addGem (2 times), 1 Ruby & 1 Diamond
     */
    @Test
    public void addGemsTwoTime() {
        System.out.println("AddGems2times");
        Bag bag = new Bag();
        bag.addGem(Gem.RUBIS);
        bag.addGem(Gem.DIAMOND);
        assertEquals(6, bag.getValue());
    }

    /**
     * try the Equality of 2 empty bags
     */
    @Test
    public void equalsTestTrueAfterInit() {
        System.out.println("EqualsTest");
        Bag bag1 = new Bag();
        Bag bag2 = new Bag();
        assertTrue(bag1.equals(bag2));
    }

    /**
     * try the equality of 2 bags with 1 ruby
     */
    @Test
    public void equalsTestTrueAfterAdding() {
        System.out.println("EqualsTestAfterAdding");
        Bag bag1 = new Bag();
        Bag bag2 = new Bag();
        bag1.addGem(Gem.RUBIS);
        bag2.addGem(Gem.RUBIS);
        assertTrue(bag1.equals(bag2));
    }

    /**
     * try the equality of 2 bags, one with one ruby the other with1 diamond
     */
    @Test
    public void equalsTestTrueAfterAddingFalse() {
        System.out.println("EqualsTestAfterAdding");
        Bag bag1 = new Bag();
        Bag bag2 = new Bag();
        bag1.addGem(Gem.RUBIS);
        bag2.addGem(Gem.DIAMOND);
        assertFalse(bag1.equals(bag2));
    }

    /**
     * try the equality of a bag and a string
     */
    @Test
    public void equalsFalseOtherObject() {
        System.out.println("EqualsFalseObj");
        Bag bag1 = new Bag();
        String bag2 = "bag2";
        assertFalse(bag1.equals(bag2));
    }

    /**
     * try the equality of a bag and a null Object
     */
    @Test
    public void equalsFalseNull() {
        System.out.println("EqualsFalseNull");
        Bag bag1 = new Bag();
        assertFalse(bag1.equals(null));
    }

    /**
     * Test of loseContent method, of class Bag.
     */
    @Test
    public void testLoseContent() {
        System.out.println("loseContent");
        Bag instance = new Bag();
        instance.addGem(Gem.DIAMOND);
        instance.loseContent();
        assertEquals(0, instance.getValue());
    }
}
