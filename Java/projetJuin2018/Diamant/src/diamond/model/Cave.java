package g49853.diamond.model;

public class Cave {

    private int nbExploredEntrance;
    private CaveEntrance currentEntrance;
    private final int MAX_ENTRANCES = 5;
    private Deck deck;
    private int nbTakenRelics;

    /**
     * Construct a cave init the number of explored entrance to 0, create a new
     * entrance, init the deck and set the number of relics found to 0
     */
    public Cave() {
        this.nbExploredEntrance = 0;
        this.currentEntrance = new CaveEntrance(this);
        this.deck = new Deck();
        this.nbTakenRelics = 0;
    }

    /**
     * getter of the number of founded relics.
     *
     * @return number of founded relics
     */
    public int getNbTakenRelics() {
        return nbTakenRelics;
    }

    /**
     * increment the number of founded relics.
     */
    public void incrementNbTakenRelics() {
        this.nbTakenRelics++;
    }

    /**
     * getter of the Deck
     *
     * @return the deck
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * getter of the number of explored entrances.
     *
     * @return number of explored entrances
     */
    public int getNbExploredEntrance() {
        return nbExploredEntrance;
    }

    /**
     * getter of the current entrance in the cave
     *
     * @return the current entrance
     */
    public CaveEntrance getCurrentEntrance() {
        return currentEntrance;
    }

    /**
     * getter of the state of the current entrance, safe or unsafe
     *
     * @return if the entrance is unsafe
     */
    public boolean isLastEntranceUnsafe() {
        return this.currentEntrance.unSafe;
    }

    /**
     * method that verify if we can create a new entrance.
     *
     * @return number of entrances inferior to MAX ENTRANCES(5)
     */
    public boolean hasNewEntranceToExplore() {
        return this.nbExploredEntrance < MAX_ENTRANCES;
    }

    /**
     * method that open a new entrance
     *
     * @throws GameExcpetion if last entrance is not closed or too many
     * entrances have been opened.
     */
    public void openNewEntrance() {
        if (this.nbExploredEntrance > 0 && !this.currentEntrance.isLockedOut()) {
            throw new GameException("Entrance not closed");
        } else if (!hasNewEntranceToExplore()) {
            throw new GameException("Too many entrances!");
        } else {
            this.currentEntrance = new CaveEntrance(this);
        }

    }

    /**
     * close the current entrance
     *
     * @throws GameException if entrance is already closed or there is no
     * entrance to close.
     */
    public void lockOutCurrentEntrance() {
        if (this.currentEntrance.isLockedOut() || this.currentEntrance == null) {
            throw new GameException("no entrance opened");
        } else {
            this.currentEntrance.lockOut();
            this.nbExploredEntrance++;
        }
    }

}
