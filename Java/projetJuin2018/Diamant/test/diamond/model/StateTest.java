package g49853.diamond.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class StateTest {

    public StateTest() {
    }

    /**
     * Test of getValue method, of class State.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        State instance = State.CAMPING;
        String expResult = "is camping";
        String result = instance.getValue();
        assertEquals(expResult, result);
    }

}
