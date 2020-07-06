package g49853.diamond.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class HazardTypeTest {

    public HazardTypeTest() {
    }

    /**
     * Test of getValue method, of class HazardType.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        HazardType instance = HazardType.BATTERING_RAM;
        String expResult = "Battering Ram";
        String result = instance.getValue();
        assertEquals(expResult, result);

    }

}
