package g49853.diamond.model;

import java.util.List;

public interface Tile {

    /**
     * method that allows explorers to discover a tile.
     *
     * @param explorers explorers who are exploring
     */
    void explore(List<Explorer> explorers);

    public String getValue();
}
