package Presentation.Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de mostrar la vista del registre
 */

public class ViewRegister extends JFrame {

    //Declarem el text dels botons
    public static final String BTN_MAIN = "BTN_MAIN";
    public static final String BTN_LOGIN = "BTN_LOGIN";

    //Declarem unes variables amb text dintre
    public static final String FRAME_TITLE = "User sign-up";
    public static final String ENVIAR_BUTTON_TEXT = "Enviar";
    public static final String LOGIN_BUTTON_TEXT = "Login";
    public static final String USER_TITLE = "Nombre de usuario: ";
    public static final String EMAIL_TITLE = "Email:                          ";
    public static final String PASS_TITLE = "Contraseña:              ";
    public static final String PASSCONFIRM_TITLE = "Repetir Contraseña:";

    //Declarem variables del tipus adecuat
    private JLabel jLNomUser;
    private JLabel jLMail;
    private JLabel jLPassword;
    private JLabel jLPasswordVerifier;
    private JButton jbEnviar;
    private JButton jbLogin;

    static JTextField jtfUser = new JTextField(20);  //creating JTextField.
    static JTextField jtfMail = new JTextField(20);  //creating JTextField.
    static JPasswordField jtfPassword = new JPasswordField(20);  //creating JTextField.
    static JPasswordField jtfPasswordVerifier = new JPasswordField(20);  //creating JTextField.

    private ViewLogin l;

    /**
     * Constructor de la classe
     **/

    public ViewRegister() {

        getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
        configureCenter();
        configureSouth();
        configureFrame();
        setLayout(new FlowLayout());
        //carcteristiques del Frame
        setSize(300, 300);
        this.setLocationRelativeTo(null);

    }

    private void configureCenter() {

        //Creem el panel
        JPanel jpCentro = new JPanel();
        jpCentro.setLayout(new BorderLayout());
        jpCentro.setBorder(BorderFactory.createTitledBorder(USER_TITLE));
        //Guardem lo que introdueix el usuari a una variable
        jLNomUser = new JLabel(USER_TITLE);
        jpCentro.add(jLNomUser, BorderLayout.AFTER_LINE_ENDS);
        jpCentro.add(jtfUser, BorderLayout.AFTER_LINE_ENDS);

        //Creem el panel
        JPanel jpCentro1 = new JPanel();
        jpCentro1.setLayout(new BorderLayout());
        jpCentro1.setBorder(BorderFactory.createTitledBorder(EMAIL_TITLE));
        //Guardem lo que introdueix el usuari a una variable
        jLMail = new JLabel(EMAIL_TITLE);
        jpCentro1.add(jLMail, BorderLayout.AFTER_LINE_ENDS);
        jpCentro1.add(jtfMail, BorderLayout.AFTER_LINE_ENDS);

        //Creem el panel
        JPanel jpCentro2 = new JPanel();
        jpCentro2.setLayout(new BorderLayout());
        jpCentro2.setBorder(BorderFactory.createTitledBorder(PASS_TITLE));
        //Guardem lo que introdueix el usuari a una variable
        jLPassword = new JLabel(PASS_TITLE);
        jpCentro2.add(jLPassword, BorderLayout.AFTER_LINE_ENDS);
        jpCentro2.add(jtfPassword, BorderLayout.AFTER_LINE_ENDS);

        //Creem el panel
        JPanel jpCentro3 = new JPanel();
        jpCentro3.setLayout(new BorderLayout());
        jpCentro3.setBorder(BorderFactory.createTitledBorder(PASSCONFIRM_TITLE));
        //Guardem lo que introdueix el usuari a una variable
        jLPasswordVerifier = new JLabel(PASSCONFIRM_TITLE);
        jpCentro3.add(jLPasswordVerifier, BorderLayout.AFTER_LINE_ENDS);
        jpCentro3.add(jtfPasswordVerifier, BorderLayout.AFTER_LINE_ENDS);

        //Afegim el contigut del panel al frame
        getContentPane().add(jpCentro);
        getContentPane().add(jpCentro1);
        getContentPane().add(jpCentro2);
        getContentPane().add(jpCentro3);

    }


    private void configureSouth() {

        //Creem el panel
        JPanel jpSouth = new JPanel();
        jbEnviar = new JButton(ENVIAR_BUTTON_TEXT);
        jbLogin = new JButton(LOGIN_BUTTON_TEXT);

        // IMPORTANT: We define the buttons' action commands to distinguish them
        jbEnviar.setActionCommand(BTN_MAIN);
        jbLogin.setActionCommand(BTN_LOGIN);

        //Afegim els botons al panel
        jpSouth.add(jbEnviar);
        jpSouth.add(jbLogin);

        //Afegim el panel al frame
        getContentPane().add(jpSouth, BorderLayout.SOUTH);
    }


    private void configureFrame() {
        // Posem titol i la manera de tancar la vista en el frame
        pack();
        setTitle(FRAME_TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method that resets both textfields
     */
    public void resetInput() {
        jtfUser.setText("");
        jtfMail.setText("");
        jtfPassword.setText("");
        jtfPasswordVerifier.setText("");
    }

    /**
     * Method that configures any class implementing the ActionListener interface
     * as a listener for all the buttons' clicks.
     *
     * @param listener The ActionListener implementation that will be notified of the clicks.
     */
    public void registerController(ActionListener listener) {
        jbEnviar.addActionListener(listener);
        //jbCancelar.addActionListener(listener);
        jbLogin.addActionListener(listener);

    }
    /**
     * Method that returns the user input.
     *
     * @return A string containing the entered username.
     */

    public String getJtfUser() {
        return jtfUser.getText();
    }

    /**
     * Method that returns the mail input.
     *
     * @return A string containing the entered mail.
     */

    public String getJtfMail() {
        return jtfMail.getText();
    }

    /**
     * Method that returns the password input.
     *
     * @return A string containing the entered password.
     */
    public String getJtfPassword() {
        return jtfPassword.getText();
    }

    /**
     * Method that returns the password verifier input.
     *
     * @return A string containing the entered password verified.
     */
    public String getJtfPasswordVerifier() {
        return jtfPasswordVerifier.getText();
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
}
