package g49853.diamond.model;

import java.util.ArrayList;
import java.util.List;

public class Treasure implements Tile {

    final int MAX_RUBIS = 15;
    final int MIN_RUBIS = 1;
    private final int initNbGems;
    private List<Gem> gems;

    /**
     * construct the Treasure and set rubies and initial value to the number
     * entered in parameter
     *
     * @param rubies number of rubies
     */
    public Treasure(int rubies) {
        this.initNbGems = rubies;
        this.gems = new ArrayList<>();
        for (int x = 0; x < initNbGems; x++) {
            gems.add(Gem.RUBIS);
        }
    }

    /**
     * construct the Treasure and set a random value to rubies and his initial
     * value
     */
    public Treasure() {
        int random = (int) (Math.random() * MAX_RUBIS) + MIN_RUBIS;
        this.initNbGems = random;
        this.gems = new ArrayList<>();
        for (int x = 0; x < initNbGems; x++) {
            gems.add(Gem.RUBIS);
        }
    }

    public List<Gem> getGems() {
        return gems;
    }

    /**
     * method that returns the value of attribute initial rubies
     *
     * @return value of initial rubies
     */
    public int getInitNbGems() {
        return this.initNbGems;
    }

    /**
     * method that get the current amount of rubies in the treasure tile.
     *
     * @return String of a number.
     */
    @Override
    public String getValue() {
        return this.initNbGems + "";
    }

    /**
     * method that adds the gems in the bag.
     *
     * @return the value of gems in the Treasure Tile.
     */
    public int getValueOfGems() {
        int sum = 0;
        for (int x = 0; x < this.gems.size(); x++) {
            sum += this.gems.get(x).getValue();
        }
        return sum;
    }

    public void transferGemsFrom(Treasure o) {
        this.gems.addAll(o.gems);
    }

    /**
     * method that gives rubies to explorers and changes the value of the
     * treasure
     *
     * @param explorers list of explorers
     * @throws IllegalArgumentException if list is empty
     * @throws NullPointerException if list equals null
     */
    @Override
    public void explore(List<Explorer> explorers) {
        if (explorers.isEmpty()) {
            throw new IllegalArgumentException("empty list given");
        } else if (explorers == null) {
            throw new NullPointerException("list given equals null");
        } else {
            int length = explorers.size();
            int valueOfGems = this.getValueOfGems();
            int rubisToShare = valueOfGems / length;
            int rubisToLet = valueOfGems % length;
            this.gems.clear();
            for (int x = 0; x < rubisToLet; x++) {
                this.gems.add(Gem.RUBIS);
            }
            explorers.forEach((j) -> {
                for (int x = 0; x < rubisToShare; x++) {
                    j.addToBag(Gem.RUBIS);
                }
            });
        }
    }

    /**
     * restore the initial value of the treasure
     */
    public void restore() {
        this.gems.clear();
        for (int x = 0; x < initNbGems; x++) {
            gems.add(Gem.RUBIS);
        }
    }

}
