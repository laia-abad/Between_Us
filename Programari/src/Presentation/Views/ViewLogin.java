package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de mostrar la vista del login
 */


public class ViewLogin extends JFrame{

    //declarem unes variables amb text dintre
    public static final String BTN_INICIA = "BTN_INICIA";
    public static final String INICIA_BUTTON_TEXT = "Iniciar sesión";
    public static final String BTN_REGISTRO = "BTN_REGISTRO";
    public static final String REGISTRO_BUTTON_TEXT = "Registrarse  ";

    public static final String FRAME_TITLE = "Login";
    public static final String NORTH_USER_TITLE = "Nombre de usuario o correo electrónico";
    public static final String NORTH_PASSWORD_TITLE = "Contraseña";

    //Declarem variables del tipus adecuat
    private JTextField jtfUsername;
    private JPasswordField jtfPassword;

    private JButton jbIniciarSesion;
    private JButton jbRegistro;

    private void configureNorth() {
        //Creem el panel
        JPanel jpNorth = new JPanel();
        //Posem el titol
        jpNorth.setLayout(new BoxLayout(jpNorth, BoxLayout.Y_AXIS));
        jpNorth.setBorder(BorderFactory.createTitledBorder(NORTH_USER_TITLE));

        //Afegim un panel amb text i un espai de text per introduir el nom d'usuari
        JPanel jpName = new JPanel(new BorderLayout());
        //guardem lo que introdueix el usuari a una variable
        jtfUsername = new JTextField();
        jpName.add(jtfUsername, BorderLayout.CENTER);

        //Afegim el panel al altre panel
        jpNorth.add(jpName);

        //Afegim el panel al frame
        add(jpNorth, BorderLayout.NORTH);
    }


    private void configureCenter() {

        //Creem el panel
        JPanel jpCenter = new JPanel();
        //Posem el titol
        jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));
        jpCenter.setBorder(BorderFactory.createTitledBorder(NORTH_PASSWORD_TITLE));

        //Afegim un panel amb text i un espai de text per introduir el nom d'usuari
        JPanel jpContra = new JPanel(new BorderLayout());
        //Guardem lo que introdueix el usuari a una variable*
        jtfPassword = new JPasswordField();
        jpContra.add(jtfPassword, BorderLayout.CENTER);

        //Afegim el panel al altre panel
        jpCenter.add(jpContra);

        //Afegim el panel al frame
        add(jpCenter, BorderLayout.CENTER);
    }


    private void configureSouth() {

        //Creem el panel
        JPanel jpSouth = new JPanel();
        jpSouth.setLayout(new BoxLayout(jpSouth, BoxLayout.X_AXIS));

        //Creeem els botons
        jbIniciarSesion = new JButton(INICIA_BUTTON_TEXT);
        jbIniciarSesion.setActionCommand(BTN_INICIA);
        jbRegistro = new JButton(REGISTRO_BUTTON_TEXT);
        jbRegistro.setActionCommand(BTN_REGISTRO);

        //Afegim els botons al panel
        jpSouth.add(jbIniciarSesion);
        jpSouth.add(jbRegistro);

        //Afegim el panel al frame
        add(jpSouth, BorderLayout.SOUTH);
    }

    /**
     * Especifiquem tota la configuracio del Frame
     **/
    private void configureFrame() {
        setSize(325,150);
        setTitle(FRAME_TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public ViewLogin(){
        configureNorth();
        configureCenter();
        configureSouth();
        configureFrame();
    }


    /**
     * Method that returns the username input.
     *
     * @return A string containing the entered name.
     */
    public String getUsernameInput() {
        return jtfUsername.getText();
    }

    /**
     * Method that returns the password input.
     *
     * @return A string containing the entered password.
     */

    public String getPasswordInput() {return jtfPassword.getText(); }


    /**
     * Method that resets both textfields
     */
    public void resetInput() {
        jtfUsername.setText("");
        jtfPassword.setText("");
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


    /**
     * Method that configures any class implementing the ActionListener interface
     * as a listener for all the buttons' clicks.
     *
     * @param listener The ActionListener implementation that will be notified of the clicks.
     */
    public void registerController(ActionListener listener) {
        jbIniciarSesion.addActionListener(listener);
        jbRegistro.addActionListener(listener);
    }


}