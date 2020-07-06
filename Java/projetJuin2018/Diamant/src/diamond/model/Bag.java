package g49853.diamond.model;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    protected List<Gem> gems;

    /**
     * Construct a Bag and set the number of player's gems to 0
     */
    public Bag() {
        this.gems = new ArrayList<>();
    }

    /**
     * method that add a number of rubies
     *
     * @param gem a gem to add
     */
    public void addGem(Gem gem) {
        this.gems.add(gem);
    }

    /**
     * returns the number of gems each player possesses
     *
     * @return the value of the bag
     */
    public int getValue() {
        int sum = 0;
        for (Gem g : this.gems) {
            sum += g.getValue();
        }
        return sum;
    }

    /**
     * method that compares 2 bags
     *
     * @param other the other object
     * @return if they are equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bag other = (Bag) obj;
        if (this.getValue() != other.getValue()) {
            return false;
        }
        return true;
    }

    /**
     * method that set number of gems to 0
     */
    public void loseContent() {
        this.gems.clear();
    }

}
