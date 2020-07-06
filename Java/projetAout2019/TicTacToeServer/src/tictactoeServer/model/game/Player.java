package tictactoeServer.model.game;

/**
 *
 * @author selim
 */
public final class Player extends User {

    private final Jeton jeton;

    public Player(User user, Jeton jeton) {

        super(user.getId(), user.getName(), user.getVictories(), user.getDefeats(), user.getDraws());
        this.jeton = jeton;
        setInGame(true);//lock the user 
    }

    public Jeton getJeton() {
        return jeton;
    }

    /**
     * method to updatePlayer
     * @param game the game they played in
     */
    public void updatePlayer(Game game) {

        if (game.isFinished()) {

            if (game.getWinner().getId() == super.getId()) {
                
                addVictory();
            }else if(game.getWinner() == null){
            
                addDraw();
            }else{
                
                addDefeat();
            }
            super.setInGame(false); //release the user 
        }
    }

    private void addVictory() {
        
        super.victories++;
    }

    private void addDefeat() {
        
        super.defeats++;
    }

    private void addDraw() {
        
        super.draws++;
    }

}
