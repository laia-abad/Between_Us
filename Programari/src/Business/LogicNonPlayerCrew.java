package Business;

import Business.Entities.Crew;
import Business.Entities.Log;
import Business.Entities.Map;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementa el thread de cada crew simulado
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class LogicNonPlayerCrew implements Runnable {
    private Crew crew;
    private Map map;
    private long instante;
    private ArrayList<Log> logs;

    public LogicNonPlayerCrew(Crew crew, Map map, long instante, ArrayList<Log> logs) {
        this.crew = crew;
        this.map = map;
        this.crew.setxActual(map.getStartRoom()[0]);
        this.crew.setyActual(map.getStartRoom()[1]);
        this.crew.setxAnterior(map.getStartRoom()[0]);
        this.crew.setyAnterior(map.getStartRoom()[1]);
        this.instante = instante;
        this.logs = logs;
    }

    /**
     * Mientras el crew esté vivo se irá moviendo tal como indica el enunciado
     */
    @Override
    public void run() {
        while (crew.isAlive()) {

            Random rgen = new Random();
            int tiempo = rgen.nextInt(15000 - 5000 + 1) + 5000;
            //int tiempo = rgen.nextInt(1000);
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (rgen.nextInt(100) < 55) {
                boolean seMovio = false;
                LogicMatch l = new LogicMatch();
                boolean noAlternativa=false;
                do {
                    int direccion = rgen.nextInt(4);
                    if (l.moveCharacter(direccion, crew, map)&&crew.isAlive()) {
                        switch (direccion) {
                            case 0: //IZQUIERDA
                                if (crew.getyActual() - 1 == crew.getyAnterior()&&!noAlternativa) {
                                    noAlternativa=true;
                                } else {
                                    crew.setyAnterior(crew.getyActual());
                                    crew.setyActual(crew.getyActual() - 1);
                                    seMovio = true;
                                    noAlternativa=false;
                                }
                                break;
                            case 1: //DERECHA
                                if(crew.getyActual() + 1==crew.getyAnterior()&&!noAlternativa){
                                    noAlternativa=true;
                                }else {
                                    crew.setyAnterior(crew.getyActual());
                                    crew.setyActual(crew.getyActual() + 1);
                                    seMovio = true;
                                    noAlternativa=false;
                                }
                                break;
                            case 2: //arriba
                                if(crew.getxActual() - 1==crew.getxAnterior()&&!noAlternativa){
                                    noAlternativa=true;
                                }else {
                                    crew.setxAnterior(crew.getxActual());
                                    crew.setxActual(crew.getxActual() - 1);
                                    seMovio = true;
                                    noAlternativa=false;
                                }
                                break;
                            case 3: //abajo
                                if(crew.getxActual() + 1==crew.getxAnterior()&&!noAlternativa){
                                    noAlternativa=true;
                                }else {
                                    crew.setxAnterior(crew.getxActual());
                                    crew.setxActual(crew.getxActual() + 1);
                                    seMovio = true;
                                    noAlternativa=false;
                                }
                                break;
                        }
                    }
                    if (map.getRooms().get(crew.getyActual()).get(crew.getxActual()).isCanLog()) {
                        if (logs.size() == 5) {
                            logs.remove(logs.size() - 1);
                        }
                        int ahora = (int)(System.currentTimeMillis() - instante) / 1000;
                        Log log = new Log(crew, map.getRooms().get(crew.getyActual()).get(crew.getxActual()).getName(), ahora);
                        logs.add(log);
                    }

                } while (!seMovio);

            }
        }
    }
}
