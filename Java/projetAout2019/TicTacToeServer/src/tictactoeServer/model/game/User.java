package tictactoeServer.model.game;

import java.util.Objects;

/**
 *
 * @author selim
 */
public class User {

    private final int id;
    private final String name;

    private boolean connected;
    protected boolean inGame;

    protected int victories;
    protected int defeats;
    protected int draws;

    /**
     * constructor of a User
     * @param id id of the user
     * @param name name of the user
     * @param victories victories of the user
     * @param defeats defeats of the user
     * @param draws draws of the user
     */
    public User(int id, String name, int victories, int defeats, int draws) {

        this.id = id;
        this.name = name;
        this.victories = victories;
        this.defeats = defeats;
        this.draws = draws;
        setConnected(true);
    }

    
   //GETTERS
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

    public boolean isConnected() {
        return connected;
    }

    public boolean isInGame() {
        return inGame;
    }
    //SETTERS
    private void setConnected(boolean connected) {
        this.connected = connected;
    }

    protected void setInGame(boolean inGame) {

        this.inGame = inGame;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        return hash;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        
        return true;
    }


}
