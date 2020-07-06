package g49853.diamond.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game implements Model {

    List<Explorer> explorers;
    List<Explorer> exploringExplorers;
    private final int NB_MAXJ = 8;
    private final int NB_MINJ = 3;
    Cave cave;

    /**
     * Create the list of explorers,list of exploring explorers and a cave.
     */
    public Game() {
        this.explorers = new ArrayList<>();
        this.exploringExplorers = new ArrayList<>();
        this.cave = new Cave();
    }

    @Override
    /**
     * method that add an explorer to the list of Explorers
     */
    public void addExplorer(Explorer explorer) {
        this.explorers.add(explorer);
    }

    @Override
    /**
     * method that remove an explorer from the exploring explorers list
     */
    public void handleExplorerDecisionToLeave(Explorer explorer) {
        if (this.exploringExplorers.contains(explorer)) {
            this.exploringExplorers.remove(explorer);
        } else {
            throw new GameException("no such explorer in exploring list");
        }
    }

    @Override
    /**
     * method that push exploring explorers deeper into the cave
     */
    public void moveForward() {
        this.cave.getCurrentEntrance().discoverNewTile(this.exploringExplorers);
        if (this.cave.getCurrentEntrance().unSafe) {
            List<Explorer> listJ = copyExploringExplorerList(this.exploringExplorers);
            for (Explorer expl : listJ) {
                handleExplorerDecisionToLeave(expl);
                expl.runAway();
            }
        }
    }

    @Override
    /**
     * method that makes explorers taking the road back to camp.
     */
    public void makeExplorersLeave() {
        this.cave.getCurrentEntrance().makeLastTileExplored();
        List<Explorer> listJLeaving;
        listJLeaving = this.explorers.stream()
                .filter(x -> x.getState() == State.LEAVING).collect(Collectors.toList());
        this.cave.getCurrentEntrance().returnToCamp(listJLeaving);
        for (Explorer expl : listJLeaving) {
            handleExplorerDecisionToLeave(expl);
        }
    }

    /**
     * method that open a new entrance and turn explorers in exploring state
     */
    @Override
    public void startNewExplorationPhase() {
        this.cave.openNewEntrance();
        for (Explorer expl : this.explorers) {
            expl.startExploration();
            this.exploringExplorers.add(expl);
        }
    }

    /**
     * close the current entrance and restore the deck.
     */
    @Override
    public void endExplorationPhase() {
        this.cave.lockOutCurrentEntrance();
        Deck deck = this.getCave().getDeck();
        for (Tile tile : this.cave.getCurrentEntrance().getPath()) {
            deck.putBack(tile);
        }
    }

    @Override
    /**
     * method that return the winner of the game
     *
     * @return Explorer who has the biggest amount of rubies.
     * @throws GameException if the game is not over.
     */
    public Explorer getWinner() {
        if (!isOver()) {
            throw new GameException("Too soon to know the winner!");
        }
        Explorer explMaxRub = new Explorer("No winner");
        int max = 0;
        int fortune;
        for (Explorer ex : this.explorers) {
            fortune = ex.getFortune();
            if (fortune > max) {
                explMaxRub = ex;
                max = fortune;
            }
        }
        return explMaxRub;
    }

    /**
     * check if the game is over
     *
     * @return the game is over
     */
    @Override
    public boolean isOver() {
        boolean isNotExploring = this.getCave().getCurrentEntrance().isLockedOut();
        boolean noMoreEntries = !this.getCave().hasNewEntranceToExplore();
        return isNotExploring && noMoreEntries;
    }

    @Override
    /**
     * verify if the number of explorers is correct
     */
    public void start() {
        if (!isThereEnoughExplorer() || !isItPossibleToAddExplorer()) {
            throw new GameException("number of explorers is invalid");
        }
    }

    @Override
    /**
     * @return number of explorers is superior to number minimum of explorers.
     */
    public boolean isThereEnoughExplorer() {
        return this.explorers.size() >= NB_MINJ;
    }

    @Override
    /**
     * @return number of explorers is inferior to number maximum of explorers.
     */
    public boolean isItPossibleToAddExplorer() {
        return this.explorers.size() < NB_MAXJ;
    }

    /**
     * method that verify if the current entrance has been trapped.
     *
     * @return the state of the entrance
     */
    @Override
    public boolean isExplorationPhaseAborted() {
        return this.cave.getCurrentEntrance().unSafe;
    }

    @Override
    /**
     * method that returns true if the list of exploring explorers is empty
     */
    public boolean isExplorationPhaseOver() {
        return this.exploringExplorers.isEmpty();
    }

    @Override
    /**
     * return the current Cave
     */
    public Cave getCave() {
        return this.cave;
    }

    @Override
    /**
     * return the list of all Explorers
     */
    public List<Explorer> getExplorers() {
        return this.explorers;
    }

    @Override
    /**
     * return the list of explorers who are exploring
     */
    public List<Explorer> getExploringExplorers() {
        return this.exploringExplorers;
    }

    /**
     *
     * method that copy the exploringList
     *
     * @param explorerL list of explorer we want to copy
     * @return list of explorers
     */
    public List<Explorer> copyExploringExplorerList(List<Explorer> explorerL) {
        List<Explorer> resList = new ArrayList<>();
        for (int j = 0; j < explorerL.size(); j++) {
            resList.add((Explorer) explorerL.get(j));
        }
        return resList;
    }

}
