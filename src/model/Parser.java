package model;

import model.Indicator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by alexclp on 28/11/2016.
 */
public class Parser {

    public static ArrayList<Indicator> parseJSON(String json) {
        ArrayList<Indicator> toReturn = new ArrayList<Indicator>();

        try {
            JSONArray jsonArray = new JSONArray(json);
            jsonArray = jsonArray.getJSONArray(1);

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject current = jsonArray.getJSONObject(i);
            }

        } catch(Exception e) {
            System.out.println();
        }

        return toReturn;
    }
}
