package Persistence;

import Business.Entities.Match;

import java.util.ArrayList;
import java.util.LinkedList;

/** Interfície que permet coleccionar els mètodes i propietats de MatchDAO.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */

public interface MatchDAO {
    ArrayList<Match> getAllMatches(String username);
    Match getMatch(String gamename, String username);
    void addMatch(Match match);
    void deleteMatch(String username, String name);
}
