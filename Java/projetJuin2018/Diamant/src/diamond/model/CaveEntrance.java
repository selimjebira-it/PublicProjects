package g49853.diamond.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author selim
 */
public class CaveEntrance {

    private Tile lastDiscoveredTile;
    private List<Tile> path;
    private boolean lockedOut;
    private boolean treasureFound;
    private Treasure firstTreasureTile;
    Cave cave;
    boolean unSafe;

    /**
     * Create an Entrance,set treasure found to false and init the path.
     *
     * @param cave the cave where we create the new entrance
     */
    public CaveEntrance(Cave cave) {
        this.lastDiscoveredTile = null;
        this.path = new ArrayList<>();
        this.lockedOut = false;
        this.treasureFound = false;
        this.cave = cave;
        this.treasureFound = false;
        this.firstTreasureTile = null;
        this.unSafe = false;

    }

    /**
     * set the value of unsafe to true
     */
    public void setStateToUnsafe() {
        this.unSafe = true;
    }

    /**
     * turn status of lockedOut to true
     */
    public void lockOut() {
        this.lockedOut = true;
    }

    /**
     * method that adds tile to the path
     *
     * @param treasure tile to add
     */
    void addTileToPath(Tile tile) {
        this.path.add(tile);
    }

    /**
     * method that create a new tile and share to explorers and trapped them if
     * 2 same hazard cards have been picked during the instance of the entrance.
     *
     * @param explorers list of explorers
     * @throws IllegalArgumentException if list is empty
     * @throws NullPointerException if is list equals null
     */
    public void discoverNewTile(List<Explorer> explorers) {
        Hazard hazard;
        Treasure treasure;
        if (explorers.isEmpty()) {
            throw new IllegalArgumentException("empty list given");
        } else if (explorers == null) {
            throw new NullPointerException("value of list is null");
        } else {
            this.lastDiscoveredTile = this.cave.getDeck().getTile();
            if (lastDiscoveredTile.getClass() == Treasure.class) {
                treasure = (Treasure) lastDiscoveredTile;
                treasure.explore(explorers);
            } else if (lastDiscoveredTile.getClass() == Hazard.class) {
                hazard = (Hazard) this.lastDiscoveredTile;
                hazard.explore(explorers);
                if (this.path.contains(lastDiscoveredTile)) {
                    setStateToUnsafe();
                    hazard.escape();
                }
            }
            addTileToPath(lastDiscoveredTile);
        }

    }

    /**
     * method that transfer gems from last discovered treasure to the first
     * discovered treasure if there is one.
     */
    public void makeLastTileExplored() {
        if (lastDiscoveredTile.getClass() == Treasure.class) {
            if (this.treasureFound) {
                this.firstTreasureTile.transferGemsFrom((Treasure) this.lastDiscoveredTile);
            } else {
                this.firstTreasureTile = (Treasure) this.lastDiscoveredTile;
                this.treasureFound = true;
            }

        }

    }

    /**
     * method that allow explorers to explore the path when they go back to
     * camp.
     *
     * @param explorers list of explorers
     */
    public void returnToCamp(List<Explorer> explorers) {
        if (!explorers.isEmpty()) {
            if (this.treasureFound) {
                Treasure treasure = this.getFirstTreasureTile();
                treasure.explore(explorers);
            }
            for (Tile tile : this.path) {
                if (tile instanceof Relic) {
                    if (((Relic) tile).canBeTaken(explorers)) {
                        this.cave.incrementNbTakenRelics();
                        ((Relic) tile).convertGameValue(this.cave.getNbTakenRelics());
                        tile.explore(explorers);
                    }

                }

            }
            for (Explorer expl : explorers) {
                expl.reachCamp();
            }
        }
    }

    /**
     * compare 2 entrances, the equality is verified if they have the same last
     * discovered last discoveredTile.
     *
     * @param other
     * @return the equality between the 2 objects
     */
    public boolean equals(CaveEntrance other) {
        return other.getLastDiscoveredTile() == this.lastDiscoveredTile;
    }

    public boolean isLockedOut() {
        return lockedOut;
    }

    public boolean isTreasureFound() {
        return treasureFound;
    }

    public List<Tile> getPath() {
        return path;
    }

    public Treasure getFirstTreasureTile() {
        if (this.firstTreasureTile == null) {
            throw new GameException("there is no treasure to return");
        }
        return firstTreasureTile;
    }

    public Cave getCave() {
        return cave;
    }

    public boolean isUnSafe() {
        return unSafe;
    }

    public Tile getLastDiscoveredTile() {
        if (this.lastDiscoveredTile == null) {
            throw new GameException("the last treasure is null");
        }
        return lastDiscoveredTile;
    }

    //used for test only.
    void setLastDiscoveredTile(Tile lastDiscoveredTile) {
        this.lastDiscoveredTile = lastDiscoveredTile;
    }

    //used for test only
    void setTreasureFound(boolean treasureFound) {
        this.treasureFound = treasureFound;
    }

    //used for test only
    void setFirstTreasureTile(Treasure firstTreasureTile) {
        this.firstTreasureTile = firstTreasureTile;
    }

}
