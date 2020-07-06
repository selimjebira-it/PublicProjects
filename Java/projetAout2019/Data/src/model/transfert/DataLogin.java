/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.transfert;

import javafx.util.Pair;
import model.Data;
import model.DataType;

/**
 *
 * @author selim
 */
public class DataLogin extends Data{

    public DataLogin(String name,char[] mdp) {
        super(DataType.LOGIN, new Pair(name,mdp));
    }
    
}
