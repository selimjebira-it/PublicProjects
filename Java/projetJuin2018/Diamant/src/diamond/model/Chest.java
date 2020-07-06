package g49853.diamond.model;

/**
 *
 * @author selim
 */
public class Chest extends Bag {

    /**
     * construct a chest that save the bag of a player.
     */
    public Chest() {
        super();
    }

    public void saveBag(Bag bag) {
        bag.gems.forEach((gem) -> {
            this.gems.add(gem);
        });
    }

    /**
     * method that clean the gems in the chest.
     */
    public void loseEverything() {
        this.gems.clear();
    }
}
