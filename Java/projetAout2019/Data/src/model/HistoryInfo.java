package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author selim
 */
public class HistoryInfo implements Serializable{
    
    private final List<Integer> played;

    public HistoryInfo(List<Integer> played) {
        this.played = played;
    }

    public List<Integer> getPlayed() {
        return played;
    }
    
    
    
    
}
