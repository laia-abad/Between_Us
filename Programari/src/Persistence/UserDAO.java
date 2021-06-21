package Persistence;

import Business.Entities.User;

import java.util.LinkedList;

/** Interfície que permet coleccionar els mètodes i propietats de UserDAO.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */

public interface UserDAO {
    void addUser(User user);
    LinkedList<User> getAllUsers();
    User getUser(String info);
    boolean checkPassword(String info, String password);
    void deleteUser(String username);

}
