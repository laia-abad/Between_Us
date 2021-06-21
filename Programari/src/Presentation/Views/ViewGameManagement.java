package Presentation.Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de mostrar las opcions de la partida
 * (Eliminar partida, continuar partida, copia configuracio i crear partida)
 * i las partidas iniciades
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class ViewGameManagement extends JFrame {

    //Declarem el text en els botons
    public static final String BTN_CONTINUAR = "BTN_MAIN";
    public static final String BTN_ELIMINAR = "BTN_ERASE";
    public static final String BTN_NUEVA = "BTN_MOSTRA";
    public static final String BTN_CONFIGURAR = "BTN_CONFIG";

    //Declarem unes variables amb text dintre
    public static final String FRAME_TITLE = "Game Management";
    public static final String ELIMINA_BUTTON_TEXT = "      ELIMINAR PARTIDA     ";
    public static final String CONTINUAR_BUTTON_TEXT = "    CONTINUAR PARTIDA  ";
    public static final String NUEVA_BUTTON_TEXT = "       NUEVA PARTIDA        ";
    public static final String CONFIG_BUTTON_TEXT = "COPIAR CONFIGURACION";
    public static final String PARTIDAS = "PARTIDAS";

    //Declarem els botons
    private JButton jbElimina;
    private JButton jbCcontinuar;
    private JButton jbNueva;
    private JButton jbConfig;

    //String inicial
    private String[] data = {"PARTIDA 1", "PARTIDA 2", "PARTIDA 3", "PARTIDA 4", "PARTIDA 5", "PARTIDA 6", "PARTIDA 7", "PARTIDA 8", "PARTIDA 9", "PARTIDA 10", "PARTIDA 11", "PARTIDA 12"};
    private JList<String> partidas;

    //constructor
    public ViewGameManagement() {

    }

    //caracteristiques del Frame
    public void startView() {
        configureGames();
        configureSouth();
        configureFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(250, 300);
    }

    private void configureSouth() {
        //Creem el panel
        JPanel jp1 = new JPanel();
        jp1.setLayout(new BoxLayout(jp1, BoxLayout.PAGE_AXIS));

        //Creem els botons
        jbCcontinuar = new JButton(CONTINUAR_BUTTON_TEXT);
        jbElimina = new JButton(ELIMINA_BUTTON_TEXT);
        jbNueva = new JButton(NUEVA_BUTTON_TEXT);
        jbConfig = new JButton(CONFIG_BUTTON_TEXT);

        // Accions de la comanda
        jbConfig.setActionCommand(BTN_CONFIGURAR);
        jbCcontinuar.setActionCommand(BTN_CONTINUAR);
        jbElimina.setActionCommand(BTN_ELIMINAR);
        jbNueva.setActionCommand(BTN_NUEVA);

        //Afegim els botons al panel
        jp1.add(jbConfig);
        jp1.add(jbCcontinuar);
        jp1.add(jbElimina);
        jp1.add(jbNueva);

        //Afegim el panel al frame
        getContentPane().add(jp1, BorderLayout.AFTER_LINE_ENDS);
    }


    private void configureGames() {
        //Creem el panel
        JPanel jpGames = new JPanel();

        //posem titol
        jpGames.setLayout(new BoxLayout(jpGames, BoxLayout.PAGE_AXIS));
        jpGames.setBorder(BorderFactory.createTitledBorder(PARTIDAS));

        //Creem el cuadre de text on posarem les partides
        JPanel jpName = new JPanel(new BorderLayout());
        JTextField jtfGames = new JTextField();
        jpName.add(jtfGames, BorderLayout.CENTER);

        //Passem les partides
        partidas = new JList<>(data);
        partidas.setVisibleRowCount(5);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(partidas);
        jpGames.add(scrollPane);

        getContentPane().add(jpGames);
    }

    private void configureFrame() {
        //Caracteristiques del frame
        pack();
        setTitle(FRAME_TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Method that configures any class implementing the ActionListener interface
     * as a listener for all the buttons' clicks.
     *
     * @param listener The ActionListener implementation that will be notified of the clicks.
     */
    public void registerController(ActionListener listener) {
        jbCcontinuar.addActionListener(listener);
        jbElimina.addActionListener(listener);
        jbNueva.addActionListener(listener);
        jbConfig.addActionListener(listener);
    }

    /**
     * Missatge d'error
     *
     * @param message
     * @param title
     */
    public int createDialogOption(String message, String title) {
        int n = JOptionPane.showConfirmDialog(
                getContentPane(),
                message,
                title,
                JOptionPane.YES_NO_OPTION);
        return n;
    }

    /**
     * Missatge d'error
     *
     * @param message
     * @param title
     */
    public void createDialogError(String message, String title) {
        JOptionPane.showMessageDialog(getContentPane(),
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    public void setListData(String[] data) {
        setData(data);
        partidas.setListData(data);
    }

    //Retorna la llista de partidas que tenim a la vista
    public String[] getData() {
        return data;
    }

    //Modifica la llista de partids que tenim a la vista
    public void setData(String[] data) {
        this.data = data;
    }

    //Retornem el index de la partida seleccionada
    public int getPartida() {
        return partidas.getSelectedIndex();
    }
}

