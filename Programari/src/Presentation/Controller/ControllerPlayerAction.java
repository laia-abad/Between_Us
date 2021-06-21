package Presentation.Controller;

import Business.Entities.*;
import Business.Entities.Character;
import Business.LogicMatch;
import Business.Entities.Log;
import Presentation.Views.ThreadPlayerAction;
import Presentation.Views.ViewPlayerAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controlador que permet gestionar totes les opcions que hi han a ViewPlayerAction.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */

public class ControllerPlayerAction implements ActionListener {

    /**
     * Atributs que emmagatzema la vista, amb la finalitat d'administrar les dades. Modificarem
     * després de que fem ús dels botons de la vista ViewGameManagement.
     *  @param view variable del tipus vista ViewObjective.
     *  @param mainController variable per gestionar el nostre contolador desde controller principal.
     *  @param user variable usuari.
     *  @param match  variable Match.
     *  @param player array de jugadores.
     *  @param logicMatch variable LogicMatch.
     *  @param characters array de jugadrors.
     *  @param logs array de tipus logs.
     *  @param thread variable ThreadPlayerAction.
     */

    private ViewPlayerAction view;
    private MainController mainController;
    private User user;
    private Match match;
    private Crew player;
    private Map map;
    private LogicMatch logicMatch;
    private Character[] characters;
    private ArrayList<Log> logs;
    private ThreadPlayerAction thread;

    public ControllerPlayerAction(ViewPlayerAction view) {
        this.view = view;
    }

    public void iniciaThread() {
        thread = new ThreadPlayerAction(view);
        new Thread(thread).start();
    }

    /**
     * Metode que serà cridat cada cop que accionem els botons de la vista, fent override desde la interfície
     * en funció de les opcions que disposem. Mitjançant un switch realitzem les diferents funcions de la vista, on es visualitzen tots els
     * mecanismes del tauler de la partida.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons. An if-else statement would suffice,
        //  but the switch will prove useful once we have more of them.
        switch (e.getActionCommand()) {
            case ViewPlayerAction.BTN_LEFT:
                if (logicMatch.moveCharacter(2, player, map)) {
                    player.setxActual(player.getxActual() - 1);
                }
                view.setEnableLog(map.getRooms().get(player.getyActual()).get(player.getxActual()).isLog());
                if (thread.getContador() > 1200) {
                    view.setEnableClasification(map.getRooms().get(player.getyActual()).get(player.getxActual()).isCompClass());
                }
                break;

            case ViewPlayerAction.BTN_RIGHT:
                if (logicMatch.moveCharacter(3, player, map)) {
                    player.setxActual(player.getxActual() + 1);
                }
                view.setEnableLog(map.getRooms().get(player.getyActual()).get(player.getxActual()).isLog());
                if (thread.getContador() > 1200) {
                    view.setEnableClasification(map.getRooms().get(player.getyActual()).get(player.getxActual()).isCompClass());
                }
                break;

            case ViewPlayerAction.BTN_UP:
                if (logicMatch.moveCharacter(0, player, map)) {
                    player.setyActual(player.getyActual() - 1);
                }
                view.setEnableLog(map.getRooms().get(player.getyActual()).get(player.getxActual()).isLog());
                if (thread.getContador() > 1200) {
                    view.setEnableClasification(map.getRooms().get(player.getyActual()).get(player.getxActual()).isCompClass());
                }
                break;

            case ViewPlayerAction.BTN_DOWN:
                if (logicMatch.moveCharacter(1, player, map)) {
                    player.setyActual(player.getyActual() + 1);
                }
                view.setEnableLog(map.getRooms().get(player.getyActual()).get(player.getxActual()).isLog());
                if (thread.getContador() > 1200) {
                    view.setEnableClasification(map.getRooms().get(player.getyActual()).get(player.getxActual()).isCompClass());
                }
                break;

            case ViewPlayerAction.BTN_ONOFF:
                view.setPintarOnOff();
                break;

            case ViewPlayerAction.BTN_OBJECTIVE:
                view.setVisible(false);
                mainController.showVistaObjective();

                break;

            case ViewPlayerAction.BTN_CLASIFICACION:
                if (!logicMatch.checkClassification(characters)) {
                    view.createDialogError("Tu clasificacion es incorrecta", "Incorrecto");
                    thread.setContador(0);
                    view.setEnableClasification(false);
                } else {
                    view.createDialogSuccess("Tu clasificacion es correcta!\nHas ganado!", "Has ganado!");
                    //guardar en la tabla
                    view.setVisible(false);
                    mainController.showVistaGameManagement();
                }
                break;

            case ViewPlayerAction.BTN_LOG:
                //view.setVisible(false);
                //mainController.showVistaLog();
                break;

            case ViewPlayerAction.BTN_EXIT:
                //guardar partida
                view.setVisible(false);
                mainController.showVistaGameManagement();
                break;

            case ViewPlayerAction.BTN_LOGOUT:
                view.setVisible(false);
                mainController.showVistaLogout();
                break;
        }
    }

    /**
     * Metode que inicialitza tota la partida.
     */
    public void setMatch(Match match) {
        this.match = match;
        LogicMatch l = new LogicMatch();
        this.map = l.importMap(match.getMap());
        int[] xy = map.getStartRoom();
        this.player = new Crew(xy[0], xy[1], match.getColor_player());
        view.setCasillas(map);
        //aca arrancan los threads (y no pueden arrancar en ningun otro lado)
        logicMatch = new LogicMatch(match.getN_players(), match.getN_impostors(), match.getColor_player(), 1, 1, match);
        logs = logicMatch.getLogs();
        characters = logicMatch.getCharacters();
        mainController.setCharacters(characters);
        mainController.setLogs(logs);
        view.setCharacters(characters);
        view.setPlayer(player);
        logicMatch.setPlayer(player);
    }

    /**
     * Getters i Setters per modificar o accedir a les nostres variables.
     */

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public Match getMatch() {
        return match;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}