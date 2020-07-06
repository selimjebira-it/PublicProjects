
package blokus.Interface;

;
import blokus.Model.Player;
import java.util.ArrayList;

/**
 *
 * @author selim
 */
public interface GameInterface {
    
    public void play(int indexPiece, int x, int y);
    public void skip();
    public void leave();
    public void newGame(ArrayList<Player> players);
    public boolean isOver();
    public ArrayList<Player> getActivePlayers();
    public ArrayList<Player> getPlayers();
    public void mirorPiece(int index);
    public void reversePiece(int index);
    public void mirorReversePiece(int index);
    
}
