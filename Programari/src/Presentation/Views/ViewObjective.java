package Presentation.Views;

import Presentation.Controller.ControllerObjective;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Esta vista permite al usuario clasificar al resto de los jugadores para
 * poder conseguir el objetivo del juego que es adivinar quien es quien
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */
public class ViewObjective extends JFrame {

    public static final String BTN_LEFT_ACTION = "LEFT";
    public static final String BTN_RIGHT_ACTION = "RIGHT";
    public static final String BTN_RETURN = "RETURN";

    private int[] rowColors;
    private String[] nameRowColors;

    private JPanel jpTableData;
    private ActionListener listener;
    private int numRows;

    public ViewObjective() {

        // Inicialitzacions base de la vista
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        pack();
        setSize(800, 600);

        // Creem el header de la taula i el posem adalt del BorderLayout (pare).
        JPanel jpTableHeaders = new JPanel(new GridLayout(0, 3));

        JLabel firstColumn = new JLabel("Unknown", SwingConstants.CENTER);
        JLabel secondColumn = new JLabel("Sus", SwingConstants.CENTER);
        JLabel thirdColumn = new JLabel("Not Sus", SwingConstants.CENTER);

        jpTableHeaders.add(firstColumn);
        jpTableHeaders.add(secondColumn);
        jpTableHeaders.add(thirdColumn);

        getContentPane().add(jpTableHeaders, BorderLayout.NORTH);
    }

    /**
     * Method that configures any class implementing the ActionListener interface
     * as a listener for all the buttons' clicks.
     *
     * @param controllerObjective The ActionListener implementation that will be notified of the clicks.
     */
    public void registerController(ControllerObjective controllerObjective) {
        this.listener = controllerObjective;

        // Inicialitzem el component la primera vegada, que es quan registrem el controlador i tenim el listener dels botons escoltant ja preparats per poder-los ficar en el botó i que facin las seva lógica de shiftar entre columnes de la matriu el jugador.
        jpTableData = new JPanel(new GridLayout(10, 3));
        //generateView(controllerObjective.getPosition());
        getContentPane().add(jpTableData, BorderLayout.CENTER);
    }



    /**
     * Aquesta funció s'actualitza cada vegada que es prem un botó d'aquesta, per tornar a montar el component jbTableData amb la nova ubicació dels botons
     * el que fa es generar una matriu de jPanels que segons el boolea de la matriu position farà visible o no, simulant així l'afecta de que es mouen.
     *
     * @param position És una matriu de booleans de dimensions (numJugadors * 3 columnes) ens indica a quina columna està cada jugador si aquella posició està 'true'
     */
    public void generateView(boolean[][] position) {
        nameRowColors = new String[position.length];
        // Utilitzant el setVisible a false i a true, quan sortim de la funció fem que el component es refresqui i s'actualitzi la UI.
        jpTableData.setVisible(false);
        jpTableData.removeAll();

        // La matriu position, és la que ens indica si hi ha o no botó en aquella part del component, es la que mana i la que hem d'actualitzar en tot moment.
        for (int i = 0; i < position.length; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel panel = getJPanelWithBackgroundColor(rowColors[i], i);

                JButton jbLeft = new JButton("<");
                JButton jbRight = new JButton(">");

                jbLeft.setActionCommand(BTN_LEFT_ACTION + '-' + i + ',' + j);
                jbRight.setActionCommand(BTN_RIGHT_ACTION + '-' + i + ',' + j);

                jbLeft.addActionListener(listener);
                jbRight.addActionListener(listener);

                JLabel jlColorText = new JLabel(nameRowColors[i], SwingConstants.CENTER);

                panel.add(jbLeft, BorderLayout.WEST);
                panel.add(jlColorText, BorderLayout.CENTER);
                panel.add(jbRight, BorderLayout.EAST);

                if (rowColors[i] == -1 || !position[i][j]) panel.setVisible(false);
                jpTableData.add(panel);
            }
        }
        jpTableData.setVisible(true);
        JButton jbVolver = new JButton("<- Volver a la partida");
        jbVolver.setActionCommand(BTN_RETURN);
        jbVolver.addActionListener(listener);
        jpTableData.add(jbVolver);
    }

    /**
     * Funció que s'encarrega de generar els 10 colors / noms dels jugadors que necessitem per la vista
     */
    public void setRowColors(int[] rowColors) {
        this.rowColors = rowColors;
    }



    /**
     * Aquesta funció s'encarrega de transformar el valor String al Objecte Color per poder generar el fondo del JPanel que s'està visualitzant en aquell moment.
     *
     * @param color String amb el color a transformar a tipus Color
     * @return Retorna el jPanel configurat amb el fons ja posat del color corresponent.
     */
    private JPanel getJPanelWithBackgroundColor(int color, int i) {
        JPanel panel = new JPanel(new BorderLayout());
        if (color != -1) {
            switch (color) {
                case 0:
                    panel.setBackground(Color.RED);
                    nameRowColors[i] = "Red";
                    break;
                case 1:
                    panel.setBackground(Color.BLUE);
                    nameRowColors[i] = "Blue";
                    break;
                case 2:
                    panel.setBackground(Color.GREEN.darker());
                    nameRowColors[i] = "Green";
                    break;
                case 3:
                    panel.setBackground(Color.PINK);
                    nameRowColors[i] = "Pink";
                    break;
                case 4:
                    panel.setBackground(Color.ORANGE);
                    nameRowColors[i] = "Orange";
                    break;
                case 5:
                    panel.setBackground(Color.YELLOW);
                    nameRowColors[i] = "Yellow";
                    break;
                case 6:
                    panel.setBackground(Color.BLACK);
                    nameRowColors[i] = "Black";
                    break;
                case 7:
                    panel.setBackground(Color.WHITE);
                    nameRowColors[i] = "White";
                    break;
                case 8:
                    panel.setBackground(new Color(108, 36, 171));
                    nameRowColors[i] = "Purple";
                    break;
                case 9:
                    panel.setBackground(new Color(150, 75, 0));
                    nameRowColors[i] = "Brown";
                    break;
                case 10:
                    panel.setBackground(Color.CYAN);
                    nameRowColors[i] = "Cyan";
                    break;
                case 11:
                    panel.setBackground(new Color(157, 255, 0));
                    nameRowColors[i] = "Lime";
                    break;
            }
        }
        return panel;
    }
}
