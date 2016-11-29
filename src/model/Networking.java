package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alexclp on 28/11/2016.
 */
public class Networking {

    public static ArrayList<Country> getListOfCountries() {
        ArrayList<Country> countries = new ArrayList<Country>();

        String urlString = "http://api.worldbank.org/countries?format=json&per_page=10000";
        StringBuilder stringBuilder = new StringBuilder();

        String json = getJSONForURL(urlString);
        try {
            JSONArray jsonArray = new JSONArray(json);
            jsonArray = jsonArray.getJSONArray(1);

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject current = jsonArray.getJSONObject(i);
                String code = current.getString("iso2Code");
                String name = current.getString("name");
                countries.add(new Country(name, code));
            }

            return countries;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<Country, ArrayList<DataSet>> getIndicatorForCountries(String indicatorCode) {
        HashMap<Country, ArrayList<DataSet>> toReturn = new HashMap<Country, ArrayList<DataSet>>();
//        ArrayList<Country> countries = getListOfCountries();
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(new Country("Italy", "IT"));
        countries.add(new Country("Romania", "RO"));
        countries.add(new Country("Germany", "DE"));
        for (int i = 0; i < countries.size(); ++i) {
            Country currentCountry = countries.get(i);

            String urlString = "http://api.worldbank.org/countries/" + currentCountry.getCode() + "/indicators/" + indicatorCode + "?format=json&per_page=10000";
            String json = getJSONForURL(urlString);

            try {
                JSONArray jsonArray = new JSONArray(json);
                jsonArray = jsonArray.getJSONArray(1);
                ArrayList<DataSet> dataSets = new ArrayList<DataSet>();

                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject currentJsonObject = jsonArray.getJSONObject(i);
                    String name = currentJsonObject.getJSONObject("indicator").getString("value");
                    String value = currentJsonObject.getString("value");
                    String year = currentJsonObject.getString("date");
                    dataSets.add(new DataSet(year, new Indicator(name, indicatorCode, value)));
                }

                toReturn.put(currentCountry, dataSets);
            } catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return toReturn;
    }

    private static String getJSONForURL(String urlString) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        System.out.println(getIndicatorForCountries("NY.GDP.MKTP.CD"));
    }
}
