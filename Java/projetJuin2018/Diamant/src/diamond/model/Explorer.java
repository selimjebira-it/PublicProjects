package g49853.diamond.model;

import java.util.Objects;

public class Explorer {

    private String pseudonyme;
    Bag bag;
    private State state;
    private Chest chest;
    private boolean poisoned;

    /**
     * Construct an explorer and setting his bag and his state to default
     *
     * @param name name of the explorer
     */
    public Explorer(String name) {
        this.pseudonyme = name;
        this.bag = new Bag();
        this.state = State.CAMPING;
        this.chest = new Chest();
    }

    /**
     * method that changes the state of the explorer from camping to exploring
     */
    public void startExploration() {
        this.state = State.EXPLORING;
    }

    /**
     * get value of attribute pseudonym
     *
     * @return value of pseudonym
     */
    public String getPseudonyme() {
        return this.pseudonyme;
    }

    /**
     * get value of attribute bag which is a Bag
     *
     * @return
     */
    public Bag getBag() {
        return this.bag;
    }

    /**
     * get value of attribute state which is a State(enumeration)
     *
     * @return value of the enumeration
     */
    public State getState() {
        return this.state;
    }

    /**
     * method that return the number of gems the explorer possesses
     *
     * @return the number of rubies
     */
    public int getFortune() {
        int res = this.chest.getValue();
        if (this.chest.gems.isEmpty()) {
            res = 0;
        }
        return res;
    }

    public boolean getPoisoned() {
        return this.poisoned;
    }

    /**
     * method that adds rubies into the bag of the explorer
     *
     * @param gem a gem to add to the bag of the explorer
     */
    public void addToBag(Gem gem) {
        this.bag.addGem(gem);
    }

    /**
     * method that change attribute state which is exploring to leaving
     */
    public void takeDecisionToLeave() {
        this.state = State.LEAVING;
    }

    /**
     * set state to camping and save the bag.
     */
    public void reachCamp() {
        if (getPoisoned()) {
            this.poisoned = false;
        }
        this.chest.saveBag(this.bag);
        this.bag.loseContent();
        this.state = State.CAMPING;
    }

    /**
     * method that reset the bag of the explorer and turn his state to camping
     */
    public void runAway() {
        this.bag.loseContent();
        reachCamp();

    }

    /**
     * method that poisoned the explorer and make him lose his bag and his chest
     * to pay the pills.
     */
    public void poisonned() {
        this.poisoned = true;
        this.bag.loseContent();
        this.chest.loseEverything();
        takeDecisionToLeave();
    }

    /**
     * method that make the explorer reach the camp.
     */
    public void helping() {
        takeDecisionToLeave();
    }

    /**
     * Classic equals method
     *
     * @param obj the object to verify the equality
     * @return the compare between the object and this object
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
        final Explorer other = (Explorer) obj;
        if (!Objects.equals(this.pseudonyme, other.pseudonyme)) {
            return false;
        }
        return true;
    }

}
