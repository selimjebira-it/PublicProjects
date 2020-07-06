package g49853.diamond.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class GemTest {

    public GemTest() {
    }

    /**
     * Test of getValue method, of class Gem.
     */
    @Test
    public void testGetValueDiams() {
        System.out.println("getValue");
        Gem instance = Gem.DIAMOND;
        int expResult = 5;
        int result = instance.getValue();
        assertEquals(expResult, result);

    }

    /**
     * Test of getValue method, of class Gem.
     */
    @Test
    public void testGetValueRubies() {
        System.out.println("getValue");
        Gem instance = Gem.RUBIS;
        int expResult = 1;
        int result = instance.getValue();
        assertEquals(expResult, result);

    }

}
