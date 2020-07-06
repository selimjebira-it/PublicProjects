package tictactoeServer.model.db;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import model.GameInfo;
import model.HistoryInfo;
import model.Profile;
import model.RecapInfo;
import tictactoeServer.model.game.Game;
import tictactoeServer.model.game.Player;

/**
 *
 * @author selim
 */
public final class DataBase {

    private final String url = "jdbc:mysql://localhost:3306/javadb?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private final String user = "java";
    private final String mdp = "selimjava";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    private final String tableUser = "javadb.user(name,mdp,date)";
    private final String tableGame = "javadb.game(idPlayer1,idPlayer2,result)";
    private final String tableHistory = "javadb.history(idGame,round,played)";

    protected Connection connection;

    public DataBase() {

        try {
            forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * method to open the conenction to the db
     */
    public void connect() {

        try {

            connection = DriverManager.getConnection(url, user, mdp);

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     * method to close the connection of the db
     */
    public void disconnect() {

        try {
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     * method to clear all profiles
     *
     * @return
     */
    public boolean clearAllProfiles() {

        try (Statement st = connection.createStatement()) {

            st.executeUpdate("DELETE FROM javadb.user ;");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * method to create a profile in the db
     *
     * @param name name of user
     * @param mdp password of the user
     * @return
     */
    public synchronized boolean createProfile(String name, String mdp) {

        if (!isNameFree(name)) {
            return false;
        }
        String sql = "INSERT INTO " + tableUser + " VALUES( ? ,MD5( ? ) ,NOW());";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {

            prepared.setString(1, name);
            prepared.setString(2, mdp);
            prepared.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * method to register a game in the database
     *
     * @param game the game to register
     */
    public synchronized void registerGame(Game game) {

        int idOne = game.getPlayer1().getId();
        int idTwoo = game.getPlayer2().getId();
        int result = game.getWinner() == null ? 0 : game.getWinner().getId();
        createGame(idOne, idTwoo, result);

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("SELECT max(id) FROM javadb.game;");
            if (rs.next()) {
                int idGame = rs.getInt(1);
                List<Integer> played = game.getPlayed();
                for (int index = 0; index < played.size(); index++) {
                    int move = played.get(index);
                    if (move != -1 || index == 0) {
                        createHistory(idGame, index, move);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateProfile(game.getPlayer1());
        updateProfile(game.getPlayer2());

    }

    /**
     * method to retrive a profile with a name and mdp
     *
     * @param name
     * @param mdp
     * @return
     */
    public Profile readProfile(String name, String mdp) {

        String sql = "SELECT id,victories,defeats,draws FROM javadb.user WHERE name = ? AND mdp = MD5( ? )";

        try (PreparedStatement prepared = connection.prepareStatement(sql)) {

            prepared.setString(1, name);
            prepared.setString(2, mdp);
            ResultSet rs = prepared.executeQuery();
            if (rs.next()) {

                int id, victories, defeats, draws;
                id = rs.getInt("id");
                victories = rs.getInt("victories");
                defeats = rs.getInt("defeats");
                draws = rs.getInt("draws");
                return new Profile(id, name, victories, defeats, draws);

            }
            return null;

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * method to retrieve the game from the db
     *
     * @param idPlayer id of the player concerned
     * @return list of gameInfo
     */
    public synchronized List<GameInfo> retrieveGames(int idPlayer) {

        String sql = "SELECT id,idPlayer1,idPlayer2,result FROM javadb.game WHERE idPlayer1 = ? OR idPlayer2 = ? ;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setInt(1, idPlayer);
            prepared.setInt(2, idPlayer);
            ResultSet rs = prepared.executeQuery();
            int idGame, idPlayer1, idPlayer2, result;
            GameInfo gameInfo;
            List<GameInfo> games = new ArrayList<>();
            while (rs.next()) {

                idGame = rs.getInt("id");
                idPlayer1 = rs.getInt("idPlayer1");
                idPlayer2 = rs.getInt("idPlayer2");
                result = rs.getInt("result");
                gameInfo = new GameInfo(idGame, idPlayer1, idPlayer2, result);
                games.add(gameInfo);
            }
            return games;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * method to get a recap of gamePlayed by 2 users
     *
     * @param idPlayer1 the first player
     * @param idPlayer2 the second player
     * @return
     */
    public RecapInfo getRecap(final int idPlayer1, final int idPlayer2) {
        
        String sql = "SELECT id,idPlayer1,idPlayer2,result FROM javadb.game WHERE (idPlayer1 = ? AND idPlayer2 = ? ) OR (idplayer1 = ? AND idPlayer2 = ?)  ;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {

            prepared.setInt(1, idPlayer1);
            prepared.setInt(2, idPlayer2);
            prepared.setInt(3, idPlayer2);
            prepared.setInt(4, idPlayer1);
            ResultSet rs = prepared.executeQuery();
            int idGame, idOne, idTwoo, result;
            GameInfo gameInfo;
            List<GameInfo> games = new ArrayList<>();
            while (rs.next()) {

                idGame = rs.getInt("id");
                idOne = rs.getInt("idPlayer1");
                idTwoo = rs.getInt("idPlayer2");
                result = rs.getInt("result");
                gameInfo = new GameInfo(idGame, idOne, idTwoo, result);
                games.add(gameInfo);
            }
            if (games.isEmpty()) {
                return null;

            }
            int victories, defeats, draws;
            victories = defeats = draws = 0;
            for (int i = 0; i < games.size(); i++) {

                result = games.get(i).getResult();
                if (result == 0) {

                    draws++;
                } else if (result == idPlayer1) {

                    victories++;
                } else {

                    defeats++;
                }
            }
            return new RecapInfo(idPlayer1, idPlayer2, victories, defeats, draws);

        } catch (SQLException ex) {

            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * getAllRecaps of games
     *
     * @return
     */
    public List<RecapInfo> getAllRecap() {

        String sql = "SELECT id,name FROM javadb.user;";
        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);
            List<Pair<Integer,String>> idPlayers = new ArrayList<>();
            int id;
            String name;
            while (rs.next()) {
                id = rs.getInt(1);
                name  = rs.getNString(2);
                idPlayers.add(new Pair(id,name));
            }
            int idOne, idTwoo, size;
            String nameTwoo;
            RecapInfo recapInfo;
            List<RecapInfo> result = new ArrayList<>();
            size = idPlayers.size();
            for (int i = 0; i < size; i++) { //Certainement pas la meilleure façon de faire (je parcours beaucoup de fois)
                idOne = idPlayers.get(i).getKey();
                name = idPlayers.get(i).getValue();
                for (int j = 0; j < size; j++) {
                    idTwoo = idPlayers.get(j).getKey();
                    nameTwoo = idPlayers.get(j).getValue();
                    if (idOne != idTwoo) {
                        recapInfo = getRecap(idOne, idTwoo);
                        if (recapInfo != null && !result.contains(recapInfo)) {
                            result.add(new RecapInfo(recapInfo, name, nameTwoo));
                        }
                    }
                }

            }
            return result;

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * method to retrieve history from db
     *
     * @param idGame id of the game
     * @return usefull information about the game
     */
    public synchronized HistoryInfo readHistory(int idGame) {

        String sql = "SELECT round,played FROM javadb.history WHERE idGame = ? ;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {

            prepared.setInt(1, idGame);
            ResultSet rs = prepared.executeQuery();
            int round, played;
            List<Integer> plays = new ArrayList<>();
            while (rs.next()) {
                round = rs.getInt("round");
                played = rs.getInt("played");
                plays.set(round, played);

            }

            return plays.isEmpty() ? null : new HistoryInfo(plays);

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private boolean isNameFree(String name) {

        String sql = "SELECT name FROM javadb.user WHERE name = ? ;";
        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            return !rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Name Is not Free");
            return false;
        }
    }

    public synchronized boolean updateProfile(Player user) {

        String sql = "UPDATE javadb.user SET victories = ? , defeats = ? , draws = ? WHERE id = ? ;";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {

            prepared.setInt(1, user.getVictories());
            prepared.setInt(2, user.getDefeats());
            prepared.setInt(3, user.getDraws());
            prepared.setInt(4, user.getId());
            prepared.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean createGame(int idPlayer1, int idPlayer2, int result) {

        if (result != 0 && (result != idPlayer1 && result != idPlayer2) || idPlayer1 == idPlayer2) {
            throw new IllegalArgumentException("Result incorrect");
        }

        String sql = "INSERT INTO " + tableGame + " VALUES( ? , ? , ? );";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {

            prepared.setInt(1, idPlayer1);
            prepared.setInt(2, idPlayer2);
            prepared.setInt(3, result);
            prepared.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean createHistory(int idGame, int round, int played) {

        String sql = "INSERT INTO " + tableHistory + " VALUES( ? , ? , ? );";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {

            prepared.setInt(1, idGame);
            prepared.setInt(2, round);
            prepared.setInt(3, played);
            prepared.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
