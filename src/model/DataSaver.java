package model;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Alex on 06/12/2016.
 */
public class DataSaver {

    /**
     * Singleton init
     */

    private static DataSaver instance = null;
    private DataSaver() {
        // Exists only to defeat instantiation.
    }

    public static DataSaver getInstance() {
        if(instance == null) {
            instance = new DataSaver();
        }
        return instance;
    }

    public static void saveJSON(String jsonString, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(jsonString);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception when writing to file");
        }
    }

    public static JSONArray getJSONFromFile(String fileName) throws IOException, JSONException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }

            return new JSONArray(sb.toString());
        } finally {
            br.close();
        }
    }
}
