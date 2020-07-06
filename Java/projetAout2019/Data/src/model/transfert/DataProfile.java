
package model.transfert;

import model.Data;
import model.DataType;
import model.Profile;

/**
 *
 * @author selim
 */
public class DataProfile extends Data {

    public DataProfile(Profile infos) {
        super(DataType.PROFIL, infos);
    }
    
    
}
