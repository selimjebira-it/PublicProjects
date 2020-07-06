package model.transfert;

import javafx.util.Pair;
import model.Data;
import model.DataType;

/**
 *
 * @author selim
 */
public class DataRegistration extends Data{

    public DataRegistration(String name,char[] mdp) {
        super(DataType.REGISTER, new Pair(name,mdp));
    }
    
    
}
