
package Persistence;

import Business.Entities.Match;
import Business.Entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */

public class SQLMatchDAO implements MatchDAO {

    /**
     * Es crea un ArrayList i guarda totes les dades de tots els Matches.
     */
    public ArrayList<Match> getAllMatches(String username) {
        ArrayList<Match> matches = new ArrayList<>();
        String query = "SELECT * FROM between_us.Matches WHERE username_player LIKE '" + username + "';";
        System.out.println(query);
        ResultSet resultat = DBConnector.getInstance().selectQuery(query);
        try {
            while (resultat.next()) {
                String name = resultat.getString("name");
                String username_player = resultat.getString("username_player");
                int color_player = resultat.getInt("color_player");
                int n_impostors = resultat.getInt("n_impostors");
                int n_players = resultat.getInt("n_players");
                String map = resultat.getString("map");
                boolean ended = resultat.getBoolean("ended");
                String filename = resultat.getString("filename");

                matches.add(new Match(name, username_player, color_player, n_impostors, n_players, map, ended, filename));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    /**
     * Guarda les dades del Match.
     */
    public Match getMatch(String gamename, String username) {
        Match match = null;
        String query = "SELECT * FROM between_us.Matches WHERE name LIKE '" + gamename + "' AND username_player LIKE '" + username + "';";
        System.out.println(query);
        ResultSet resultat = DBConnector.getInstance().selectQuery(query);

        try {
            while (resultat.next()) {
                String name = resultat.getString("name");
                String username_player = resultat.getString("username_player");
                int color_player = resultat.getInt("color_player");
                int n_impostors = resultat.getInt("n_impostors");
                int n_players = resultat.getInt("n_players");
                String map = resultat.getString("map");
                boolean ended = resultat.getBoolean("ended");
                String filename = resultat.getString("filename");

                match = new Match(name, username_player, color_player, n_impostors, n_players, map, ended, filename);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return match;
    }

    /**
     * Afegeix un Match
     */
    public void addMatch(Match match) {
        String query = "INSERT INTO Matches(name, username_player, color_player, n_impostors, n_players, map, ended, filename) VALUES (" +
                "'" + match.getName() + "', '" + match.getUsername_player() + "', '" + match.getColor_player() + "', '" + match.getN_impostors() + "', '" + match.getN_players() + "', '" + match.getMap() + "', '" + match.isEnded() + "', '" + match.getFilename() + "');";
        System.out.println(query);
        DBConnector.getInstance().insertQuery(query);
    }

    /**
     * Esborra un Match
     */
    public void deleteMatch(String username, String name) {
        String query = "DELETE FROM Matches WHERE username_player LIKE '" + username + "' AND name LIKE '" + name + "';";
        DBConnector.getInstance().deleteQuery(query);
    }
}
