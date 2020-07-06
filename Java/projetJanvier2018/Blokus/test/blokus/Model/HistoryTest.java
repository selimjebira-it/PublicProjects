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
public class HistoryTest {
    
    public HistoryTest() {
    }

    /**
     * Test of addText method, of class History.
     */
    @Test
    public void testAddText() {
        
        System.out.println("addText");
        String text = "blabla je test ";
        History instance = new History();
        instance.addText(text);
        
    }

    /**
     * Test of getText method, of class History.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        History instance = new History();
        instance.addText("line 1");
        instance.addText("line2");
        System.out.println(instance.getText());
    }

    /**
     * Test of clear method, of class History.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        History instance = new History();
        instance.addText("line 1");
        instance.addText("line 2");
        instance.clear();
        instance.addText("il ne reste que ça");
        System.out.println(instance.getText());
        
    }
    
}
