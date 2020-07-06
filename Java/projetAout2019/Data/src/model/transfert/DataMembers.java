/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.transfert;

import java.util.List;
import model.Data;
import model.DataType;
import model.Profile;

/**
 *
 * @author selim
 */
public class DataMembers extends Data{
    
    public DataMembers(List<Profile> users) {
        super(DataType.MEMBERS, users);
    }
    
}
