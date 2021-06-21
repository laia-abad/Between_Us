package Presentation.Controller;

import Business.Entities.Log;
import Presentation.Views.ThreadPlayerAction;
import Presentation.Views.ViewLog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Controlador que permet gestionar totes les opcions que hi han a ViewLog.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */

public class ControllerLog implements MouseListener, ActionListener {

    private ViewLog viewLog;

    // Informació de la taula (IMPORTANT MANTENIR EL FORMAT!)
    public String[][] data;
    private ThreadPlayerAction thread;
    private ArrayList<Log> logs;
    private MainController mainController;


    public ControllerLog(ViewLog viewLog){
        this.viewLog= viewLog;
    }

    /**
     * Métode que permet diferenciar entre els diferents rols dels personatges de la partida, ja bé siguin sospitossos de ser impostors,
     * no sospitossos o bé sense un rol encara assignat.
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        // Agafem el valor del index de la fila que l'usuari ha apretat i d'aquesta persejem i extreiem el valor del role i del color que figura en la columna 0 (Unknown (Red))
        int row = viewLog.getjTable().rowAtPoint(e.getPoint());
        String roleName = viewLog.getjTable().getValueAt(row, 0).toString().split(" ")[0];
        String color = viewLog.getjTable().getValueAt(row, 0).toString().split(" ")[1];

        // Ens recorrem tota la nostre matriu i mirem totes les coincidencies de la taula amb el valor clickat, per tal d'actualitzar-les totes.
        for (Object[] rowToUpdate : data) {
            if (rowToUpdate[0].toString().contains(viewLog.getjTable().getValueAt(row, 0).toString())){
                if (roleName.equals("Unknown")) {
                    rowToUpdate[0] = (String) "Sus " + color;
                }
                if (roleName.equals("Sus")) {
                    rowToUpdate[0] = (String) "Not Sus " + color;
                }
                if (roleName.equals("Not")) {
                    color = viewLog.getjTable().getValueAt(row, 0).toString().split(" ")[2];
                    rowToUpdate[0] = (String) "Unknown " + color;
                }
            }
        }
        // Cridem la funció per actualitzar la nostre taula, amb la informació de la matriu ja actualitzada i preparada.
        viewLog.setData(data);
        viewLog.refreshView();
    }

    /**
     * Getters i Setters per modificar o accedir a les nostres variables.
     */
    public Object[][] getData() {
        return data;
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
        setData(logs);
    }

    public void setData(ArrayList<Log> logs) {
        data = new String[5][3];
        if (logs != null) {
            for (int i = 0; i < logs.size(); i++) {
                if (logs.get(i) != null) {
                    String crewmate = "";
                    switch (logs.get(i).getCharacter().getSus()) {
                        case 0:
                            crewmate = "Unknown";
                            break;
                        case 1:
                            crewmate = "Sus";
                            break;
                        case 2:
                            crewmate = "Not Sus";
                            break;
                    }
                    switch (logs.get(i).getCharacter().getColor()) {
                        case 0:
                            crewmate = crewmate + " (Red)";
                            break;
                        case 1:
                            crewmate = crewmate + " (Blue)";
                            break;
                        case 2:
                            crewmate = crewmate + " (Green)";
                            break;
                        case 3:
                            crewmate = crewmate + " (Pink)";
                            break;
                        case 4:
                            crewmate = crewmate + " (Orange)";
                            break;
                        case 5:
                            crewmate = crewmate + " (Yellow)";
                            break;
                        case 6:
                            crewmate = crewmate + " (Black)";
                            break;
                        case 7:
                            crewmate = crewmate + " (White)";
                            break;
                        case 8:
                            crewmate = crewmate + " (Purple)";
                            break;
                        case 9:
                            crewmate = crewmate + " (Brown)";
                            break;
                        case 10:
                            crewmate = crewmate + " (Cyan)";
                            break;
                        case 11:
                            crewmate = crewmate + " (Lime)";
                            break;


                    }
                    data[i][0] = crewmate;
                    data[i][1] = logs.get(i).getRoom();
                    data[i][2] = Integer.toString(logs.get(i).getInstant());
                } else {
                    data[i][0] = " ";
                    data[i][1] = " ";
                    data[i][2] = " ";
                }
            }
            if(logs.size()<5){
                for(int i=0;i<5-logs.size();i++){
                    data[i][0] = " ";
                    data[i][1] = " ";
                    data[i][2] = " ";
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                data[i][0] = " ";
                data[i][1] = " ";
                data[i][2] = " ";
            }
        }
        viewLog.setData(data);
        viewLog.refreshView();
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        viewLog.setVisible(false);
        mainController.showVistaPlayerAction();
    }
}

