package model;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;

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

    public boolean checkForFile(String fileName) throws IOException {
        File jsonFile = new File(fileName);
        return jsonFile.exists();
    }

    public void saveJSON(String jsonString, String fileName) {
        BufferedWriter writer = null;
        try {
            File file = new File("json" + File.separator + fileName);
            file.getParentFile().mkdirs();
            file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file, false));
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception when writing to file");
        } finally {
            try {
                writer.close();
            }
            catch (IOException ex) {
                System.err.println("An IOException was caught!");
                ex.printStackTrace();
            }
        }
    }

    public JSONArray getJSONFromFile(String fileName) throws IOException, JSONException {
        BufferedReader br = new BufferedReader(new FileReader("json" + File.separator + fileName));
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
