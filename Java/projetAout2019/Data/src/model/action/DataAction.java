/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.action;

import model.Data;
import model.DataType;

/**
 *
 * @author selim
 */
public final class DataAction extends Data{

    public DataAction(ActionType type) {
        super(DataType.ACTION, type);
    }
    
}
