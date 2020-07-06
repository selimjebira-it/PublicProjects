
package model.transfert;

import javafx.util.Pair;
import model.Data;
import model.DataType;

/**
 *
 * @author selim
 */
public final class DataMsg extends Data {
    
    public DataMsg(MsgType type, String msg) {
        super(DataType.MESSAGE, new Pair(type,msg));
    }
    
}
