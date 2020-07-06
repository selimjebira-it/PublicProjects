package tictactoeServer.model.game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author selim
 */
public final class Board {

    public static final int BOARD_SIZE = 81;
    public static final int SQUARE_SIZE = 9;
    private final int[][] verification = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //lines
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //collumns
        {0, 4, 8}, {2, 4, 6}};

    private final List<List<Jeton>> board;
    private final List<Jeton> completed;
    private final List<Integer> nb_of_jetons;
    private Jeton result;

    /**
     * constructor of the board
     */
    public Board() {

        this.board = new ArrayList<>(SQUARE_SIZE);
        for (int i = 0; i < SQUARE_SIZE; i++) {
            List<Jeton> grid = new ArrayList<>(SQUARE_SIZE);
            for (int j = 0; j < SQUARE_SIZE; j++) {

                grid.add(null);
            }
            board.add(grid);
        }
        completed = new ArrayList<>(SQUARE_SIZE);
        for (int i = 0; i < SQUARE_SIZE; i++) {

            completed.add(null);
        }
        nb_of_jetons = new ArrayList<>(SQUARE_SIZE);
        for (int i = 0; i < SQUARE_SIZE; i++) {

            nb_of_jetons.add(0);
        }

    }

    /**
     * method to reset the board
     */
    public void reset() {
        for (int i = 0; i < SQUARE_SIZE; i++) {

            completed.set(i,null);
        }
        for (int i = 0; i < SQUARE_SIZE; i++) {

            for (int j = 0; j < SQUARE_SIZE; j++) {

                board.get(i).set(j, null);
            }

        }
        for (int i = 0; i < SQUARE_SIZE; i++) {

            nb_of_jetons.set(i, 0);
        }
        result = null;
    }

    /**
     * method to insert a piece in the board
     *
     * @param square the little grid
     * @param position the box in the grid
     * @param jeton the alue
     */
    public void insert(int square, int position, Jeton jeton) {

        board.get(square).set(position, jeton);
        nb_of_jetons.set(square, nb_of_jetons.get(square) + 1);
        update(square);

    }

    private void update(int square) {

        List<Jeton> jetons = board.get(square);
        Jeton jeton;
        for (int[] list : verification) {

            jeton = jetons.get(list[0]);
            
            if (jeton == jetons.get(list[1]) && jeton == jetons.get(list[2]) && jeton != null) {
                completed.set(square, jeton);
                update();
                break;
            }
        }

    }

    private void update() {

        Jeton jeton;
        for (int[] list : verification) {

            jeton = completed.get(list[0]);
            if (jeton == null) {
                break;
            }
            if (jeton == completed.get(list[1]) && jeton == completed.get(list[2])) {

                result = jeton;
                break;
            }

        }
    }

    //GETTERS--------------------------------------------------------------------------
    /**
     * getter of the value of a box inside a little grid
     *
     * @param square the little grid
     * @param position the box
     * @return
     */
    public Jeton getValue(int square, int position) {

        return board.get(square).get(position);
        
    }

    /**
     * getter of the value of a little grid
     *
     * @param square the little grid we want to retrieve Jeton
     * @return
     */
    public Jeton getValue(int square) {

        return completed.get(square);

    }

    /**
     * method to verify if a little grid is complete
     *
     * @param square the little grid to verify
     * @return
     */
    public boolean isComplete(int square) {

        return completed.get(square) != null || nb_of_jetons.get(square) == SQUARE_SIZE;

    }

    public boolean isComplete() {

        return result != null || nbJetonsComplete();
    }

    private boolean nbJetonsComplete() {

        for (int i : nb_of_jetons) {

            if (i != SQUARE_SIZE) {
                return false;
            }
        }
        return true;
    }

    public Jeton getResult() {

        return result;
    }

}
