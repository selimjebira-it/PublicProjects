package blokus.Enum;



import blokus.Model.Position;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author selim
 */
public enum Shape implements Serializable {

    ONE(22),//22 is center(0,0)
    TWOO(12, 22),//12 is bottom (0,1)
    THREE(12, 12, 22),
    FOUR(12, 21, 22),//21 is right(1,0)
    FIVE(12, 12, 12, 22),
    SIX(12, 12, 24, 22),//24 is left(-1,0)
    SEVEN(12, 21, 14, 22),//14 is bottomLeft(-1,1)
    HEIGHT(12, 21, 42, 22),//42 is top (0,-1)
    NINE(21, 12, 21, 22),
    TEN(12, 12, 12, 12, 22),
    ELEVEN(12, 12, 12, 24, 22),
    TWELVE(12, 12, 24, 12, 22),
    THIRTEEN(12, 12, 24, 42, 22),
    FOURTEEN(21, 12, 12, 24, 22),
    FIFTHEEN(12, 21, 14, 12, 22),
    SIXTEEN(12, 14, 21, 21, 22),
    SEVENTEEN(12, 12, 21, 21, 22),
    HEIGHTEEN(21, 12, 21, 12, 22),
    NINETEEN(12, 21, 21, 12, 22),
    TWENTY(12, 21, 21, 14, 22),
    TWENTYONE(14, 21, 21, 14, 22),
    TWENTYTWOO(12,12,21,42,42,22);

    private final int[] dir;
    private final ArrayList<Position> positions;

    private Shape(int... dir) {

        this.dir = dir;
        positions = new ArrayList<>();
        init();
    }

    private void init() {

        Position pos;
        for (int i : dir) {
            pos = new Position(convert(i % 5), convert(i / 10));
            positions.add(pos);
        }
    }

    private int convert(int i) {

        switch (i) {
            case 4:
                return -1;
            case 1:
                return 1;
            default:
                return 0;
        }
    }

    public ArrayList<Position> getPositions() {

        return (ArrayList<Position>) positions.clone();
    }

    @Override
    public String toString() {

        StringBuilder buff = new StringBuilder();
        positions.forEach(p -> buff.append(p));
        return buff.toString();
    }

}
