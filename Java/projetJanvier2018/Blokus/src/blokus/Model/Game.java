package blokus.Model;

import blokus.Interface.GameInterface;
import blokus.Interface.ObservableImpl;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toCollection;

/**
 *
 * @author selim
 */
public class Game extends ObservableImpl implements GameInterface {

    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 20;
    private final ArrayList<Player> players;
    private final BoardControl board;
    private Player currentPlayer;
    private boolean open;
    private final History history;

    /**
     * constructor of the game
     */
    public Game() {

        this.players = new ArrayList<>();
        this.board = new BoardControl(BOARD_WIDTH, BOARD_HEIGHT);
        history = new History();
    }

    /**
     * method to add a player
     *
     * @param player the player to add
     */
    private void addPlayer(Player player) {

        players.add(player);
    }

    /**
     * method to restart a game
     */
    public void restart() {

        players.forEach(Player::enterGame);
        board.reset();
        currentPlayer = null;
        history.clear();
        getCurrentPlayer().setCurrentPlayer(true);
    }

    /**
     * method to get the current player
     *
     * @return
     */
    public Player getCurrentPlayer() {

        if (currentPlayer == null) {

            return currentPlayer = players.get(0);
        } else {

            return currentPlayer;
        }
    }

    /**
     * method to get the next player
     */
    private void nextPlayer() {

        ArrayList<Player> activePlayers = getActivePlayers();
        if (activePlayers.isEmpty()) {
            currentPlayer = null;
        } else {
            Player player = getCurrentPlayer();
            int indexCu = activePlayers.indexOf(player);
            int indexNext = (indexCu + 1) % activePlayers.size();
            currentPlayer = activePlayers.get(indexNext);
            currentPlayer.setCurrentPlayer(true);
            player.setCurrentPlayer(false);
        }
    }

    @Override
    public void newGame(ArrayList<Player> players) {

        this.players.clear();
        players.forEach(player -> addPlayer(player));
        restart();
        open = true;

    }

    @Override
    public ArrayList<Player> getActivePlayers() {

        return players.stream().filter(Player::isInGame).collect(toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Player> getPlayers() {

        return players.stream().collect(toCollection(ArrayList::new));
    }

    @Override
    public boolean isOver() {

        return players.isEmpty()
                || players.stream().anyMatch(Player::hasFinished)
                || players.stream().noneMatch(Player::isInGame)
                || players.stream().noneMatch(player -> {
                    return board.canPlay(player);
                });
    }

    @Override
    public void play(int indexPiece, int x, int y) {

        Player player = getCurrentPlayer();
        Piece pieceToPlay = player.getPiece(indexPiece);
        try {

            board.insert(currentPlayer, pieceToPlay, new Position(x, y));

            nextPlayer();

        } catch (BlokusException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void play(Player player, Piece piece, Position pos) throws BlokusException {

        board.insert(player, piece, pos);
        String historyText = "Player : " + player.getIndex() + " -> " + piece.toString();
        
        history.addText(historyText);
        nextPlayer();
        notifyObs();

    }

    @Override
    public void skip() {

        nextPlayer();
    }

    @Override
    public void leave() {

        Player player = getCurrentPlayer();
        nextPlayer();
        player.leaveGame();
        notifyObs();
    }

    @Override
    public void mirorPiece(int index) {

        getCurrentPlayer().getPiece(index).miror();
    }

    @Override
    public void reversePiece(int index) {

        getCurrentPlayer().getPiece(index).reverse();
    }

    @Override
    public void mirorReversePiece(int index) {

        getCurrentPlayer().getPiece(index).mirorReverse();
    }

    public boolean isOpen() {

        return open;

    }

    public int getIndexOfCurrentPlayer() {

        if (currentPlayer == null) {
            return 0;
        }
        return players.indexOf(currentPlayer);
    }

    public BoardControl getBoard() {
        return board;
    }

    public String getHistory() {

        return history.getText();
    }

}
