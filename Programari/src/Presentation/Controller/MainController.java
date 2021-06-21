package Presentation.Controller;
import Business.Entities.*;
import Business.Entities.Character;
import Business.LogicUser;
import Presentation.Views.*;
import Business.Entities.Log;

import java.util.ArrayList;

/**
 * Controlador principal que gestiona tota la resta de controladors de les diferents vistes.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */

public class MainController {

    /**
     * Atributs que emmagatzema la vista, amb la finalitat d'administrar les dades. Modificarem
     * després de que fem ús dels botons de la vista ViewGameManagement.
     *  @param user variable usuari de la clase usuari.
     *  @param match variable Match.
     *  @param characters array de jugadrors.
     *  @param logs array de tipus logs.
     *  @param logicUser Variable de LogicUser.
     *  @param viewRegister Variable de .
     *  @param controllerRegister Variable del tipus Controlador del registre.
     *  @param viewLogin Variable de la vista login..
     *  @param controllerLogin variable de controlador de login.
     *  @param viewGameManagement Variable de de la vista ViewGameManagement.
     *  @param controllerGameManagement Variable del tipus Controlador del GameManagement.
     *  @param viewLogout Variable de la vista logout.
     *  @param controllerLogout Variable del Controlador de logout.
     *  @param viewNewGame Variable de la vista NewGame..
     *  @param controllerNewGame Variable del tipus Controlador del New Game.
     *  @param viewPlayerAction Variable de la vista de player action.
     *  @param controllerPlayerAction Variable del tipus Controlador del Player Action.
     *  @param viewObjective Variable de de la vista Objective.
     *  @param controllerObjective Variable del tipus Controlador del Objective.
     *  @param viewLog Variable de la vista Log.
     *  @param controllerLog Variable del tipus Controlador del Log.
     */

    private User user;
    private Match match;
    private Character[] characters;
    private ArrayList<Log> logs;

    private LogicUser logicUser;

    private ViewRegister viewRegister;
    private ControllerRegister controllerRegister;

    private ViewLogin viewLogin;
    private ControllerLogin controllerLogin;

    private ViewGameManagement viewGameManagement;
    private ControllerGameManagement controllerGameManagement;

    private ViewLogout viewLogout;
    private ControllerLogout controllerLogout;

    private ViewNewGame viewNewGame;
    private ControllerNewGame controllerNewGame;

    private ViewPlayerAction viewPlayerAction;
    private ControllerPlayerAction controllerPlayerAction;

    private ViewObjective viewObjective;
    private ControllerObjective controllerObjective;

    private ViewLog viewLog;
    private ControllerLog controllerLog;

    /**
     * Constructor que rep totes les vistes de l'aplicació junt amb els seus controladors.
     */
    public MainController(ViewRegister viewRegister, ControllerRegister controllerRegister, ViewLogin viewLogin, ControllerLogin controllerLogin, ViewGameManagement viewGameManagement, ControllerGameManagement controllerGameManagement, ViewLogout viewLogout, ControllerLogout controllerLogout, ViewNewGame viewNewGame, ControllerNewGame controllerNewGame, ViewPlayerAction viewPlayerAction, ControllerPlayerAction controllerPlayerAction, ViewObjective viewObjective, ControllerObjective controllerObjective, ViewLog viewLog, ControllerLog controllerLog) {
        logicUser = new LogicUser();
        this.viewRegister = viewRegister;
        this.controllerRegister = controllerRegister;
        this.viewLogin = viewLogin;
        this.controllerLogin = controllerLogin;
        this.viewLogout = viewLogout;
        this.controllerLogout = controllerLogout;
        this.viewGameManagement = viewGameManagement;
        this.controllerGameManagement = controllerGameManagement;
        this.viewObjective = viewObjective;
        this.controllerObjective = controllerObjective;
        this.viewPlayerAction = viewPlayerAction;
        this.controllerPlayerAction = controllerPlayerAction;
        this.viewNewGame = viewNewGame;
        this.controllerNewGame = controllerNewGame;
        this.viewLog = viewLog;
        this.controllerLog= controllerLog;
    }

    /**
     * Controladors on es genera la crida i l'ordre de quan s'executaran les diferents vistes del programa.
     */
    public void showVistaLogin() {
        viewLogin.setVisible(true);
    }

    public void showVistaRegister() {
        viewRegister.setVisible(true);
    }

    public void showVistaGameManagement() {
        if (controllerGameManagement.getUser() != user) {
            controllerGameManagement.setUser(user);
            controllerGameManagement.setListaPartidas();
        }
        viewGameManagement.setVisible(true);
    }

    public void showVistaNewGame() {
        if (controllerNewGame.getUser() != user) {
            controllerNewGame.setUser(user);
        }
        viewNewGame.setVisible(true);
    }

    public void showVistaObjective() {
        if (controllerObjective.getMatch() != match) {
            controllerObjective.setUser(user);
            controllerObjective.setMatch(match);
            controllerObjective.setCharacters(characters);
        }
        controllerObjective.setPosition();
        viewObjective.setVisible(true);
    }

    public void showVistaPlayerAction() {
        if (controllerPlayerAction.getMatch() != match) {
            controllerPlayerAction.setCharacters(characters);
            controllerPlayerAction.setMatch(match);
            controllerPlayerAction.setUser(user);
        }
        viewPlayerAction.setVisible(true);
        controllerPlayerAction.iniciaThread();

    }

    public void showVistaLog() {
        controllerLog.setLogs(logs);
        viewLog.setVisible(true);
    }

    public void showVistaLogout() {
        controllerLogout.setUser(user);
        viewLogout.setVisible(true);
    }

    /**
     * Getters i Setters per modificar o accedir a les nostres variables.
     */
    public void setUser(User user) {
        if (user != null) {
            this.user = logicUser.loginUser(user.getUsername());
        } else {
            this.user = null;
        }
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setCharacters(Character[] characters){
        this.characters=characters;
    }

    public void setCharacter(int i,int xActual, int yActual, int xAnterior, int yAnterior){
        characters[i].setxActual(xActual);
        characters[i].setyActual(yActual);
        characters[i].setxAnterior(xAnterior);
        characters[i].setyAnterior(yAnterior);
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }
}