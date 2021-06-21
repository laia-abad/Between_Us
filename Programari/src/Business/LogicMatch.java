package Business;

import Business.Entities.*;
import Business.Entities.Character;
import Business.Entities.Map;
import Business.Entities.Log;
import Persistence.JSONmap;
import Persistence.MatchDAO;
import Persistence.SQLMatchDAO;

import java.io.File;
import java.util.*;

/**
 * Implementa todo lo que tenga que ver con las partidas. Desde aquí se llama a los threads
 * que simulan a los personajes, se corrobora si se pueden mover, se trabaja con la base de
 * datos para guardar y acceder a las partidas, y se corrobora la clasificacion de los jugadores
 * segun el usuario.
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class LogicMatch {
    private Character[] characters;
    private LogicNonPlayerImpostor[] logicNonPlayerImpostors;
    private LogicNonPlayerCrew[] logicNonPlayerCrews;
    private Character player;
    private int numImpostors;
    private ArrayList<Log> logs;

    public LogicMatch() {
    }

    /**
     * Creamos los threads de los jugadores simulados en el constructor de LogicMatch
     *
     * @param numPlayers   el numero de jugadores incluyendo los impostores
     * @param numImpostors cantidad de impostores
     * @param colorUser    color del usuario
     * @param xInicial     x del mapa donde deben ser creados
     * @param yInicial     y del mapa donde deben ser creados
     * @param match        el match para poder acceder al mapa
     */

    public LogicMatch(int numPlayers, int numImpostors, int colorUser, int xInicial, int yInicial, Match match) {
        this.numImpostors = numImpostors;
        characters = new Character[numPlayers - 1];
        logicNonPlayerCrews = new LogicNonPlayerCrew[numPlayers - 1];
        logicNonPlayerImpostors = new LogicNonPlayerImpostor[numImpostors];
        this.player = player;

        //Asignamos colores aleatorios
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            arrayList.add(i);
        }

        arrayList.remove(colorUser);
        Integer[] array = arrayList.toArray(arrayList.toArray(new Integer[11]));

        for (int i = 0; i < 11; i++) {
            array[i] = arrayList.get(i);
        }
        Random rgen = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        logs = new ArrayList<>();

        long instante = System.currentTimeMillis();
        //Iniciamos los threads de cada personaje simulado
        //Threads impostores
        for (int i = 0; i < numImpostors; i++) {
            characters[i] = new Impostor(xInicial, yInicial, array[i]);
            logicNonPlayerImpostors[i] = new LogicNonPlayerImpostor((Impostor) characters[i], importMap(match.getMap()), instante, logs);
            new Thread(logicNonPlayerImpostors[i]).start();
        }
        //Threads crews
        for (int j = 0; j < numPlayers - numImpostors - 1; j++) {
            characters[j + numImpostors] = new Crew(xInicial, yInicial, array[j + numImpostors]);
            logicNonPlayerCrews[j] = new LogicNonPlayerCrew((Crew) characters[j + numImpostors], importMap(match.getMap()), instante, logs);
            new Thread(logicNonPlayerCrews[j]).start();
        }
        //Le pasamos characters a los impostores
        for (int i = 0; i < numImpostors; i++) {
            logicNonPlayerImpostors[i].setCharacters(characters);
        }
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    /**
     * Se devuelven los characters desordenados
     *
     * @return array de characters
     */
    public Character[] getCharacters() {
        List<Character> characterList = Arrays.asList(characters);
        Collections.shuffle(characterList);
        characterList.toArray(characters);
        return characters;
    }

    /**
     * Método que borra matchs
     *
     * @param username   del usuario
     * @param name_match el nombre de la partida
     */
    public void deleteMatch(String username, String name_match) {
        MatchDAO matchDAO = new SQLMatchDAO();
        matchDAO.deleteMatch(username, name_match);
    }

    /**
     * Método que copia matchs
     *
     * @param username   del usuario
     * @param name_match el nombre de la partida
     */
    public void copyGame(String username, String name_match) {
        MatchDAO matchDAO = new SQLMatchDAO();
        Match match = matchDAO.getMatch(name_match, username);
        match.setName(name_match + "(Copy)");
        matchDAO.addMatch(match);
    }

    /**
     * Método que carga matchs
     *
     * @param username   del usuario
     * @param name_match el nombre de la partida
     * @return el match cargado
     */
    public Match loadGame(String username, String name_match) {
        MatchDAO matchDAO = new SQLMatchDAO();
        Match match = matchDAO.getMatch(name_match, username);
        match.getFilename(); //load this :/
        return match;
    }

    /**
     * Método que carga todos los matchs
     *
     * @param username del usuario
     * @return la lista de matchs
     */
    public String[] loadAllGames(String username) {
        MatchDAO matchDAO = new SQLMatchDAO();
        ArrayList<Match> matches = matchDAO.getAllMatches(username);
        ArrayList array = new ArrayList();
        for (int i = 0; i < matches.size(); i++) {
            array.add(matches.get(i).getName());
        }
        String[] array2 = (String[]) array.toArray(new String[array.size()]);
        return array2;
    }

    /**
     * Método que importa un mapa a partir de un path
     *
     * @param map es el path del mapa
     * @return el mapa como tal
     */
    public Map importMap(String map) {
        JSONmap jsonMap = new JSONmap();
        return jsonMap.loadTemes(map);
    }

    /**
     * Crea una nueva partida
     *
     * @param gamename        nombre de la partida
     * @param players         numero de jugadores
     * @param impostors       numero de impostores
     * @param color           color del usuario
     * @param map             path del mapa
     * @param username_player
     * @return devuelve un arraylist de posibles errores
     */

    public ArrayList createGame(String gamename, int players, int impostors, int color, String map, String username_player) {
        MatchDAO matchDAO = new SQLMatchDAO();
        ArrayList errores = new ArrayList<String>();
        if (matchDAO.getMatch(gamename, username_player) != null) {
            System.out.println("Ya tienes una partida con ese nombre");
            errores.add("Ya tienes una partida con ese nombre");
        }

        if (impostors > players / 3) {
            System.out.println("El nombre de impostores tiene que ser menor al tercio de los jugadores");
            errores.add("El nombre de impostores tiene que ser menor al tercio de los jugadores");
        }

        if (gamename == null || players == 0 || impostors == 0 || color == -1 || map == null) {
            System.out.println("No has introducido todas las credenciales");
            errores.add("No has introducido todas las credenciales");
        }

        if (errores.size() == 0) {
            String filename = "resources/matches/" + gamename + ".json";
            File fichero = new File(filename);
            Match match = new Match(gamename, username_player, color, impostors, players, map, false, filename);
            matchDAO.addMatch(match);
        }

        return errores;

    }

    /**
     * Método que indica si tanto el usuario se puede mover hacia el costado
     * (si hay sala o no).
     *
     * @param direction del 0 al 3
     * @param player    el jugador con su posicion
     * @param map       para saber si hay salas o no
     * @return boolean que indica si se puede mover o no
     */
    public boolean moveCharacter(int direction, Character player, Map map) {
        switch (direction) {
            case 0: //izquierda
                if (player.getyActual() != 0) {
                    return map.getRooms().get(player.getyActual() - 1).get(player.getxActual()) != null;
                } else {
                    return false;
                }

            case 1: //derecha
                if (player.getyActual() != 3) {
                    return map.getRooms().get(player.getyActual() + 1).get(player.getxActual()) != null;
                } else {
                    return false;
                }

            case 2: //arriba
                if (player.getxActual() != 0) {
                    return map.getRooms().get(player.getyActual()).get(player.getxActual() - 1) != null;
                } else {
                    return false;
                }

            case 3://abajo
                if (player.getxActual() != 3) {
                    return map.getRooms().get(player.getyActual()).get(player.getxActual() + 1) != null;
                } else {
                    return false;
                }

            default:
                return false;
        }
    }

    public void setPlayer(Character player) {
        this.player = player;
        for (int i = 0; i < numImpostors; i++) {
            logicNonPlayerImpostors[i].setPlayer(player);
        }
    }

    /**
     * Método que corrobora que la clasificacion que hizo el usuario sea buena para
     * asi ganar la partida
     *
     * @param characters array de los NPC
     * @return boolean que indica si es correcto o no
     */
    public boolean checkClassification(Character[] characters) {
        for (int i = 0; i < characters.length; i++) {
            switch (characters[i].getSus()) {
                case 0:
                    return false;
                case 1:
                    if (!Impostor.class.isInstance(characters[i])) {
                        return false;
                    }
                    break;
                case 2:
                    if (!Crew.class.isInstance(characters[i])) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

}
