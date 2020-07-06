/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author selim
 */
public class PositionTest {

    static final int X = 2;
    static final int Y = 3;
    static final Position POSITION = new Position(X, Y);

    public PositionTest() {
    }

    /**
     * Test of getX method, of class Position.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Position instance = POSITION;
        int expResult = 2;
        int result = instance.getX();
        assertEquals(expResult, result);

    }

    /**
     * Test of getY method, of class Position.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Position instance = POSITION;
        int expResult = 3;
        int result = instance.getY();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Position.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        //not evaluated
    }

    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = new Position(2, 3);
        Position instance = POSITION;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEqualsNull() {

        System.out.println("equals");
        Object obj = null;
        Position instance = POSITION;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Position.
     */
    @Test
    public void testEqualsFalse() {

        System.out.println("equals");
        Object obj = new Position(1, 1);
        Position instance = POSITION;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Position.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Position instance = POSITION;
        String expResult = "(2,3)";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

}
