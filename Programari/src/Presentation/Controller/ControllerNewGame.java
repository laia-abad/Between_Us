package Presentation.Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Business.Entities.Map;
import Business.Entities.User;
import Business.LogicMatch;
import Presentation.Views.ViewNewGame;

/**
 * Controlador que permet gestionar totes les opcions que hi han a ViewNewGame.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */
public class ControllerNewGame implements ActionListener {

    /**
     * Atributs que emmagatzema la vista, amb la finalitat d'administrar les dades. Modificarem
     * després de que fem ús dels botons de la vista ViewGameManagement.
     *  @param view variable del tipus vista ViewLogout.
     *  @param logicNewGame listaPartidas, llista de les partides.
     *  @param map variable de la classe mapa del joc.
     *  @param user variable usuari de la clase usuari.
     *  @param gamename nombre del juego.
     *  @param players enter amb el número de jugadors.
     *  @param impostors enter amb el número d'impostors.
     *  @param color enter amb el número de color.
     *  @param mainController variable per gestionar el nostre contolador desde controller principal.
     */

    private ViewNewGame view;
    private LogicMatch logicNewGame = new LogicMatch();
    private Map map;
    private User user;
    private String gamename;
    private int players;
    private int impostors;
    private int color;
    private MainController mainController;

    /**
     * Constructor que rep la vista ViewNewGame, i on creem una instància del mapa.
     */
    public ControllerNewGame(ViewNewGame viewNewGame){
        this.view = viewNewGame;
        this.map = new Map();
    }

    public String returnGameName(){
        return gamename;
    }
    public int returnPlayers(){
        return players;
    }
    public int returnImpostors(){
        return impostors;
    }
    public int returnColor(){
        return color;
    }

    /**
     * Metode que serà cridat cada cop que accionem els botons de la vista, fent override desde la interfície
     * en funció de les opcions que disposem. Mitjançant un switch realitzem les diferents funcions de la vista.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ViewNewGame.BTN_INICIA:
                gamename = view.getGameNameInput();
                players = view.getPlayersInput();
                impostors = view.getImpostorInput();
                color = view.getColorInput();

                System.out.println("Juego " + view.getGameNameInput());
                System.out.println("Color " + view.getColorInput());
                System.out.println("Impostor " + view.getImpostorInput());
                System.out.println("Players " + view.getPlayersInput());
                System.out.println("Map " + map.getFilename());
                System.out.println("User " + user.getUsername());

                ArrayList errores = logicNewGame.createGame(gamename, players, impostors, color, map.getFilename(), user.getUsername());

                if (errores.size() == 0) {
                    mainController.setMatch(logicNewGame.loadGame(user.getUsername(), view.getGameNameInput()));
                    view.setVisible(false);
                    mainController.showVistaPlayerAction();

                } else {
                    //view.resetInput();
                    String stringErrores = "";
                    for (int i = 0; i < errores.size(); i++) {
                        stringErrores = stringErrores + errores.get(i) + "\n";
                    }
                    view.createDialogError(stringErrores, "Error al empezar partida");
                }
                break;

            case ViewNewGame.BTN_MAIN:
                String path = view.fileChooser().getPath().replace("\\", "\\\\");
                map = logicNewGame.importMap(path);
                if (map == null) {
                    view.createDialogError("El mapa introducido no es correcto", "Error al cargar el mapa");
                }
                break;
        }
    }

    /**
     * Getters i Setters per modificar o accedir a les nostres variables.
     */
    public User getUser() {
        return user;
    }

    public Map getMap() {
        return map;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}

