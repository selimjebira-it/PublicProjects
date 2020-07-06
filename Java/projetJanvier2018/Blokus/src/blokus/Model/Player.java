package blokus.Model;




import blokus.Enum.Color;
import blokus.Enum.Shape;
import blokus.Interface.ObservableImpl;
import blokus.Interface.Observer;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.stream.Collectors.toCollection;

/**
 *
 * @author selim
 */
public class Player extends ObservableImpl {

    private final String name;
    private final String color;
    private final ArrayList<Piece> bag;
    private final Position firstMove;
    private final int index;
    
    
    private boolean inGame;
    private boolean currentPlayer;
    private int score;

    /**
     * constructor of player
     * @param name the player's name
     * @param color the color of the player
     * @param index index of the player
     */
    public Player(String name, Color color,int index) {

        this.name = name;
        this.color = color.toString();
        this.firstMove = color.getFirstMove();
        this.index = index;
        currentPlayer = false;
        
        
        this.bag = Arrays.asList(Shape.values()).stream().map(shape -> {
            return new Piece(shape, color);
        }).collect(toCollection(ArrayList::new));

    }



    /**
     * setter of current player
     * @param currentPlayer 
     */
    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
        notifyObs();
    }

    /**
     * setter of in game
     * @param bool 
     */
    private void setInGame(boolean bool) {

        inGame = bool;
        notifyObs();
    }

    /**
     * updating the score of the player
     */
    public void updateScore() {

        this.score = (int) getActivePieces().stream().mapToInt((Piece piece) -> {
            return piece.getPoints();
        }).sum();
        notifyObs();
    }

    /**
     * reset the player
     */
    private void reset() {

        bag.forEach(Piece::reset);
        currentPlayer = false;
        updateScore();
    }

    /**
     * enter the game
     */
    public void enterGame() {

        reset();
        setInGame(true);
    }

    /**
     * leave the game
     */
    public void leaveGame() {

        setInGame(false);

    }

    public Piece getPiece(int index)  {

        return bag.get(index);
    }

    public ArrayList<Piece> getActivePieces() {

        return bag.stream().filter(Piece::isPlayed).collect(toCollection(ArrayList::new));
    }

    public ArrayList<Piece> getInactivePieces() {

        return bag.stream().filter(Piece::isNotPlayed).collect(toCollection(ArrayList::new));
    }

    public ArrayList<Piece> getBag() {

        return bag;
    }

    public int getNbPiecesLeft() {

        return getInactivePieces().size();
    }

    public int getScore() {

        return score;
    }

    public String getName() {

        return name;
    }

    public String getColor() {

        return color;
    }

    public boolean isInGame() {

        return inGame;
    }

    public boolean isCurrentPlayer() {
        
        return currentPlayer;
    }

    public boolean hasFinished() {

        return getInactivePieces().isEmpty();
    }

    public Position getFirstMove() {
        
        return firstMove;
        
    }
    
    public int getIndex(){
        return index;
    }
    
    

    @Override
    public String toString() {

        return "\tName : " + name + "\tScore : " + score + "\tState : " + inGame + "\t PiecesLeft :" + this.getInactivePieces().size();
    }

}
