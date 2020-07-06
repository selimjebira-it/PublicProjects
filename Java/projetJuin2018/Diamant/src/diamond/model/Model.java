package g49853.diamond.model;

import java.util.List;

public interface Model {

    /**
     * This methode can be used to add new player (explorer) in the game.
     *
     * @param explorer the explorer to add.
     */
    void addExplorer(Explorer explorer);

    /**
     * Make all exploring explorers move forward in the cave. The explorers
     * share what they found.
     */
    void moveForward();

    /**
     * Declares if it's the end of a turn.
     *
     * @return true if it's the end of the game.
     */
    boolean isExplorationPhaseOver();

    /**
     * verify if it is the end of the game
     *
     * @return
     */
    boolean isOver();

    /**
     * Return the cave of the game.
     *
     * @return the game's cave.
     */
    Cave getCave();

    /**
     * Give all explorers of the game. They could be exploring or leaving.
     *
     * @return all the explorers of the game.
     */
    List<Explorer> getExplorers();

    /**
     * Give all explorers which are exploring.
     *
     * @return explorers in the cave.
     */
    List<Explorer> getExploringExplorers();

    /**
     * Consider the choice of the explorer to leave the cave.
     *
     * @param explorer The explorer who make the choice to leave.
     * @throws RuntimeException If the explorer is not in the current game.
     */
    void handleExplorerDecisionToLeave(Explorer explorer);

    /**
     * verify if the game respect the rules.
     */
    void start();

    /**
     *
     * @return state of unsafe of the current entrance
     */
    boolean isExplorationPhaseAborted();

    /**
     * method that verify if the number of explorers is enough to launch a game.
     *
     * @return number of explorers is >= 3
     */
    boolean isThereEnoughExplorer();

    /**
     * method that verify if we can add an explorer in the game
     *
     * @return the number of explorers is > 8
     */
    boolean isItPossibleToAddExplorer();

    /**
     * method that return the explorer who has the most amount of rubies.
     *
     * @return the explorer with the max of rubies in the current game.
     */
    Explorer getWinner();

    /**
     * method that takes out of the cave explorers who want to.
     */
    void makeExplorersLeave();

    /**
     * mehtod that open a new entrance and explorers go turn into an exploration
     * mode
     */
    void startNewExplorationPhase();

    /**
     * method that ends the current exploring phase and close the current
     * entrance
     */
    void endExplorationPhase();
}
