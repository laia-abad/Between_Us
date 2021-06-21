package Persistence;

import Business.Entities.Match;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * * Classe on guardem totes les dades de la partida a un fitxer json.
 * @author Silvia Miralles Calvet
 * @author Laia Abad Muñoz
 * @author Francisco Bellver Asperilla
 * @author Matias Balzamo
 * @author Ferran Tió López
 */
public class JSONmatch {

    public static void guardarPartida(Match match) throws IOException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder json = new StringBuilder();

        BufferedReader resource = null;
        try {
            resource = new BufferedReader(new FileReader(match.getFilename()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String linea = "";
        while ( (linea = resource.readLine()) != null  ){
            json.append(linea);

            JsonWriter writer;

            try {
                //lleguim el json
                writer = new JsonWriter(new FileWriter(match.getFilename()));

                writer.beginObject();
                //escrius competition
                /*writer.name("competition");
                writer.beginObject();
                writer.name("name");
                //escribim el nom del rapero
                writer.value(competicio.getNom());
                writer.name("startDate");
                writer.value(parser.format(competicio.getDataInicial()));
                writer.name("endDate");
                writer.value(parser.format(competicio.getDataFinal()));
                writer.name("phases");
                writer.beginArray();
                for (int i = 0; i < competicio.getFases().size(); i++) {
                    writer.beginObject();
                    writer.name("budget");
                    writer.value(competicio.getFases().get(i).getPressupost());
                    writer.name("country");
                    writer.value(competicio.getFases().get(i).getPais());
                    writer.endObject();
                }
                writer.endArray();
                writer.endObject();
                writer.name("countries");
                writer.beginArray();
                for (int i = 0; i < competicio.getPaisosAcceptats().size(); i++) {
                    writer.value(competicio.getPaisosAcceptats().get(i).getNomAngles());
                }
                writer.endArray();
                writer.name("rappers");
                writer.beginArray();
                for (int i = 0; i < competicio.getRaperos().size(); i++) {
                    writer.beginObject();
                    writer.name("realName");
                    writer.value(competicio.getRaperos().get(i).getNomComplert());
                    writer.name("stageName");
                    writer.value(competicio.getRaperos().get(i).getNomArtistic());
                    writer.name("birth");
                    writer.value(parser.format(competicio.getRaperos().get(i).getDataNaixement()));
                    writer.name("nationality");
                    writer.value(competicio.getRaperos().get(i).getPaisOrigen());
                    writer.name("level");
                    writer.value(competicio.getRaperos().get(i).getNivell());
                    writer.name("photo");
                    writer.value(competicio.getRaperos().get(i).getUrlImatge());
                    writer.endObject();
                }
                writer.endArray();
                writer.endObject();*/
                writer.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        resource.close();

    }
}
