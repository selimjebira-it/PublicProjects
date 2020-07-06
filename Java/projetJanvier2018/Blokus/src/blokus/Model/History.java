package blokus.Model;

/**
 *
 * @author selim
 */
public class History {
    private final StringBuilder buff;

    public History() {
        
        this.buff = new StringBuilder();
    }
    
    public void addText(String text){
        
        buff.append(text).append(System.getProperty("line.separator"));
    }
    
    public String getText(){
        
        return buff.toString();
    }
    
    public void clear(){
        
        buff.delete(0, buff.length());
    }
    
    
}
