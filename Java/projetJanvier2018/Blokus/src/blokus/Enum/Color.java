package blokus.Enum;

import blokus.Model.Position;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author selim
 */
public enum Color implements Serializable{
    
    RED(new Position(0, 0)),
    BLUE(new Position(1,0)),
    GREEN(new Position(0,1)),
    YELLOW(new Position(1,1));      ;
    
    
    private final Position firstMove;

    private Color(Position firstMove) {
        this.firstMove = firstMove;
    }

    public Position getFirstMove() {
        return firstMove;
    }

    @Override
    public String toString() {
        return this.name();
    }
    
    

}
