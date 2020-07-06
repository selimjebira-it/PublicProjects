package g49853.diamond.model;

public enum HazardType {
    STONE_BALL("Stone Ball"),
    GIANT_SPIDERS("Giant Spider"),
    SNAKES("Snake"),
    BATTERING_RAM("Battering Ram"),
    LAVA_FIELD("Lava Field"),
    POISON("Poison");

    private String name;

    /**
     * class that contains Enumeration for the state of the player
     *
     * @param name
     */
    HazardType(String name) {
        this.name = name;
    }

    String getValue() {
        return this.name;
    }
}
