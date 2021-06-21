package Persistence;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */

public class DBConnector {
    /**
     *
     *  @param FILENAME variable estàtica on guardem la ruta de l'arxiu.
     *  @param json variable amb les dades de l'arxiu configuration.json .
     *  @param url variable estàtica on guardem la url.
     *  @param conn variable estàtica del tipus connection.
     *  @param s variable estàtica del tipus Statement..
     *  @param instance variable estàtica del tipus connection.
     */

    private static final String FILENAME = "resources/config.json";
    JSONConfiguration json;
    private static String url;
    private static Connection conn;
    private static Statement s;
    private static DBConnector instance;

    /**
     * Constructor de classe
     */
    public DBConnector() {
        loadConfig();
    }

    /**
     * Carrega les dades del config.json
     */
    private void loadConfig() {
        //Leemos de fichero y parseamos en un JsonElement llamado datos; inicializamos nuestra clase JSONConfiguration.
        JsonParser parser = new JsonParser();
        json = new JSONConfiguration();
        try {
            FileReader fr = new FileReader(FILENAME);
            JsonElement datos = parser.parse(fr);

            //Cargamos datos a nuestra clase JSONConfiguration del fichero "config.json"
            json.setPort(datos.getAsJsonObject().get("port").getAsString());
            json.setIP(datos.getAsJsonObject().get("IP").getAsString());
            json.setDatabase(datos.getAsJsonObject().get("database").getAsString());
            json.setUser(datos.getAsJsonObject().get("user").getAsString());
            json.setPassword(datos.getAsJsonObject().get("password").getAsString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter de la instancia
     * @return una instancia de la conexio de la base
     */
    public static DBConnector getInstance(){
        if(instance == null){
            instance = new DBConnector();
            instance.connect();
        }
        return  instance;
    }

    /**
     * Es connecta a la base de dades
     */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            url = "jdbc:mysql://" + json.getIP() + ":" + json.getPort() + "/" + json.getDatabase();
            conn = (Connection) DriverManager.getConnection(url, json.getUser(), json.getPassword());
            if (conn != null) { //jdbc:subprotocol:subname
                System.out.println("Connexió a base de dades " + url + " ... Ok");
            }
        }
        catch(SQLException ex) {
            System.out.println("Problema al connectarnos a la BBDD --> " + url);
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex);
        }

    }

    /**
     * Fa una query d'inserció a la base de dades
     * @param query query per introduir a la base de dades
     */
    public void insertQuery(String query){
        try {
            s = (Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Problema al Inserir --> " + ex.getSQLState());
        }
    }

    /**
     * Fa una query de actualitzacio
     * @param query query per a actualitzar la base de dades
     */
    public void updateQuery(String query){
        try {
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Modificar --> " + ex.getSQLState());
        }
    }

    /**
     * Fa una query de selecció
     * @param query query per a seleccionar dades
     * @return les dades seleccionades
     */
    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            s =(Statement) conn.createStatement();
            rs = s.executeQuery (query);

        } catch (SQLException ex) {
            System.out.println("Problema al recuperar los datos --> " + ex.getSQLState());
        }
        return rs;
    }

    /**
     * Method that executes a deletion query to the connected database.
     *
     * @param query String representation of the query to execute.
     */
    public void deleteQuery(String query){
        try {
            s = (Statement) conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

    /**
     * Es desconecta de la base de dades
     */
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
        }
    }

}


