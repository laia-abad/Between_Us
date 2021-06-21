package Business.Entities;

/**
 * Representa una partida y contiene toda la informacion relacionada a la partida,
 * la cual se obtiene en su mayoria en la vista GameManagement. Tambien tiene al mapa.
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class Match {
    private static final int RED = 0;
    private static final int BLUE = 1;
    private static final int GREEN = 2;
    private static final int PINK = 3;
    private static final int ORANGE = 4;
    private static final int YELLOW = 5;
    private static final int BLACK = 6;
    private static final int WHITE = 7;
    private static final int PURPLE = 8;
    private static final int BROWN = 9;
    private static final int CYAN = 10;
    private static final int LIME = 11;

    private String name;
    private String username_player;
    private int color_player;
    private int n_impostors;
    private int n_players;
    private String map;
    private boolean ended;
    private String filename;

    public Match (String name, String username_player, int color_player, int n_impostors, int n_players, String map, boolean ended, String filename) {
        this.name = name;
        this.username_player = username_player;
        this.color_player = color_player;
        this.n_impostors = n_impostors;
        this.n_players = n_players;
        this.map = map;
        this.ended = ended;
        this.filename = filename;
    }

    public Match () {}

    /**
     * Getters y setters
     */

    public void setColor_player(int color_player) {
        this.color_player = color_player;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public void setN_impostors(int n_impostors) {
        this.n_impostors = n_impostors;
    }

    public void setN_players(int n_players) {
        this.n_players = n_players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername_player(String username_player) {
        this.username_player = username_player;
    }

    public String getUsername_player() {
        return username_player;
    }

    public int getColor_player() {
        return color_player;
    }

    public int getN_impostors() {
        return n_impostors;
    }

    public int getN_players() {
        return n_players;
    }

    public String getMap() {
        return map;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isEnded() {
        return ended;
    }
}
