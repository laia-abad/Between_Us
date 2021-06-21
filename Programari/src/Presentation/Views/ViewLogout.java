package Presentation.Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

/**
 * Esta vista permite al usuario loguearse
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class ViewLogout extends JFrame {

    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETE = "BTN_DELETE";
    public static final String FRAME_TITLE = "LOGOUT";

    public static final String BUTTON_LOGOUT_TEXT = "LOGOUT";
    public static final String BUTTON_DELETE_TEXT = "DELETE";


    private JTextField jtfGameName;


    private JButton jbLogout;
    private JButton jbDelete;
    private JButton jbImage;


    public ViewLogout() throws IOException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
        configureImage();
        getContentPane().add(drawConfig());
        pack();
        configureButtonLogout();
        configureButtonDelete();
        configureFrame();
    }

    private void configureImage() throws IOException {

       JPanel jp1 = new JPanel();

        BufferedImage imagen = ImageIO.read(new File("resources/usuario.jpg"));
        Image newImage = imagen.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        jbImage = new JButton(new ImageIcon(newImage));

        jp1.add(jbImage);

        getContentPane().add(jp1, BorderLayout.AFTER_LINE_ENDS);

    }
    private JPanel drawConfig(){

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(LabelText("USERNAME/MAIL"));
        panel.add(LabelText("PASSWORD"));
        return panel;
    }

    private JPanel LabelText(String nombre){
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(nombre),BorderLayout.NORTH);
        panel.add(jtfGameName = new JTextField(16),BorderLayout.CENTER);
        panel.add(jtfGameName = new JTextField(16),BorderLayout.CENTER);

        return panel;
    }

    private void configureButtonLogout() {

        JPanel jpButton = new JPanel();
        jpButton.setLayout(new BoxLayout(jpButton, BoxLayout.Y_AXIS));

        jbLogout = new JButton(BUTTON_LOGOUT_TEXT);
        jbLogout.setActionCommand(BTN_LOGOUT);

        jpButton.add(jbLogout);

        add(jpButton, BorderLayout.SOUTH);
    }

    private void configureButtonDelete() {

        JPanel jpButton = new JPanel();
        jpButton.setLayout(new BoxLayout(jpButton, BoxLayout.Y_AXIS));

        jbDelete = new JButton(BUTTON_DELETE_TEXT);
        jbDelete.setActionCommand(BTN_DELETE);

        jpButton.add(jbDelete);

        add(jpButton, BorderLayout.SOUTH);
    }


    private void configureFrame() {
        setSize(305,160);
        setTitle(FRAME_TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    public void logoutController(ActionListener listener) {
        jbDelete.addActionListener(listener);
        jbLogout.addActionListener(listener);
    }

}
