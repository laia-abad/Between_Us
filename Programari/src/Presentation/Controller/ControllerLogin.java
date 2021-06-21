package Presentation.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Business.Entities.User;
import Business.LogicUser;
import Presentation.Views.ViewLogin;

/**
 * Controlador que permet accedir a la nostra aplicació segons un nom d'usuari, juntament introduïnt la seva paraula de pas,
 * o bé accedir a una vista per registrar un nou usuari.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */
public class ControllerLogin implements ActionListener {

    /**
     * Atributs que ens permet identificar usuari.
     * @param view variable que referencia a la nostra vista ViewLogin.
     * @param user variable del tipus User on desarem els canvis amb la informació de l'usuari.
     * @param username nom d'usuari al que volem accedir.
     * @param password  paraula de pas de l'usuari al que volem accedir.
     */

    private ViewLogin view;
    private User user;
    private String username;
    private String password;
    private MainController mainController; //flag = 0 (no hay boton) / flag = 1 (inicia) / flag = 2 (registro)

    /**
     * Constructor que rep la vista ViewLogin, i on creem una instància d'usuari.
     * @param view vista rebuda de ViewLogin.
     */
    public ControllerLogin(ViewLogin view){
        this.view = view;
        user = new User();
    }

    /**
     * Getters i Setters per modificar o accedir a les nostres variables.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public String returnUsername(){
        return username;
    }

    public String returnPassword(){
        return password;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Metode que serà cridat cada cop que accionem els botons de la vista, fent override desde la interfície
     * en funció de les opcions que disposem. Mitjançant un switch realitzem les diferents funcions, Envía les dades de l'usuari,
     * o opció d'accés a la vista Registrar usuari.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ViewLogin.BTN_INICIA:
                username = view.getUsernameInput();
                password = view.getPasswordInput();
                LogicUser login = new LogicUser();
                ArrayList errores = login.login(username, password);

                if (errores.size() == 0) {
                    user.setUsername(username);
                    user.setPassword(password);
                    mainController.setUser(user);
                    view.setVisible(false);
                    mainController.showVistaGameManagement();

                } else {
                    view.resetInput();
                    String stringErrores = "";
                    for (int i = 0; i < errores.size(); i++) {
                        stringErrores = stringErrores + errores.get(i) + "\n";
                    }
                    view.createDialogError(stringErrores, "Error al hacer login");
                }
                break;

            case ViewLogin.BTN_REGISTRO:
                view.setVisible(false);
                mainController.showVistaRegister();
                break;
        }
    }


}
