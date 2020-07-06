package blokus.Model;

import java.io.Serializable;





/**
 *
 * @author selim
 */
public class Position implements Serializable{
    
    private final int x;
    private final int y;

    /**
     * constructor of position 
     * @param x horizontal position
     * @param y vertical position
     */
    public Position(int x, int y) {
        
        this.x = x;
        this.y = y;
    }

    /**
     * getter of horizontal position
     * @return horizontal position
     */
    public int getX() {
        
        return x;
    }

    /**
     * getter of vertcial position
     * @return vertical position
     */
    public int getY() {
        
        return y;
    }

    @Override
    public int hashCode() {
        
        int hash = 3;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
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
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
        return "("+ x + "," + y + ")";
    }

  
    
    
    
    
}
