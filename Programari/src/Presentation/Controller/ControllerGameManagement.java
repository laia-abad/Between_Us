package Presentation.Controller;

import Business.Entities.User;
import Business.LogicMatch;
import Presentation.Views.ViewGameManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Controlador que permet gestionar totes les opcions que hi han a ViewPlayerAction.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */
public class ControllerGameManagement implements ActionListener {

    // Attribute storing the view, for managing purposes. We will modify
    //  it after we get notified that a button has been pressed.
    private ViewGameManagement view;
    private String[] listaPartidas;
    private int numPartida;
    private LogicMatch logic;
    private User user;
    private MainController mainController;

    /**
     * Constructor que rep la vista ViewGameManagement.
     * @param view vista rebuda.
     */

    public ControllerGameManagement(ViewGameManagement view) {
        this.view = view;
        this.logic = new LogicMatch();
        this.view.startView();
    }

    /**
     * Metode que serà cridat cada cop que accionem els botons de la vista, fent override desde la interfície
     * en funció de les opcions que disposem. Mitjançant un switch realitzem les diferents funcions, Configurar partida,
     * Continuar una partida guardada, Eliminar una partida guardada o crear-ne una de nova.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons. An if-else statement would suffice,
        //  but the switch will prove useful once we have more of them.
        switch (e.getActionCommand()) {
            case ViewGameManagement.BTN_CONFIGURAR:
                numPartida = view.getPartida();
                if (numPartida == -1) {
                    view.createDialogError("Tienes que seleccionar una partida para copiarla", "Error copiando configuracion");
                } else {
                    logic.copyGame(user.getUsername(), listaPartidas[numPartida]);
                    mainController.setMatch(logic.loadGame(user.getUsername(), listaPartidas[numPartida]));//falta implementar

                    view.setVisible(false);
                    mainController.showVistaPlayerAction();
                }
                break;

            case ViewGameManagement.BTN_CONTINUAR:
                numPartida = view.getPartida();
                if (numPartida == -1) {
                    view.createDialogError("Tienes que seleccionar una partida para continuar", "Error al continuar partida");
                } else {
                    logic.loadGame(user.getUsername(), listaPartidas[numPartida]); //falta implementar
					mainController.setMatch(logic.loadGame(user.getUsername(), listaPartidas[numPartida]));
                    view.setVisible(false);
                    mainController.showVistaPlayerAction();
                }
                break;

            case ViewGameManagement.BTN_ELIMINAR:
                numPartida = view.getPartida();
                if (numPartida == -1) {
                    view.createDialogError("Tienes que seleccionar una partida para eliminarla", "Error al eliminar partida");
                } else {
                    int response = view.createDialogOption("Estas seguro que quieres eliminar la partida '" + listaPartidas[numPartida] + "'?", "Eliminar partida");
                    if (response == JOptionPane.YES_OPTION) {
                        logic.deleteMatch(user.getUsername(), listaPartidas[numPartida]);
                        listaPartidas = logic.loadAllGames(user.getUsername());
                        view.setListData(listaPartidas);
                    }
                }
                break;

            case ViewGameManagement.BTN_NUEVA:
                view.setVisible(false);
                mainController.showVistaNewGame();
                break;
        }

    }

    /**
     * Getters i Setters per modificar o accedir a les nostres variables.
     */

    public void setListaPartidas() {
        listaPartidas = logic.loadAllGames(user.getUsername());
        view.setListData(listaPartidas);
    }

    public User getUser() {
        return user;
    }

    public String getMatchName () {
        numPartida = view.getPartida();
        return listaPartidas[numPartida];
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
