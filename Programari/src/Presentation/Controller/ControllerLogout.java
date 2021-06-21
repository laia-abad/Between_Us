package Presentation.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Business.Entities.User;
import Business.LogicUser;
import Presentation.Views.ViewLogout;

/**
 * Controlador que permet a l'usuari deixar l'aplicació, o eliminar usuari, tot seguit re-dirigeix a la vista de login usuari.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */

public class ControllerLogout implements ActionListener{

    /**
     * Atributs que emmagatzema la vista, amb la finalitat d'administrar les dades. Modificarem
     * després de que fem ús dels botons de la vista ViewGameManagement.
     *  @param viewLogout variable del tipus vista ViewLogout.
     *  @param logiclogout listaPartidas, llista de les partides.
     *  @param user variable usuari de la clase usuari.
     *  @param mainController variable per gestionar el nostre contolador desde controller principal.
     */

    private ViewLogout viewLogout;
    private LogicUser logicLogout;
    private User user;
    private MainController mainController;

    /**
     * Constructor que rep la vista ViewLogout, i on creem una instància de logic usuari.
     */
    public ControllerLogout(ViewLogout logout){
        this.viewLogout = logout;
        logicLogout = new LogicUser();
    }


    /**
     * Métode que serà cridat cada cop que accionem els botons de la vista, fent override desde la interfície
     * en funció de les opcions que disposem. Mitjançant un switch realitzem les diferents funcions,
     * generant les opcions de sortir de l'aplicació i accedint a la vista Login d'usuari, o bé, esborrant usuari.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ViewLogout.BTN_LOGOUT:
                user = logicLogout.logoutUser();
                mainController.setUser(user);
                viewLogout.setVisible(false);
                mainController.showVistaLogin();
                break;

            case ViewLogout.BTN_DELETE:
                logicLogout.deleteUser(user.getUsername());
                viewLogout.setVisible(false);
                mainController.showVistaLogin();
                break;
        }
    }

    /**
     * Getters i Setters per modificar o accedir a les nostres variables.
     */
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
