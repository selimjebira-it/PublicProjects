
package model.transfert;

import java.util.List;
import javafx.util.Pair;
import model.Data;
import model.DataType;
import model.GameInfo;

/**
 *
 * @author selim
 */
public class DataGame extends Data{
    
    public DataGame(int idPlayer, List<GameInfo> games) {
        super(DataType.GAME, new Pair(idPlayer,games));
    }
    
}
