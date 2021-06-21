package Business.Entities;

import java.util.ArrayList;

/**
 * Representa al mapa y contiene toda la informacion necesaria sobre las salas
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class Map {
    private ArrayList<ArrayList<Room>> rooms;
    private String filename;

    public Map() {
        rooms = new ArrayList();
        for (int i = 0; i < 4; i++) {
            rooms.add(new ArrayList());
            for (int j = 0; j < 4; j++) {
                rooms.get(i).add(null);
            }
        }
    }

    /**
     * Método que devuelve las coordenadas del start room como propiedad del mapa
     *
     * @return coordenadas del startRoom
     */

    public int[] getStartRoom() {
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = 0; j < rooms.size(); j++) {
                if (rooms.get(i).get(j) != null) {
                    if (rooms.get(i).get(j).isStartRoom()) {
                        int[] coordenadas = {j, i};
                        return coordenadas;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Getters y setters
     */
    public ArrayList<ArrayList<Room>> getRooms() {
        return rooms;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

















    /*public void setMap(File map){
        mapName = map.getPath();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(map));
            JsonParser jp = new JsonParser();
            JsonObject jo = (JsonObject) jp.parse(bf);

            JsonArray ja = (JsonArray) jo.get("rooms");
            for (int i=0; i < ja.size(); i++){
                JsonObject j = (JsonObject) ja.get(i);
                Room r = new Room(j.get("name").getAsString(), j.get("isStartRoom").getAsBoolean(), j.get("isCompClass").getAsBoolean(), j.get("isLog").getAsBoolean(), j.get("ventRoom").getAsString() );
                JsonArray position = (JsonArray) j.get("position");

                rooms.get(position.get(0).getAsInt()).add(position.get(1).getAsInt(), r);


            }

            System.out.println(rooms.get(0).get(1).getName());

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }*/
}
