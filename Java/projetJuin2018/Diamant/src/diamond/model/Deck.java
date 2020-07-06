package g49853.diamond.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    List<Tile> tiles = new ArrayList<>();

    /**
     * create a deck that contains 15 treasure
     */
    public Deck() {
        int[] listValues = {1, 2, 3, 4, 5, 5, 7, 7, 9, 11, 11, 13, 14, 15, 17};
        for (int i : listValues) {
            this.tiles.add(new Treasure(i));
        }
        Hazard haz;
        for (HazardType hzT : HazardType.values()) {
            for (int x = 0; x < 3; x++) {
                haz = new Hazard(hzT);
                this.tiles.add(haz);
            }
        }
        Relic relic;
        for (int x = 0; x < 5; x++) {
            relic = new Relic();
            this.tiles.add(relic);
        }

    }

    /**
     * getter of tiles
     *
     * @return the list of tiles.
     */
    List<Tile> getTiles() {
        return tiles;
    }

    /**
     * pick a treasure in the deck.
     *
     * @return a random tile in the list of tiles.
     */
    public Tile getTile() {
        int rand = random(0, this.tiles.size());
        Tile picked = tiles.get(rand);
        tiles.remove(picked);
        return picked;
    }

    /**
     * return a random value between max and min.
     *
     * @param min min value
     * @param max max value
     * @return
     */
    public int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * put back the treasure in the deck and restore its value to initial value
     *
     * @param tile the tile we want to put back in the deck
     */
    public void putBack(Tile tile) {
        Treasure treasure;
        Hazard danger;
        if (tile.getClass() == Treasure.class) {
            treasure = (Treasure) tile;
            treasure.restore();
            this.tiles.add(treasure);
        } else if (tile.getClass() == Hazard.class) {
            danger = (Hazard) tile;
            if (!danger.exlorersEscapeReason) {
                this.tiles.add(danger);
            }
        }

    }
}
