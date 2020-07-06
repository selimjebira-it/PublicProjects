package tictactoeServer.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.util.Pair;

/**
 *
 * @author selim
 */
public final class Game extends Observable {

    private final static String MSG_ERROR_POS = "YOU HAVE ENTERED A BAD POSITION (MAX = " + Board.BOARD_SIZE + "): ";
    private final static String MSG_ERROR_ALREADY_TAKEN = "ERROR POSITION ALREADY TAKEN : ";
    private final static String MSG_ERROR_COMPLETED = "ERROR SQUARE COMPLETED : ";
    private final static String MSG_ERROR_RULE = "YOU MUST INSERT YOUR JETON IN THE SQUARE NUMBER : ";
    private final static String MSG_ERROR_PLAYER = "PLAYER CAN'T JOIN THE GAME HE IS NOT CONNECTED ";
    private final static String MSG_ERROR_PLAYER_IN_GAME = "PLAYER CAN'T JOIN THE GAME HE IS ALREADY IN GAME ";
    private final static String MSG_ERROR_GAME_FINISHED = "GAME ALREADY FINISHED ";
    private final static String MSG_ERROR_NO_DIFFERENT_USERS = "YOU CAN'T PLAY AGAINST YOUR SELF ";
    private final static String MSG_FINISH_FIRST = "THE GAME MUST BE FINISHED TO REPLAY - SURREND OR FINISH THE GAME ";

    private final Player player1;
    private final Player player2;
    private final Board board;
    private final List<Integer> played;
    private int count;

    private boolean turn;
    private boolean first;
    private int lastBoxPicked;
    private int lastPositionPicked;
    private Player winner;
    private boolean finished;
    private boolean aborted;

    /**
     * constructor of a game
     *
     * @param user1 the user1
     * @param user2 the user2
     * @throws GameException if player are in game
     */
    public Game(User user1, User user2) throws GameException {

        if (user1 == user2) {
            throw new GameException(MSG_ERROR_NO_DIFFERENT_USERS);
        }
        if (!user1.isConnected() || !user2.isConnected()) {
            throw new GameException(MSG_ERROR_PLAYER + (user1.isConnected() == false ? user1.getName() : user2.getName()));
        }

        if (user1.isInGame() || user2.isInGame()) {
            throw new GameException(MSG_ERROR_PLAYER_IN_GAME + (user1.isInGame() == true ? user1.getName() : user2.getName()));
        }

        this.player1 = new Player(user1, Jeton.JETON_O);
        this.player2 = new Player(user2, Jeton.JETON_X);
        this.board = new Board();
        this.played = new ArrayList<>(Board.BOARD_SIZE);
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            played.add(-1);
        }

        turn = true;
        first = true;
        lastBoxPicked = -1;
        lastPositionPicked = -1;
    }

    private void verifyEntry(int position, int square, int pos) throws GameException {

        if (finished) {
            throw new GameException(MSG_ERROR_GAME_FINISHED);
        }
        if(board.isComplete(square)){
        
            throw new GameException(MSG_ERROR_COMPLETED + square);
        }
        
        if (board.getValue(square, pos) != null) {
            throw new GameException(MSG_ERROR_ALREADY_TAKEN + position);
        }

        if (square != this.lastBoxPicked && this.lastBoxPicked != -1 && !board.isComplete(lastBoxPicked)) {

            throw new GameException(MSG_ERROR_RULE + lastBoxPicked);
        }
    }

    private void executeInsertion(int position, int square, int pos) {

        board.insert(square, pos, turn == true ? player1.getJeton() : player2.getJeton());
        lastBoxPicked = pos;
        played.set(count, position);
        count++;
        turn = !turn;

    }

    private void verifyNewState(int position, int square, int pos) {

        if (board.getResult() != null) {

            winner = board.getResult() == player1.getJeton() ? player1 : player2;
            setGameIsFinished(true);
        } else if (board.isComplete()) {
            winner = null;
            setGameIsFinished(true);
        } else {

            setChanged();
            notifyObservers(new Pair(position, board.getValue(square) != null));
        }

    }

    private void setGameIsFinished(boolean finished) {

        this.finished = finished;
        player1.updatePlayer(this);
        player2.updatePlayer(this);
        setChanged();
        notifyObservers();

    }

    /**
     * method to play
     *
     * @param position position to insert the piece goes from UP_L to BOT_R
     * @throws GameException
     */
    public void play(int position) throws GameException {

        if (position < 0 || position >= Board.BOARD_SIZE) {
            throw new IllegalArgumentException(MSG_ERROR_POS + position);
        }

        int square = position / Board.SQUARE_SIZE;
        int pos = position % Board.SQUARE_SIZE;

        verifyEntry(position, square, pos);

        executeInsertion(position, square, pos);

        lastPositionPicked = position;
        verifyNewState(position, square, pos);

    }

    /**
     * method to replay
     *
     * @throws GameException
     */
    public void replay() throws GameException {

        if (!finished) {
            throw new GameException(MSG_FINISH_FIRST);
        }
        turn = !first;
        first = turn;
        for (int i = 0; i < Board.BOARD_SIZE; i++) {

            played.set(i, -1);
        }
        board.reset();
        lastBoxPicked = -1;
        lastPositionPicked = -1;
        setGameIsFinished(false);

    }

    /**
     * method to surrend
     *
     * @param id
     * @throws GameException
     */
    public void surrender(int id) throws GameException {

        if (finished) {
            throw new GameException(MSG_ERROR_GAME_FINISHED);
        }
        winner = id == player1.getId() ? this.player2 : this.player1;
        aborted = true;
        setGameIsFinished(true);

    }

    public Player getCurrentPlayer() {

        return turn == true ? player1 : player2;
    }

    /**
     * method to get the id of the current player
     *
     * @return
     */
    public int getCurrentId() {

        return turn == true ? player1.getId() : player2.getId();
    }

    public List<Integer> getPlayed() {

        ArrayList<Integer> copy = new ArrayList<>();
        played.forEach((i) -> {
            copy.add(i);
        });
        return copy;
    }

    public int getCount() {

        return count;
    }

    public Player getWinner() {

        return winner;
    }

    public Player getPlayer1() {

        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isFinished() {

        return finished;
    }

    public boolean isAborted() {

        return aborted;
    }

    public int getLastPositionPicked() {
        return lastPositionPicked;
    }
    
    public void start(){
    
        setGameIsFinished(false);
    }

}
