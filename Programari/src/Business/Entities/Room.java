package Business.Entities;

/**
 * Representa una sala del mapa y contiene toda la informacion sobre las propiedades como
 * log, comparar clasificaciones, vent room, aparte de las coordenadas y el color. Todo esto
 * se completa con la informacion del fichero json.map
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class Room {
    private int position;
    private int x;
    private int y;
    private int color;
    private String name;        //el enunciado dice que tiene nombre
    private boolean isStartRoom; //habitacion de inicio
    private boolean isCompClass; //si se puede comprobar la clasificacion
    private boolean isLog;
    private boolean canLog;
    private String ventRoom; //name de la habitacion del vent


    public Room (){}

    public Room(int x, int y,int color,String name, boolean isStartRoom, boolean isCompClass, boolean isLog, String ventRoom) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.name = name;
        this.isStartRoom = isStartRoom;
        this.isCompClass = isCompClass;
        this.isLog = isLog;
        this.ventRoom = ventRoom;
    }

    /**
     * Getters y setters
     */

    public int getX() { return x; }

    public int getY() { return y; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void setColor(int color) { this.color = color; }

    public int getColor() { return color; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setStartRoom(boolean startRoom) {
        isStartRoom = startRoom;
    }

    public String getVentRoom() {
        return ventRoom;
    }

    public void setCompClass(boolean compClass) {
        isCompClass = compClass;
    }

    public void setLog(boolean log) {
        isLog = log;
    }

    public void setVentRoom(String ventRoom) {
        this.ventRoom = ventRoom;
    }

    public boolean isCompClass() {
        return isCompClass;
    }

    public boolean isStartRoom() {
        return isStartRoom;
    }

    public boolean isLog() {
        return isLog;
    }

    public void setCanLog(boolean canLog) {
        this.canLog = canLog;
    }

    public boolean isCanLog() {
        return canLog;
    }

    public void setPosition(int nextInt) {this.position = nextInt; }
}