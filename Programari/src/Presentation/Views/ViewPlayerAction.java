package Presentation.Views;

import Business.Entities.Character;
import Business.Entities.Crew;
import Business.Entities.Map;
import Business.Entities.Room;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Esta vista pinta el juego en si. Muestra el mapa y los jugadores y ademas contiene los botones
 * que dependen de las diferentes propiedades de las salas.
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class ViewPlayerAction extends JFrame {

    public static final String BTN_UP = "BTN_UP";
    public static final String BTN_DOWN = "BTN_DOWN";
    public static final String BTN_LEFT = "BTN_LEFT";
    public static final String BTN_RIGHT = "BTN_RIGHT";
    public static final String BTN_ONOFF = "BTN_ONOFF";
    public static final String BTN_OBJECTIVE = "BTN_OBJECTIVE";
    public static final String BTN_CLASIFICACION = "BTN_CLASIFICACION";
    public static final String BTN_LOG = "BTN_LOG";
    public static final String BTN_EXIT = "BTN_EXIT";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";

    //Coordenadas ficha
    private int columnaUsuario;
    private int filaUsuario;
    private Crew player;

    private final JButton bi = new JButton("Izquierda");
    private final JButton bd = new JButton("Derecha");
    private final JButton bArriba = new JButton("Arriba");
    private final JButton bAbajo = new JButton("Abajo");
    private final JButton boNoFF = new JButton("ON/OFF");
    private final JButton bObjective = new JButton("OBJECTIVE");
    private final JButton bClasificacion = new JButton("CLASIFICATION");
    private final JButton bLog = new JButton("LOG");
    private final JButton bExit = new JButton("EXIT");
    private final JButton bLogout = new JButton("LOGOUT");

    private JList<String> jlSala;
    private String[] data = {"Esto es una prueba de la variable data", "Esto es una prueba de la variable data", "Esto es una prueba de la variable data"
            , "Esto es una prueba de la variable data", "Esto es una prueba de la variable data", "Esto es una prueba de la variable data"
            , "Esto es una prueba de la variable data"};

    private Map casillas = new Map();
    private Character[] characters;
    private boolean pintarOnOff;

    public ViewPlayerAction() {
        this.pintarOnOff = false;
        creaBotones();
    }
    /**
     * Method que define los botones y sus tamanos
     */
    public void creaBotones() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 800);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        bi.setBounds(900, 50, 89, 23);
        bi.setActionCommand(BTN_LEFT);
        contentPane.add(bi);

        bd.setBounds(900, 100, 89, 23);
        bd.setActionCommand(BTN_RIGHT);
        contentPane.add(bd);

        bArriba.setBounds(900, 150, 80, 23);
        bArriba.setActionCommand(BTN_UP);
        contentPane.add(bArriba);

        bAbajo.setBounds(900, 200, 80, 23);
        bAbajo.setActionCommand(BTN_DOWN);
        contentPane.add(bAbajo);

        boNoFF.setBounds(900, 250, 80, 23);
        boNoFF.setActionCommand(BTN_ONOFF);
        contentPane.add(boNoFF);

        bObjective.setBounds(900, 300, 120, 23);
        bObjective.setActionCommand(BTN_OBJECTIVE);
        contentPane.add(bObjective);

        bClasificacion.setBounds(900, 350, 80, 23);
        bClasificacion.setActionCommand(BTN_CLASIFICACION);
        contentPane.add(bClasificacion);
        bClasificacion.setEnabled(false);

        bLog.setBounds(900, 400, 80, 23);
        bLog.setActionCommand(BTN_LOG);
        contentPane.add(bLog);
        bLog.setEnabled(false);

        bExit.setBounds(900, 450, 80, 23);
        bExit.setActionCommand(BTN_EXIT);
        contentPane.add(bExit);

        bLogout.setBounds(900, 500, 89, 23);
        bLogout.setActionCommand(BTN_LOGOUT);
        contentPane.add(bLogout);

        setBounds(100, 0, 1215, 1200);

    }

    public void setEnableClasification(boolean activate) {
        bClasificacion.setEnabled(activate);
    }

    public void setEnableLog(boolean activate) {
        bLog.setEnabled(activate);
    }

    /**
     * Method that configures any class implementing the ActionListener interface
     * as a listener for all the buttons' clicks.
     *
     * @param listener The ActionListener implementation that will be notified of the clicks.
     */
    public void registerController(ActionListener listener) {

        bi.addActionListener(listener);
        bd.addActionListener(listener);
        bArriba.addActionListener(listener);
        bAbajo.addActionListener(listener);
        boNoFF.addActionListener(listener);
        bExit.addActionListener(listener);
        bObjective.addActionListener(listener);
        bClasificacion.addActionListener(listener);
        bLog.addActionListener(listener);
        bLogout.addActionListener(listener);
    }


    //Tablero
    /**
     * Metodo que pinta el tablero del juego (mapa, bolitas y cuadrados de cuando mueren)
     *
     * @param g Grafico del jFrame
     */
    public void paint(Graphics g) {

        //Pinta el Tablero a partir de MAP.json
        Room r;
        for (int j = 0; j < casillas.getRooms().size(); j++) {
            for (int k = 0; k < casillas.getRooms().get(j).size(); k++) {
                r = casillas.getRooms().get(j).get(k);
                if (r != null) {
                    switch (r.getColor()) {
                        case 0:
                            g.setColor(new Color(254, 152, 152));
                            break;
                        case 1:
                            g.setColor(new Color(152, 188, 254));
                            break;
                        case 2:
                            g.setColor(new Color(152, 254, 219));
                            break;
                        case 3:
                            g.setColor(Color.pink.brighter());
                            break;
                        case 4:
                            g.setColor(new Color(254, 214, 152));
                            break;
                        case 5:
                            g.setColor(new Color(254, 250, 152));
                            break;
                        case 6:
                            g.setColor(Color.black);
                            break;
                        case 7:
                            g.setColor(new Color(182, 182, 182));
                            break;
                        case 8:
                            g.setColor(new Color(108, 36, 171).brighter());
                            break;
                        case 9:
                            g.setColor(new Color(150, 75, 0).brighter());
                            break;
                        case 10:
                            g.setColor(Color.cyan.brighter());
                            break;
                        case 11:
                            g.setColor(new Color(157, 255, 0).brighter());
                            break;
                    }

                } else {
                    g.setColor(Color.black);
                }
                g.fillRect(125 + k * 175, 40 + j * 175, 175, 175);
            }
        }

        //Pinta User
        selectColor(g, player.getColor());
        g.fillOval(player.getxActual() * 175 + 135, player.getyActual() * 175 + 60, 20, 20);
        if (!player.isAlive()) {
            g.setColor(Color.black);
            g.fillRect(130 + player.getxActual() * 175, 55 + player.getyActual() * 175, 15, 15);
        }

        //Pinta NPC
        int x = 1;
        int y = 0;
        for (int i = 0; i < characters.length; i++) {
            selectColor(g, characters[i].getColor());
            g.fillOval(135 + characters[i].getxActual() * 175 + x * 45, 60 + characters[i].getyActual() * 175 + y * 45, 20, 20);
            if (Crew.class.isInstance(characters[i])) {
                Crew crew = (Crew) characters[i];
                if (!crew.isAlive()) {
                    g.setColor(Color.black);
                    g.fillRect(7 + 130 + characters[i].getxActual() * 175 + x * 45, 7 + 55 + characters[i].getyActual() * 175 + y * 45, 14, 14);
                }
            }
            if (x > 2) {
                x = 0;
                y++;
            } else {
                x++;
            }
        }

        //Pinta negro si se apreta ON/OFF
        if (pintarOnOff) {
            for (int j = 0; j < casillas.getRooms().size(); j++) {
                for (int k = 0; k < casillas.getRooms().get(j).size(); k++) {
                    if (player.getxActual() != k || player.getyActual() != j) {
                        g.setColor(Color.black);
                        g.fillRect(125 + k * 175, 40 + j * 175, 175, 175);
                    }
                }
            }
        }


    }

    /**
     * Metodo que selecciona color segun el int del character[i]
     *
     * @param g Grafico del JFrame
     * @param color color del character[i] para poder pintarlo
     */

    //Seleccionamos color segun el int del character[i]
    private void selectColor(Graphics g, int color) {
        switch (color) {
            case 0:
                g.setColor(Color.red);
                break;
            case 1:
                g.setColor(Color.blue);
                break;
            case 2:
                g.setColor(Color.green.darker());
                break;
            case 3:
                g.setColor(Color.pink);
                break;
            case 4:
                g.setColor(Color.orange);
                break;
            case 5:
                g.setColor(Color.yellow);
                break;
            case 6:
                g.setColor(Color.black);
                break;
            case 7:
                g.setColor(Color.white);
                break;
            case 8:
                g.setColor(new Color(108, 36, 171));
                break;
            case 9:
                g.setColor(new Color(150, 75, 0));
                break;
            case 10:
                g.setColor(Color.cyan);
                break;
            case 11:
                g.setColor(new Color(157, 255, 0));
                break;
        }
    }

    /**
     * Getters y setters
     */
    public Map getCasillas() {
        return casillas;
    }

    public void setCasillas(Map casillas) {
        this.casillas = casillas;
    }

    public Character[] getCharacters() {
        return characters;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public int getColumna() {
        return columnaUsuario;
    }

    public int getFila() {
        return filaUsuario;
    }

    public void setColumna(int columna) {
        this.columnaUsuario = columna;
    }

    public void setFila(int fila) {
        this.filaUsuario = fila;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Crew player) {
        this.player = player;
    }

    public void setPintarOnOff() {
        if (pintarOnOff == true) {
            pintarOnOff = false;
        } else {

            pintarOnOff = true;
        }
    }

    public void createDialogError(String message, String title) {
        JOptionPane.showMessageDialog(getContentPane(),
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    public void createDialogSuccess(String message, String title) {
        JOptionPane.showMessageDialog(getContentPane(),
                message,
                title,
                JOptionPane.PLAIN_MESSAGE);
    }
}