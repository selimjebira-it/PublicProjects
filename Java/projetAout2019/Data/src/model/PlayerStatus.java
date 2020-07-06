package model;

/**
 *
 * @author selim
 */
public enum PlayerStatus {
    ONLINE("ON LINE"), INGAME("IN GAME");
    
    private final String name;

    private PlayerStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    

}
