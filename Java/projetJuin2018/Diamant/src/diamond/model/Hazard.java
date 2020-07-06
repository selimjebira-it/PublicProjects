package g49853.diamond.model;

import java.util.List;

public class Hazard implements Tile {

    boolean exlorersEscapeReason;
    HazardType type;

    public Hazard(HazardType hazardType) {
        this.type = hazardType;
    }

    public HazardType getType() {
        return type;
    }

    public boolean isExlorersEscapeReason() {
        return exlorersEscapeReason;
    }

    /**
     * set explorerEscape to true
     */
    public void escape() {
        this.exlorersEscapeReason = true;
    }

    @Override
    public void explore(List<Explorer> explorers) {
        Explorer expl;
        Explorer explRich;
        int rand = (int) ((Math.random() * (explorers.size() - 1)) + 1);
        if (this.type == HazardType.POISON) {
            if (explorers.size() == 1) {
                expl = explorers.get(0);
                expl.poisonned();
            } else {
                expl = explorers.get(rand);
                expl.poisonned();
                explRich = whoIsRich(explorers);
                explRich.helping();
            }
        }
    }

    /**
     * method that return who has the highest amount of gems.
     *
     * @param explorers list of explorers
     * @return explorer who has the highest amount of gems.
     */
    public Explorer whoIsRich(List<Explorer> explorers) {
        Explorer explMaxRub = new Explorer("No Rich BOy");
        int max = 0;
        int fortune = 0;
        for (Explorer ex : explorers) {
            fortune = ex.getFortune();
            fortune += ex.getBag().getValue();
            if (fortune > max) {
                explMaxRub = ex;
                max = fortune;
            }
        }
        return explMaxRub;
    }

    @Override
    public String getValue() {
        return this.type.name();
    }

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
        final Hazard other = (Hazard) obj;
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}
