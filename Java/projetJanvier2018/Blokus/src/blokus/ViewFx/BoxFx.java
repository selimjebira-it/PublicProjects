/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.ViewFx;

import blokus.Interface.Observable;
import blokus.Interface.Observer;
import blokus.Model.Box;
import blokus.Model.Position;
import javafx.scene.paint.Paint;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author selim
 */
public class BoxFx implements Observer {

    private final Box box;
    private final Rectangle rectangle;
    private static final int RECTANGLE_SIZE = 27;

    private Paint color;

    public BoxFx(Box box) {

        this.box = box;
        color = Paint.valueOf(box.getColor());
        this.rectangle = new Rectangle(RECTANGLE_SIZE, RECTANGLE_SIZE, color);
        box.addObs(this);
    }

    @Override
    public void update() {

        color = Paint.valueOf(box.getColor());
        rectangle.setFill(color);
    }

    public Position getPosition() {

        return box.getPosition();
    }

    public Rectangle getRectangle() {

        return rectangle;
    }
    
    public Paint getColor(){
        return color;
    }

}
