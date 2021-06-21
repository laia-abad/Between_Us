package Presentation.Controller;
import Business.Entities.User;
import Business.LogicUser;
import Presentation.Views.ViewRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controlador que permet gestionar totes les opcions que hi han a ViewRegister.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */
public class ControllerRegister implements ActionListener {
    /**
     * Atributs que emmagatzema la vista, amb la finalitat d'administrar les dades. Modificarem
     * després de que fem ús dels botons de la vista ViewGameManagement.
     *  @param view variable del tipus vista ViewRegister.
     *  @param user variable usuari de la clase usuari.
     *  @param mainController variable per gestionar el nostre contolador desde controller principal.
     */

    // Attribute storing the view, for managing purposes. We will modify
    //  it after we get notified that a button has been pressed.
    private ViewRegister view;
    private User user;
    private MainController mainController;
    //private RegisterModel m;


    // Parametrized constructor, receiving the view.
    public ControllerRegister(ViewRegister view) {
        this.view = view;
        user = new User();
    }

    /**
     * The ButtonController class will implement the ActionListener interface,
     * which defines behavior to execute when a button (or similar elements) are
     * pressed (or actioned in similar ways).
     *
     * The ButtonController class will act as the system's controller (as described
     * in the GRASP patterns), managing the view and separating it from the rest.
     */

    // Method that will be called every time a button is pressed, overriden
    //  from the interface to provide an implementation.
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons. An if-else statement would suffice,
        //  but the switch will prove useful once we have more of them.
        switch (e.getActionCommand()) {
            case ViewRegister.BTN_MAIN:
                LogicUser logicRegister = new LogicUser();
                ArrayList errores = logicRegister.register(view.getJtfUser(), view.getJtfMail(), view.getJtfPassword(), view.getJtfPasswordVerifier());
                if (errores.size() == 0) {
                    user.setUsername(view.getJtfUser());
                    user.setEmail(view.getJtfMail());
                    user.setPassword(view.getJtfPassword());
                    mainController.setUser(user);
                    view.setVisible(false);
                    mainController.showVistaGameManagement();

                } else {
                    view.resetInput();
                    String stringErrores = "";
                    for (int i = 0; i < errores.size(); i++) {
                        stringErrores = stringErrores + errores.get(i) + "\n";
                    }
                    view.createDialogError(stringErrores, "Error al registrarse");
                }
                break;

            case ViewRegister.BTN_LOGIN:
                view.setVisible(false);
                mainController.showVistaLogin();
                break;

        }
    }

    /**
     * Getters i Setters per modificar o accedir a les nostres variables.
     */
    public String getUsername() {
        return view.getJtfUser();
    }

    public User getUser() {
        return user;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
