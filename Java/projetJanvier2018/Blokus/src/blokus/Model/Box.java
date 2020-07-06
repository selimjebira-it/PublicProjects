/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import blokus.Interface.ObservableImpl;

import java.util.Objects;

/**
 *
 * @author selim
 */
public class Box extends ObservableImpl{

    private static final String DEFAULT_COLOR = "WHITE";
    private final Position position;
    private final String defaultColor;
    private String color;

    /**
     * constructor of a box
     *
     * @param position the position of the box
     */
    public Box(Position position) {

        this.position = position;
        this.defaultColor = DEFAULT_COLOR;
        color = defaultColor;
    }

    /**
     * setter of color
     *
     * @param color the color to set
     */
    public void setColor(String color) {

        this.color = color;
        notifyObs();
    }

    /**
     * reset the box to its initial color
     */
    public void reset() {
        
        setColor(defaultColor);
    }

    /**
     * getter of position of the box
     *
     * @return the position of the box
     */
    public Position getPosition() {

        return position;
    }

    /**
     * getter of defaultColor
     *
     * @return the defaultColor of the box
     */
    public String getDefaultColor() {

        return defaultColor;
    }

    /**
     * get the current color of the box
     *
     * @return the color of the box
     */
    public String getColor() {

        return color;
    }

    /**
     * method to know if the box is free
     *
     * @return
     */
    public boolean isFree() {
        
        return color.equals(defaultColor);
    }

 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.position);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Box other = (Box) obj;
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
        if(color.equals(DEFAULT_COLOR))
            return " ";
        else{
            return color.charAt(0) + "";
        }
    }
    
    

}
