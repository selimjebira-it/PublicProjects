package blokus.Controler;


import blokus.Enum.Color;
import blokus.Model.Game;
import blokus.Model.Player;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author selim
 */
public class Controler {

    private Game game;

    private final static ArrayList<Player> PLAYERS
            = new ArrayList<>(Arrays.asList(new Player[]{new Player("Player1", Color.RED,1),
        new Player("Player2", Color.BLUE,2), new Player("Player3", Color.YELLOW,3), new Player("Player4", Color.GREEN,4)}));

    public Controler() {

    }

    /**
     * create a new Game and init players in game
     */
    public void newGame() {

        this.game = new Game();

        game.newGame(PLAYERS);
        if(game.isOpen()){
            System.out.println("game has been opened");
            
        }
        
    }
    
    public void resetGame(){
        this.game.restart();
    }

    /**
     * method to make the current player leave the game
     */
    public void leave() {

        try {
            game.leave();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * method to skip a turn
     */
    public void skip() {

        try {
            game.skip();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int getIndexCurrentPlayer(){
        
        return game.getIndexOfCurrentPlayer();
    }
    
    public String getHistory(){
        
        return game.getHistory();
    }

    public Game getGame() {
        
        return game;
    }

    
}
