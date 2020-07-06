
package model.transfert;

import javafx.util.Pair;
import model.Data;
import model.DataType;
import model.HistoryInfo;

/**
 *
 * @author selim
 */
public class DataHistory extends Data{
    
    public DataHistory(int idGame, HistoryInfo infos) {
        super(DataType.HISTORY, new Pair(idGame,infos));
    }
    
}
