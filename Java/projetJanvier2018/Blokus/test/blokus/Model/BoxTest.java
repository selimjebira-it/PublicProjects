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
public class BoxTest {

    static final Position POSITION = new Position(3, 2);
    static final String COLOR = "Blue";
    static final Box BOX = new Box(POSITION);

    public BoxTest() {
    }

    /**
     * Test of setColor method, of class Box.
     */
    @Test
    public void testSetColor() {
        
        System.out.println("setColor");
        String color = COLOR;
        Box instance = BOX;
        instance.setColor(color);
        assertEquals(instance.getColor(), color);

    }

    /**
     * Test of reset method, of class Box.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Box instance = BOX;
        String color = COLOR;
        instance.setColor(color);
        instance.reset();
        assertEquals(instance.getColor(), "WHITE");

    }

    /**
     * Test of getPosition method, of class Box.
     */
    @Test
    public void testGetPosition() {

        System.out.println("getPosition");
        Box instance = BOX;
        Position expResult = POSITION;
        Position result = instance.getPosition();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDefaultColor method, of class Box.
     */
    @Test
    public void testGetDefaultColor() {
        System.out.println("getDefaultColor");
        Box instance = BOX;
        String expResult = "WHITE";
        String result = instance.getDefaultColor();
        assertEquals(expResult, result);

    }

    /**
     * Test of getColor method, of class Box.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Box instance = BOX;
        String expResult = "WHITE";
        String result = instance.getColor();
        assertEquals(expResult, result);

    }

    /**
     * Test of isFree method, of class Box.
     */
    @Test
    public void testIsFree() {
        
        System.out.println("isFree");
        Box instance = BOX;
        boolean expResult = true;
        boolean result = instance.isFree();
        assertEquals(expResult, result);

    }

    /**
     * Test of isFree method, of class Box.
     */
    @Test
    public void testIsFreeFalse() {
        
        System.out.println("isFree false");
        Box instance = new Box(POSITION);
        instance.setColor(COLOR);
        boolean expResult = false;
        boolean result = instance.isFree();
        assertEquals(expResult, result);

    }

    /**
     * Test of hashCode method, of class Box.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        //not Evaluated
    }

    /**
     * Test of equals method, of class Box.
     */
    @Test
    public void testEquals() {
        
        System.out.println("equals");
        Object obj =  new Box(new Position(3, 2));
        Box instance = BOX;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of equals method, of class Box.
     */
    @Test
    public void testEqualsFalse() {
        
        System.out.println("equals false");
        Object obj =  new Box(new Position(0, 0));
        Box instance = BOX;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of equals method, of class Box.
     */
    @Test
    public void testEqualsNull() {
        
        System.out.println("equals null");
        Object obj =  null;
        Box instance = BOX;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class Box.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Box instance = BOX;
        String expResult = " ";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

}
