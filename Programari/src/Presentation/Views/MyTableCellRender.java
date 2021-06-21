package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 *  Aquesta classe va incorporada a la jTable i ens fa la funció
 *  de render de les files, per tal d'afegir-hi
 *  un background color de manera automàtica cada vegada que s'actualitza.
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class MyTableCellRender extends DefaultTableCellRenderer {

    public MyTableCellRender() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Printem els colors corresponents només per la primera columna tal i com el disseny de l'enunciat.
        if (column == 0) {
            //TODO: Afegir els colors que falten (màxim 10 com a la vista de viewTrain)
          
            cell.setForeground(Color.darkGray);

            if (value.toString().contains("Red")) {
                cell.setBackground(Color.red);
            } else if (value.toString().contains("Orange")) {
                cell.setBackground(Color.orange);
            } else if (value.toString().contains("Green")) {
                cell.setBackground(Color.green.darker());
            } else if (value.toString().contains("Blue")) {
                cell.setBackground(Color.blue);
            } else if (value.toString().contains("Pink")) {
                cell.setBackground(Color.pink);
            } else if (value.toString().contains("Yellow")) {
                cell.setBackground(Color.yellow);
            } else if (value.toString().contains("Black")) {
                cell.setBackground(Color.black);
            } else if (value.toString().contains("White")) {
                cell.setBackground(Color.white);
            } else if (value.toString().contains("Purple")) {
                cell.setBackground(new Color(108, 36, 171));
            } else if (value.toString().contains("Lime")) {
                cell.setBackground(new Color(157, 255, 0));
            } else if (value.toString().contains("Cyan")) {
                cell.setBackground(Color.cyan);
            } else if (value.toString().contains("Brown")) {
                cell.setBackground(new Color(150, 75, 0));
            }

        } else {
            cell.setBackground(Color.white);
            cell.setForeground(Color.black);
        }

        return this;
    }
}
