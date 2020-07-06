package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author selim
 */
public class GameInfo implements Serializable {

    private final int id;
    private final int idPlayer1;
    private final int idPlayer2;
    private final int result;

    public GameInfo(int id, int idPlayer1, int idPlayer2, int result) {
        this.id = id;
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public int getIdPlayer1() {
        return idPlayer1;
    }

    public int getIdPlayer2() {
        return idPlayer2;
    }

    public int getResult() {
        return result;
    }
    
    public List<Integer> getAll(){
    
        return Arrays.asList(id,idPlayer1,idPlayer2,result);
    }
    
    
    
}
