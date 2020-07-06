/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus;

import blokus.Enum.Color;
import blokus.Enum.Shape;
import blokus.Model.Piece;

/**
 *
 * @author selim
 */
public class Blokus {

    public static void main(String[] args) {
        Piece piece = new Piece(Shape.THREE, Color.BLUE);
        System.out.println(piece.getColor());
    }
    
    
}
