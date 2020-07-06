package g49853.diamond.model;

public enum Gem {
    DIAMOND(5),
    RUBIS(1);

    private final int value;

    Gem(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }
}
