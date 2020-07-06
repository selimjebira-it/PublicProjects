/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author selim
 */
public class RecapInfo implements Serializable {
    
    private final int idPlayer1;
    private String namePlayer1;
    private final int idPlayer2;
    private String namePlayer2;
    private final int victories;
    private final int defeats;
    private final int draws;

    public RecapInfo(int idPlayer1, int idPlayer2, int victories, int defeats, int draws) {
        
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.victories = victories;
        this.defeats = defeats;
        this.draws = draws;
    }
    
    public RecapInfo(int idPlayer1,String nameOne,int idPlayer2,String nameTwoo,int victories,int defeats,int draws){
    
        this(idPlayer1,idPlayer2,victories,defeats,draws);
        namePlayer1 = nameOne;
        namePlayer2 = nameTwoo;
    }
    
    public RecapInfo(RecapInfo other,String nameOne,String nameTwoo){
    
        this(other.idPlayer1,nameOne,other.idPlayer2,nameTwoo,other.victories,other.defeats,other.draws);
    }
    
    

    public int getIdPlayer1() {
        return idPlayer1;
    }

    public int getIdPlayer2() {
        return idPlayer2;
    }

    public int getVictories() {
        return victories;
    }

    public int getDefeats() {
        return defeats;
    }

    public int getDraws() {
        return draws;
    }

    public String getNamePlayer1() {
        return namePlayer1;
    }

    public String getNamePlayer2() {
        return namePlayer2;
    }
    
    



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecapInfo other = (RecapInfo) obj;
        if((this.idPlayer1 == other.idPlayer1 && this.idPlayer2 == other.idPlayer2 ) || (this.idPlayer2 == other.idPlayer1 && this.idPlayer1 == other.idPlayer2)){
            return true;
        }else{
            return false;
        }
        
    }
    
    
    
    
}
