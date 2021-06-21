package Business;

import Business.Entities.User;
import Persistence.SQLUserDAO;
import Persistence.UserDAO;

import java.util.ArrayList;

/**
 * Clase que implementa la lógica del usuario (login, registro, contraseñas)
 * y se comunica con la base de datos
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */
public class LogicUser {

    private static final int PASSWORD_LENGTH = 8;

    /**
     * Método que busca al usuario en la base de datos y
     * si no se puede loguear el usuario devuelve los errores correspondientes.
     *
     * @param credentials del usuario
     * @param password    contrasena del usuario
     * @return errores que haya tenido al loguerse
     */
    public ArrayList login(String credentials, String password) {
        UserDAO userDAO = new SQLUserDAO();
        User user = userDAO.getUser(credentials);
        ArrayList errores = new ArrayList();
        if (user != null) {
            System.out.println("\nEL USUARIO INTRODUCIDO ES VALIDO\n");

            if (user.getPassword().equals(password)) {
                System.out.println("\nTE HAS LOGEADO CORRECTAMENTE\n");

            } else {
                System.out.println("\nLA CONTRASEÑA ES INCORRECTA\n");
                errores.add("La contraseña es incorrecta.");
            }
        } else {
            System.out.println("\nEL USUARIO INTRODUCIDO NO ES VALIDO\n");
            errores.add("El usuario introducido no es valido.");
        }
        return errores;
    }

    /**
     * Método que loguea al usuario
     *
     * @param username del usuario
     * @return el usuario en si
     */
    public User loginUser (String username) {
        UserDAO userDAO = new SQLUserDAO();
        return userDAO.getUser(username);
    }

    /**
     * Método que registra al usuario en la base de datos
     * a traves de los 4 parametros que pide la vista del registro
     *
     * @param newUsername        del usuario a registrar
     * @param newEmail
     * @param newPassword
     * @param newPasswordConfirm
     * @return arraylist de errores
     */
    public ArrayList register(String newUsername, String newEmail, String newPassword, String newPasswordConfirm){
        UserDAO userDAO = new SQLUserDAO();
        ArrayList errores = new ArrayList<String>();

        if (userDAO.getUser(newUsername) != null) {
            //System.out.println("Username ya existe");
            errores.add("El nombre de usuario ya existe");
        }

        if (!isValidEmailAddress(newEmail)) {
            //System.out.println("Email mal");
            errores.add("Por favor introduzca un correo electrónico válido");
        } else if (userDAO.getUser(newEmail) != null) {
            //System.out.println("Email ya existe");
            errores.add("El correo electrónico ya existe");
        }

        if (!newPassword.equals(newPasswordConfirm)) {
            errores.add("Las contraseñas no coinciden");
            //System.out.println("Passwords no coinciden");
        } else {
            if (!isValidPassword(newPassword)){
                errores.add("Contraseña no válida (debe contener 8 ó más caracteres, mayúsculas, minúsculas y números");
                //System.out.println("Password no valido");
            }
        }


        if (errores.size() == 0) {
            User user = new User(newUsername,newEmail,newPassword);
            userDAO.addUser(user);
        }

        return errores; //retornar exception o string amb missatge
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean isValidPassword(String password) {
        if (password.length() < PASSWORD_LENGTH) return false;
        boolean num = false;
        boolean upperCase = false;
        boolean lowerCase = false;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= '0' && ch <= '9') num = true;
            else if (ch >= 'A' && ch <= 'Z') upperCase = true;
            else if (ch >= 'a' && ch <= 'z') lowerCase = true;
        }
        return (lowerCase && upperCase && num);
    }

    /**
     * Método que borra al usuario de la base de datos.
     *
     * @param username del usuario que se va a borrar
     */
    public void deleteUser (String username) {
        UserDAO userDAO = new SQLUserDAO();
        userDAO.deleteUser(username);
    }

    public User logoutUser () {
        return null;
    }
}
