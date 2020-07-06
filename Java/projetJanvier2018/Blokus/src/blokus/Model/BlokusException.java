/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

/**
 *
 * @author selim
 */
public class BlokusException extends Exception {
    
    private static final String DEFAULT = "BLOKUS EXCEPTION AS OCCURED :";
    private final String msg;
    
    public BlokusException() {
        msg = DEFAULT;
    }
    
    public BlokusException(String msg){
        this.msg = DEFAULT + msg;
    }
    
    @Override
    public String getMessage(){
        return msg;
    }
    
}
