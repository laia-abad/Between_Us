package Presentation.Controller;

import Business.Entities.Match;
import Business.Entities.User;
import Business.LogicMatch;
import Presentation.Views.ViewObjective;
import Business.Entities.Character;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador que permet gestionar totes les opcions que hi han a ViewObjective.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */
public class ControllerObjective implements ActionListener {

    /**
     * Atributs que emmagatzema la vista, amb la finalitat d'administrar les dades. Modificarem
     * després de que fem ús dels botons de la vista ViewGameManagement.
     *  @param viewObjective variable del tipus vista ViewObjective.
     *  @param user variable usuari de la clase usuari.
     *  @param position matriu de posicion usuari.
     *  @param mainController variable per gestionar el nostre contolador desde controller principal.
     *  @param logicMatch variable LogicMatch.
     *  @param characters array de jugadores.
     *  @param match variable Match.
     */
    private ViewObjective viewObjective;
    private User user;
    private boolean[][] position;
    private MainController mainController;
    private LogicMatch logicMatch;
    private Character[] characters;
    private Match match;

    public ControllerObjective(ViewObjective viewNewGame){
        this.viewObjective = viewNewGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String sActionCommand = button.getActionCommand();

        if (e.getActionCommand().equals(ViewObjective.BTN_RETURN)) {
            viewObjective.setVisible(false);
            mainController.showVistaPlayerAction();

        } else {
            // Podem agafar el valor de columna i fila a partir del botó, ja que ho afegim al seu actionCommand, per poder saber quin botó ens han clickat, a partir de la seva fila i columna.
            int row = Integer.parseInt(sActionCommand.split("-")[1].split(",")[0]);
            int col = Integer.parseInt(sActionCommand.split("-")[1].split(",")[1]);

            if (e.getActionCommand().contains(ViewObjective.BTN_LEFT_ACTION)) {
                // Aquí es decrementa (una columna menys) el jugador que s'esta aplicant, juntament amb el control d'errors corresponent per fer-ho cíclic.
                position[row][col] = false;
                if (col - 1 < 0) {
                    position[row][2] = true;
                    characters[row].setSus(2);

                } else {
                    position[row][col - 1] = true;
                    characters[row].setSus(col - 1);
                }
            } else if (e.getActionCommand().contains(ViewObjective.BTN_RIGHT_ACTION)) {
                // Aquí s'incrementa (una columna més) el jugador que s'esta aplicant, juntament amb el control d'errors corresponent per fer-ho cíclic.
                position[row][col] = false;
                if (col + 1 > 2) {
                    position[row][0] = true;
                    characters[row].setSus(0);
                } else {
                    position[row][col + 1] = true;
                    characters[row].setSus(col + 1);
                }
            }
        }
        // Un cop actualitzada la matriu position de booleans, avisem a la vista que s'ha de refrescar, passant-li els nous valors a modificar.
        viewObjective.generateView(position);
    }

    /**
     * Getter de la matriu de posició dels jugadors dins la vista.
     * @return Retorna la matriu position.
     */
    public boolean[][] getPosition() {
        return position;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setPosition() {
        for (int i = 0; i < characters.length; i++) {
            switch (characters[i].getSus()) {
                case 0:
                    position[i][0] = true;
                    position[i][1] = false;
                    position[i][2] = false;
                    break;

                case 1:
                    position[i][0] = false;
                    position[i][1] = true;
                    position[i][2] = false;
                    break;

                case 2:
                    position[i][0] = false;
                    position[i][1] = false;
                    position[i][2] = true;
                    break;
            }
        }
        viewObjective.generateView(position);
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
        position = new boolean[characters.length][3];
        int[] colors = new int[characters.length];
        for (int i = 0; i < characters.length; i++) {
            colors[i] = characters[i].getColor();
        }
        viewObjective.setRowColors(colors);
        setPosition();
    }
}
