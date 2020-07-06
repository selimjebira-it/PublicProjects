package blokus.Model;


import blokus.Enum.Color;
import blokus.Enum.Shape;
import blokus.Interface.ObservableImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import static java.util.stream.Collectors.toCollection;

/**
 *
 * @author selim
 */
public class Piece extends ObservableImpl implements Serializable{

    
    
    
    private final Shape shape;
    private final Color color;
    private ArrayList<Position> positions;
    private Position first;

    public Piece(Shape shape, Color color) {

        this.shape = shape;
        this.color = color;
        positions = shape.getPositions();
        first = null;

    }

    public void reset() {

        positions = shape.getPositions();
        setFirst(null);
    }


    public ArrayList<Position> getPositionsWithFirst(Position position) {

        Position firstP = position;
        ArrayList<Position> result = new ArrayList<>();
        for(Position pos : positions){
            result.add(firstP);
            firstP = new Position(firstP.getX() + pos.getX(), firstP.getY() + pos.getY());
        }
        return result;

    }
    
    

    public void miror() {

        this.positions = shape.getPositions().stream().map((Position pos) -> {

            int x = pos.getX() == 0 ? 0 : (-1 * pos.getX());
            int y = pos.getY();
            return new Position(x, y);

        }).collect(toCollection(ArrayList::new));
        notifyObs();
    }

    public void reverse() {

        this.positions = shape.getPositions().stream().map((Position pos) -> {
            int x = pos.getX();
            int y = pos.getY() == 0 ? 0 : -1 * pos.getY();
            return new Position(x, y);
        }).collect(toCollection(ArrayList::new));
        notifyObs();
    }

    public void mirorReverse() {

        this.positions = shape.getPositions().stream().map((Position pos) -> {
            int x = pos.getX() == 0 ? 0 : -1 * pos.getX();
            int y = pos.getY() == 0 ? 0 : -1 * pos.getY();
            return new Position(x, y);
        }).collect(toCollection(ArrayList::new));
        notifyObs();
    }

    public void setFirst(Position first) {

        this.first = first;
        notifyObs();
    }

    public Position getFirst() {

        return first;
    }

    public ArrayList<Position> getPositions() {

        return positions.stream().collect(toCollection(ArrayList::new));
        
    }

    public String getColor() {

        return color.name();
    }

    public boolean isPlayed() {

        return first != null;
    }

    public boolean isNotPlayed() {

        return !isPlayed();
    }

    public int getPoints() {

        return shape.getPositions().size();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.shape);
        hash = 67 * hash + Objects.hashCode(this.color);
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
        final Piece other = (Piece) obj;
        if (this.shape != other.shape) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        
        return  "Pièce " + shape.name() ;
    }
    
    

}
