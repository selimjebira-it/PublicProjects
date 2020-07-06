package g49853.diamond.model;

import java.util.List;

public class Relic implements Tile {

    private int valueInDiamonds = 1;

    @Override
    public String getValue() {
        return "Relic";
    }

    public int getValueInDiamonds() {
        return this.valueInDiamonds;
    }

    public boolean canBeTaken(List<Explorer> explorers) {
        boolean cdt1 = explorers.size() == 1;
        boolean cdt2 = false;
        if (cdt1) {
            cdt2 = explorers.get(0).getState() == State.LEAVING;
        }
        return cdt1 && cdt2;
    }

    public void convertGameValue(int nbTakenRelics) {
        if (nbTakenRelics >= 3) {
            this.valueInDiamonds = 2;
        }
    }

    @Override
    public void explore(List<Explorer> explorers) {
        if (canBeTaken(explorers)) {
            for (int x = 0; x < this.valueInDiamonds; x++) {
                explorers.get(0).addToBag(Gem.DIAMOND);
            }
        }
    }

}
