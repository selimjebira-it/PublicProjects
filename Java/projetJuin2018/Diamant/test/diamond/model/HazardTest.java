package g49853.diamond.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class HazardTest {

    public HazardTest() {
    }

    /**
     * Test of getType method, of class Hazard.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Hazard instance = new Hazard(HazardType.BATTERING_RAM);
        HazardType expResult = HazardType.BATTERING_RAM;
        HazardType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of isExlorersEscapeReason method, of class Hazard.
     */
    @Test
    public void testIsExlorersEscapeReason() {
        System.out.println("isExlorersEscapeReason");
        Hazard instance = new Hazard(HazardType.BATTERING_RAM);
        boolean result = instance.isExlorersEscapeReason();
        assertFalse(result);
    }

    /**
     * Test of isExlorersEscapeReason method, of class Hazard.
     */
    @Test
    public void testIsExlorersEscapeReasonTrue() {
        System.out.println("isExlorersEscapeReasonTrue");
        Hazard instance = new Hazard(HazardType.BATTERING_RAM);
        instance.escape();
        boolean result = instance.isExlorersEscapeReason();
        assertTrue(result);
    }

    /**
     * Test of getValue method, of class Hazard.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Hazard instance = new Hazard(HazardType.BATTERING_RAM);
        String expResult = HazardType.BATTERING_RAM.name();
        String result = instance.getValue();
        assertEquals(expResult, result);
    }

    @Test
    public void testContains() {
        System.out.println("testContains");
        Tile instance = new Hazard(HazardType.BATTERING_RAM);
        Hazard instance2 = new Hazard(HazardType.BATTERING_RAM);
        List path = new ArrayList<>();
        path.add(instance);
        assertTrue(path.contains(instance2));
    }
}
