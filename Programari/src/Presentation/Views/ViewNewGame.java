package Presentation.Views;

import Business.Entities.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Esta vista permite elegir al jugador el numero de jugadores, de impostores
 * su color y el mapa.
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class ViewNewGame extends JFrame{

    public static final String BTN_INICIA = "BTN_INICIA";
    public static final String BTN_MAIN = "BTN_MAIN";
    public static final String FRAME_TITLE = "CONFIG PARTIDA";
    public static final String NORTH_COLOR = "COLOR";
    public static final String NORTH_IMPOSTOR = "IMPOSTORES";


    public static final String INICIA_BUTTON_TEXT = "START";



    private JButton jbMap;
    private JButton jbStart;

    private JTextField jtfGameName;
    private JList<String> jlImpostor;
    private JList<String> jlColor;
    private JList<String> jlPlayers;


    public ViewNewGame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
        getContentPane().add(drawConfig());
        pack();
        configurePlayers();
        configureImpostor();
        configureColor();
        configureMap();
        configureButton();
        configureFrame();
    }


    private JPanel drawConfig(){

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(LabelText("NOMBRE DE LA PARTIDA"));
        return panel;
    }

    private JPanel LabelText(String nombre){
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(nombre),BorderLayout.NORTH);
        panel.add(jtfGameName = new JTextField(16),BorderLayout.CENTER);

        return panel;
    }


    private void configureMap() {

        JPanel jp1 = new JPanel();

        jbMap = new JButton("MAP");
        //jbCcontinuar.setSize(400, 200);
        jbMap.setPreferredSize(new Dimension(100, 80));

        // IMPORTANT: We define the buttons' action commands to distinguish them
        jbMap.setActionCommand(BTN_MAIN);

        jp1.add(jbMap);
        getContentPane().add(jp1, BorderLayout.AFTER_LINE_ENDS);

    }

    private void configurePlayers() {

        JPanel jpPlayers = new JPanel();

        jpPlayers .setLayout(new BoxLayout(jpPlayers , BoxLayout.PAGE_AXIS));
        jpPlayers .setBorder(BorderFactory.createTitledBorder( "JUGADORES"));


        String[] data = {"      4       ", "      5       ", "      6       "
                , "      7      ", "      8       ", "      9       "
                , "     10       "};
        jlPlayers =  new JList<>(data);
        jlPlayers.setVisibleRowCount(3);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jlPlayers);
        jpPlayers.add(scrollPane);

        getContentPane().add(jpPlayers );
    }


    private void configureImpostor() {

        JPanel jpImpostor = new JPanel();

        jpImpostor.setLayout(new BoxLayout(jpImpostor, BoxLayout.PAGE_AXIS));
        jpImpostor.setBorder(BorderFactory.createTitledBorder( NORTH_IMPOSTOR));


        String[] data = {"         1          ", "         2          ", "         3          "};
        jlImpostor =  new JList<>(data);
        jlImpostor.setVisibleRowCount(3);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jlImpostor);
        jpImpostor.add(scrollPane);

        getContentPane().add(jpImpostor);

    }



    private void configureColor() {

        JPanel jpColor = new JPanel();

        jpColor.setLayout(new BoxLayout(jpColor, BoxLayout.PAGE_AXIS));
        jpColor.setBorder(BorderFactory.createTitledBorder( NORTH_COLOR));


        String[] data = {"  RED ", "  BLUE ", "  GREEN ", "  PINK ", "  ORANGE ", "  YELLOW ", "  BLACK ","  WHITE ","  PURPLE ", "  BROWN ","  CYAN ","  LIME "};
        jlColor =  new JList<>(data);
        jlColor.setVisibleRowCount(3);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jlColor);
        jpColor.add(scrollPane);

        getContentPane().add(jpColor);


    }


    private void configureButton() {

        JPanel jpButton = new JPanel();
        jpButton.setLayout(new BoxLayout(jpButton, BoxLayout.Y_AXIS));

        jbStart = new JButton(INICIA_BUTTON_TEXT);
        jbStart.setActionCommand(BTN_INICIA);

        jpButton.add(jbStart);

        add(jpButton, BorderLayout.SOUTH);
    }


    private void configureFrame() {
        setSize(215,350);
        setTitle(FRAME_TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    /**
     * Method that returns the game name input.
     *
     * @return A string containing the entered name.
     */
    public String getGameNameInput() {
        return jtfGameName.getText();
    }

    /**
     * Method that returns the players input.
     *
     * @return A string containing the entered name.
     */
    public int getPlayersInput() {
        return jlPlayers.getSelectedIndex()+4;
    }

    /**
     * Method that returns the impostor input.
     *
     * @return A string containing the entered name.
     */
    public int getImpostorInput() {
        return jlImpostor.getSelectedIndex()+1;
    }

    /**
     * Method that returns the color input.
     *
     * @return A string containing the entered name.
     */
    public int getColorInput() {
        return jlColor.getSelectedIndex();
    }



    /**
     * Method that resets both textfields
     */
    public void resetInput() {
        jtfGameName.setText("");

    }


    /**
     * Method that configures any class implementing the ActionListener interface
     * as a listener for all the buttons' clicks.
     *
     * @param listener The ActionListener implementation that will be notified of the clicks.
     */
    public void registerController(ActionListener listener) {

        jbStart.addActionListener(listener);
        jbMap.addActionListener(listener);
    }

    public File fileChooser(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter _fileExtensionFilter = new FileNameExtensionFilter(".json (mapas)", "json");
        fileChooser.setFileFilter(_fileExtensionFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showSaveDialog(null);

        return fileChooser.getSelectedFile();
    }

    /**
     * Metodo que le muestra error al usuario
     */
    public void createDialogError(String message, String title) {
        JOptionPane.showMessageDialog(getContentPane(),
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

}
