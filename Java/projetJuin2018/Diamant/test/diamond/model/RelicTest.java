package g49853.diamond.model;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class RelicTest {

    public RelicTest() {
    }

    /**
     * Test of canBeTaken method, of class Relic.
     */
    @Test
    public void testCanBeTaken() {
        System.out.println("canBeTaken");
        Explorer expl = new Explorer("selim");
        expl.takeDecisionToLeave();
        List<Explorer> explorers = Arrays.asList(expl);
        Relic instance = new Relic();
        boolean result = instance.canBeTaken(explorers);
        assertTrue(result);
    }

    /**
     * Test of convertGameValue method, of class Relic.
     */
    @Test
    public void testConvertGameValue() {
        System.out.println("convertGameValue1Diam");
        int nbTakenRelics = 0;
        Relic instance = new Relic();
        instance.convertGameValue(nbTakenRelics);
        assertEquals(1, instance.getValueInDiamonds());
    }

    /**
     * Test of convertGameValue method, of class Relic.
     */
    @Test
    public void testConvertGameValue3Relics() {
        System.out.println("convertGameValue2Diams");
        int nbTakenRelics = 3;
        Relic instance = new Relic();
        instance.convertGameValue(nbTakenRelics);
        assertEquals(2, instance.getValueInDiamonds());
    }

    /**
     * Test of explore method, of class Relic.
     */
    @Test
    public void testExplore() {
        System.out.println("explore");
        Explorer expl = new Explorer("Selim");
        expl.takeDecisionToLeave();
        List<Explorer> explorers = Arrays.asList(expl);
        Relic instance = new Relic();
        instance.explore(explorers);
        expl.reachCamp();
        assertEquals(5, expl.getFortune());

    }

    /**
     * Test of explore method, of class Relic.
     */
    @Test
    public void testExplore2Diams() {
        System.out.println("explore");
        Explorer expl = new Explorer("Selim");
        expl.takeDecisionToLeave();
        List<Explorer> explorers = Arrays.asList(expl);
        Relic instance = new Relic();
        instance.convertGameValue(3);
        instance.explore(explorers);
        expl.reachCamp();
        assertEquals(10, expl.getFortune());

    }

    /**
     * Test of getValue method, of class Relic.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Relic instance = new Relic();
        String expResult = "Relic";
        String result = instance.getValue();
        assertEquals(expResult, result);
    }

}
