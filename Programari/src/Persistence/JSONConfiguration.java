package Persistence;

/**
 * * Classe que permet emmagatzemar les dades de l'arxiu config.json .
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */

public class JSONConfiguration {

    /**
     *
     *  @param port variable on es guarda el port de la base de dades.
     *  @param IP variable IP amb l'adreça de l'arxiu.
     *  @param database nom de la base de dades.
     *  @param user nom de l'usuari.
     *  @param password password assignat a l'usuari.
     */

    private String port;
    private String IP;
    private String database;
    private String user;
    private String password;

    /**
     * Constructors que permet inicialitzar les dades del fitxer.
     */
    public JSONConfiguration() {
    }

    public JSONConfiguration(String port, String IP, String database, String user, String password) {
        this.port = port;
        this.IP = IP;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * Getters i Setters per modificar o accedir a les nostres variables.
     */

    public String getPort() {
        return port;
    }

    public String getIP() {
        return IP;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
