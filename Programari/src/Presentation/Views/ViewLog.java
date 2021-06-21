package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 * La view Log muestra cuales usuarios entraron a las salas que tienen esta propiedad
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */
public class ViewLog extends JFrame{

    private JTable jTable;
    private Object[][] data;
    private String[] columnNames = {"Crewmate", "Room", "Instant"};
    public static final String BTN_VOLVER = "BTN_VOLVER";
    private JButton jbVolver;

    public ViewLog(){

        // Inicialitzacions base de la vista
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        pack();
        setSize(800, 600);
        jTable = new JTable();

        jTable.setDefaultRenderer(Object.class, new MyTableCellRender());
        jTable.setDefaultEditor(Object.class, null);

        jTable.getTableHeader().setBackground(Color.black);
        jTable.getTableHeader().setForeground(Color.white);

        jbVolver = new JButton("<- Volver a la partida");
        jbVolver.setActionCommand(BTN_VOLVER);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        getContentPane().add(jScrollPane, BorderLayout.CENTER);
        getContentPane().add(jbVolver, BorderLayout.SOUTH);
    }

    public JTable getjTable() {
        return jTable;
    }

    /**
     * Method that configures any class implementing the ActionListener interface
     * as a listener for all the buttons' clicks.
     *
     * @param listener The ActionListener implementation that will be notified of the clicks.
     */
    public void registerController(MouseListener listener, ActionListener listener2) {
        jTable.addMouseListener(listener);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jbVolver.addActionListener(listener2);
    }

    /**
     * Aquesta funció s'encarrega de actualitzar la taula sencera, amb el paràmetre que passem.
     */
    public void refreshView() {
        getUpdatedModel().fireTableDataChanged();
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    /**
     * Funció fragmentada que utilitza el tableModel per realitzar l'actualització de les dades de la nostre jTable.
     * @return Retorna el model de la nostre taula.
     */
    private DefaultTableModel getUpdatedModel(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        for (Object[] newDatum : data) {
            String[] dataString = new String[3];

            dataString[0] = (String) newDatum[0];
            dataString[1] = (String) newDatum[1];
            dataString[2] = (String) newDatum[2];

            tableModel.addRow(dataString);
        }
        jTable.setModel(tableModel);
        return tableModel;
    }
}