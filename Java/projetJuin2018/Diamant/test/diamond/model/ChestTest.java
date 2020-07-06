/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g49853.diamond.model;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class ChestTest {

    public ChestTest() {
    }

    /**
     * Test of saveBag method, of class Chest.
     */
    @Test
    public void testSaveBag() {
        System.out.println("saveBag");
        Explorer expl = new Explorer("Selim");
        Treasure treasure = new Treasure(5);
        treasure.explore(Arrays.asList(expl));
        Chest instance = new Chest();
        instance.saveBag(expl.getBag());
        assertEquals(5, instance.gems.size());

    }

}
