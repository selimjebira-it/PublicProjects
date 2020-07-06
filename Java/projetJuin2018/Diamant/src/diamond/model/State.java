package g49853.diamond.model;

public enum State {
    LEAVING("is leaving"),
    EXPLORING("is exploring"),
    CAMPING("is camping");

    private String name;

    /**
     * class that contains Enumeration for the state of the player
     *
     * @param name
     */
    State(String name) {
        this.name = name;
    }

    /**
     *
     * @return the sentence linked to the enum.
     */
    public String getValue() {
        return this.name;
    }

}
