package Persistence;

import Business.Entities.User;
import Persistence.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

/** Classe on guardem totes les dades dels usuaris a la base de dades.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */
public class SQLUserDAO implements UserDAO {

    public SQLUserDAO() {}

    /**
     *Afegeix un usuari a la base de dades.
     */
    public void addUser(User user) {
        String query = "INSERT INTO Users(username, email, password) VALUES (" +
                "'" + user.getUsername() + "', '" + user.getEmail() + "', '" + user.getPassword() + "');";
        System.out.println(query);
        DBConnector.getInstance().insertQuery(query);
    }

    /**
     *Retorna tots els usuaris desde la base de dades.
     */
    public LinkedList<User> getAllUsers() {
        LinkedList<User> users = new LinkedList<>();
        String query = "SELECT * FROM Users;";
        System.out.println(query);
        ResultSet resultat = DBConnector.getInstance().selectQuery(query);
        try {
            while (resultat.next()) {
                String username = resultat.getString("username");
                String correu = resultat.getString("email");
                String contrasenya = resultat.getString("password");

                users.add(new User(username, correu, contrasenya));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     *Obté usuari de la base de dades.
     */

    public User getUser(String info) {
        User user = null;
        String query = "SELECT * FROM between_us.Users WHERE username LIKE '" + info + "' OR email LIKE '" + info + "';";
        System.out.println(query);
        ResultSet resultat = DBConnector.getInstance().selectQuery(query);

        try {
            while (resultat.next()) {
                String username = resultat.getString("username");
                String email = resultat.getString("email");
                String password = resultat.getString("password");
                user = new User(username, email, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     *Retorna cert o fals a l'hora de corroborar el password de l'usuari.
     */
    public boolean checkPassword(String info, String password) {
        String query = "SELECT username FROM Users WHERE (username LIKE '" + info + "' OR email LIKE '" + info + "') AND password LIKE '" + password + "';";
        System.out.println(query);
        if (DBConnector.getInstance().selectQuery(query) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *Esborra usuari de la base de dades.
     */
    public void deleteUser(String username) {
        String query3 = "DELETE FROM Results WHERE username LIKE '" + username + "';";
        DBConnector.getInstance().deleteQuery(query3);
        String query = "DELETE FROM Matches WHERE username_player LIKE '" + username + "';";
        DBConnector.getInstance().deleteQuery(query);
        String query2 = "DELETE FROM Users WHERE username LIKE '" + username + "';";
        DBConnector.getInstance().deleteQuery(query2);
    }


    //exemple update
    /*public static void extUserSaldo(String name, Float saldo, Float saldoActual) {
        String query = "UPDATE User SET saldo = " + (saldoActual-saldo) + " WHERE username = '" + name + "';";
        System.out.println(query);
        DBConnector.getInstance().updateQuery(query);
    }*/


}
