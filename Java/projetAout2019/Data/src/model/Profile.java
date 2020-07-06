package model;

import java.io.Serializable;

/**
 *
 * @author selim
 */
public class Profile implements Serializable {

    private final int id;
    private final String name;
    private final int victories;
    private final int defeats;
    private final int draws;
    private final double score;
    private final int totalGames;

    public Profile(int id, String name, int victories, int defeats, int draws) {

        this.id = id;
        this.name = name;
        this.victories = victories;
        this.defeats = defeats;
        this.draws = draws;
        this.score = victories == 0 ? 0 :((double) victories / (victories + defeats)) * 100;
        this.totalGames = victories + defeats + draws;
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public double getScore() {
        return score;
    }

    public int getTotalGames() {
        return totalGames;
    }


    @Override
    public String toString() {
        return "UserInfo{" + "id=" + id + ", name=" + name + ", victories=" + victories + ", defeats=" + defeats + ", draws=" + draws + '}';
    }

}
