package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;

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
}
