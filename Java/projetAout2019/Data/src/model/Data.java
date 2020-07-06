
package model;

import java.io.Serializable;

/**
 *
 * @author selim
 */
public class Data implements Serializable{
    
    private final DataType type;
    private final Object content;

    public Data(DataType type, Object content) {
        
        this.type = type;
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public DataType getType() {
        return type;
    }
    
    
}
