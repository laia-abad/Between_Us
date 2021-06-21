package Business;

import Business.Entities.*;
import Business.Entities.Character;
import Business.Entities.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementa el thread de cada impostor simulado
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */
public class LogicNonPlayerImpostor implements Runnable {
    private Impostor impostor;
    private Map map;
    private Character[] characters;
    private Character player;
    private long instante;
    private ArrayList<Log> logs;


    public LogicNonPlayerImpostor(Impostor impostor, Map map, long instante, ArrayList<Log> logs) {
        this.map = map;
        this.impostor = impostor;
        this.impostor.setxActual(map.getStartRoom()[0]);
        this.impostor.setyActual(map.getStartRoom()[1]);
        this.impostor.setxAnterior(map.getStartRoom()[0]);
        this.impostor.setyAnterior(map.getStartRoom()[1]);
        this.instante = instante;
        this.logs = logs;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    /**
     * El impostor siempre estará vivo e irá moviéndose y matando crews
     * como lo indica el enunciado
     */
    @Override
    public void run() {

        long tiempo25 = System.currentTimeMillis();

        while (true) { // este vivo
            Random rgen = new Random();
            int tiempo = rgen.nextInt(80 - 60 + 1) + 60;
            //int tiempo = rgen.nextInt(20);
            //Durante ese tiempo generado puede matar a algÃºn tripulante
            for (int i = 0; i < tiempo; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (System.currentTimeMillis() - tiempo25 > 25000) { //pasaron 25 segundos
                    //Lógica de cómo matan los impostores
                    int personas = 0;
                    int posArray = 0;
                    boolean isPlayer = false;
                    for (int j = 0; j < characters.length; j++) {
                        if (impostor.getxActual() == characters[j].getxActual() && impostor.getyActual() == characters[j].getyActual() && characters[j] != impostor) {
                            if (Crew.class.isInstance(characters[posArray])) {
                                Crew crew = (Crew) characters[posArray];
                                if (crew.isAlive()) {
                                    personas++;
                                    posArray = j;
                                }
                            } else {
                                personas++;
                            }
                        }
                        if (impostor.getxActual() == player.getxActual() && impostor.getyActual() == player.getyActual()) {
                            personas++;
                            isPlayer = true;
                        }
                        if (personas == 1) { //
                            if (isPlayer) {
                                //matar jugador i acabar la partida
                            } else if (Crew.class.isInstance(characters[posArray])) {
                                Crew crew = (Crew) characters[posArray];
                                if (crew.isAlive()) {
                                    crew.setIsAlive(false);
                                    characters[posArray] = crew;
                                    move();
                                }
                            }
                        }

                    }
                    tiempo25 = System.currentTimeMillis();
                }
            }
            //Faltan los conductos de ventilacion
            int num = rgen.nextInt(100);
            if (num < 45) {
                move();
            }
        }
    }

    /**
     * Método que mueve al impostor, accediendo a su xActual y su yActual
     */
    public void move() {
        Random rgen = new Random();
        boolean seMovio = false;
        do {
            LogicMatch l = new LogicMatch();
            int direccion = rgen.nextInt(4);
            if (l.moveCharacter(direccion, impostor, map)) {
                switch (direccion) {
                    case 0: //IZQUIERDA
                        impostor.setyAnterior(impostor.getyActual());
                        impostor.setyActual(impostor.getyActual() - 1);

                        seMovio = true;
                        break;
                    case 1: //DERECHA
                        impostor.setyAnterior(impostor.getyActual());
                        impostor.setyActual(impostor.getyActual() + 1);
                        seMovio = true;
                        break;
                    case 2: //arriba
                        impostor.setxAnterior(impostor.getxActual());
                        impostor.setxActual(impostor.getxActual() - 1);
                        seMovio = true;
                        break;
                    case 3: //abajo
                        impostor.setxAnterior(impostor.getxActual());
                        impostor.setxActual(impostor.getxActual() + 1);
                        seMovio = true;
                        break;
                }
            }
            if (map.getRooms().get(impostor.getyActual()).get(impostor.getxActual()).isCanLog()) {
                if (logs.size() == 5) {
                    logs.remove(logs.size() - 1);
                }
                int ahora = (int)(System.currentTimeMillis() - instante) / 1000;
                Log log = new Log(impostor, map.getRooms().get(impostor.getyActual()).get(impostor.getxActual()).getName(), ahora);
                logs.add(log);
            }
        } while (!seMovio);
    }
}