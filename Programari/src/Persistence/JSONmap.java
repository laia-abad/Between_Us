package Persistence;

import Business.Entities.Map;
import Business.Entities.Room;
import com.google.gson.stream.JsonReader;


import java.io.FileReader;
import java.io.IOException;

public class JSONmap {

    private static final String FILENAME_MAP = "resources/map.json";

    /**
     * Llegeix totes les dades del fitxer en referència a les dades del mapa del joc, i retorna totes les dades a una variable map.
     */
    public Map loadTemes(String filename)  {
        Map map = new Map();
        map.setFilename(filename);

        try {
            JsonReader reader;
            reader = new JsonReader(new FileReader(filename));

            reader.beginObject();
            reader.nextName();
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                Room aux = new Room();
                //name
                reader.nextName();
                aux.setName(reader.nextString());
                //isStartRoom
                reader.nextName();
                aux.setStartRoom(reader.nextBoolean());
                //isCompClass
                reader.nextName();
                aux.setCompClass(reader.nextBoolean());
                //isLog
                reader.nextName();
                aux.setLog(reader.nextBoolean());
                //CanLog
                reader.nextName();
                aux.setCanLog(reader.nextBoolean());
                //ventRoom
                reader.nextName();
                aux.setVentRoom(reader.nextString());
                //color
                reader.nextName();
                aux.setColor(reader.nextInt());
                //rooms
                reader.nextName();
                reader.beginArray();
                map.getRooms().get(reader.nextInt()).set(reader.nextInt(), aux);
                reader.endArray();

                reader.endObject();
            }
            reader.endArray();
            reader.endObject();
            reader.close();
        } catch (Exception e) {
            return null;
        }
        return map;
    }
}
