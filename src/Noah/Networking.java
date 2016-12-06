package Noah;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Alex on 28/11/2016.
 */
public class Networking {

    /**
     * Singleton init
     */

    private static Networking instance = null;
    private Networking() {
        // Exists only to defeat instantiation.
    }

    public static Networking getInstance() {
        if(instance == null) {
            instance = new Networking();
        }
        return instance;
    }

    /**
     * Method to get a list of all the countries in the world
     *
     * @return ArrayList of object Country
     */

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

    /**
     * Method to get the latest value for a certain indicator for different countries
     *
     * @param countryCodes  - array of strings containing the country codes (i.e. DE)
     * @param indicatorCode - code string of the indicator
     * @return a map that has the country code as the key and as the object an instance of Indicator
     */

    public static HashMap<String, Indicator> getLastIndicatorForCountries(String[] countryCodes, String indicatorCode) {
        HashMap<String, Indicator> toReturn = new HashMap<String, Indicator>();

        for (int i = 0; i < countryCodes.length; ++i) {
            String currentCountry = countryCodes[i];

            String urlString = "http://api.worldbank.org/countries/" + currentCountry + "/indicators/" + indicatorCode + "?format=json&per_page=10000";
            String json = getJSONForURL(urlString);
            System.out.println("URL: " + urlString);

            try {
                JSONArray jsonArray = new JSONArray(json);
                jsonArray = jsonArray.getJSONArray(1); // getting rid of the header here (has info about how many pages of data)

                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject current = jsonArray.getJSONObject(j);
                    JSONObject indicatorObject = current.getJSONObject("indicator");
                    if (!current.getString("value").equals("null")) {
                        String name = indicatorObject.getString("value");
                        String stringValue = current.getString("value");

                        double value;

                        if (stringValue.equals("null")) {
                            value = 0;
                        } else {
                            value = Double.parseDouble(stringValue);

                        }
                        toReturn.put(currentCountry, new Indicator(name, indicatorCode, value));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return toReturn;
    }

    /**
     * Method to get the value of an indicator for some countries given a range of years
     *
     * @param countryCodes  - array of String with the country codes
     * @param indicatorCode - the code of the indicator as a String
     * @param beginYear     - String of the beginning year
     * @param endYear       - String of the end year
     * @return - A HashMap that has the keys as country codes and the object is a TreeMap that has the key an year and the object an indicator
     * For example, to get Germany's GDP for 2015 you'll have to write map.get("DE").get("2015")
     */

    public static HashMap<String, TreeMap<String, Indicator>> getRangeOfIndicatorsForCountries(String[] countryCodes, String indicatorCode, String beginYear, String endYear) {
        HashMap<String, TreeMap<String, Indicator>> toReturn = new HashMap<String, TreeMap<String, Indicator>>();

        for (int i = 0; i < countryCodes.length; ++i) {
            String currentCountry = countryCodes[i];
            System.out.println("Countries:" + currentCountry);
            String urlString = "http://api.worldbank.org/countries/" + currentCountry + "/indicators/" + indicatorCode + "?format=json&per_page=1000&date=" + beginYear + ":" + endYear;
            String json = getJSONForURL(urlString);
            System.out.println("URL: " + urlString);

            try {
                JSONArray jsonArray = new JSONArray(json);
                jsonArray = jsonArray.getJSONArray(1); // getting rid of the header here (has info about how many pages of data)

                TreeMap<String, Indicator> yearsMap = new TreeMap<String, Indicator>();

                for (int j = 0; j < jsonArray.length(); ++j) {

                    JSONObject current = jsonArray.getJSONObject(j);

                    String date = current.getString("date");

                    JSONObject indicatorObject = current.getJSONObject("indicator");
                    String name = indicatorObject.getString("value");
                    String stringValue = current.getString("value");

                    double value;

                    if (stringValue.equals("null")) {
                        value = 0;
                    } else {
                        value = Double.parseDouble(stringValue);

                    }
                    yearsMap.put(date, new Indicator(name, indicatorCode, value));
                }

                toReturn.put(currentCountry, yearsMap);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return toReturn;
    }

    /**
     * Method that will return a JSON string from a given URL String
     *
     * @param urlString - the URL String
     * @return a String containing the JSON from the URL
     */

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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        String gdp = "NY.GDP.MKTP.CD";
        String[] countries = {"MLT"};
        System.out.println(getLastIndicatorForCountries(countries, gdp));
        System.out.println(getRangeOfIndicatorsForCountries(countries, gdp, "1990", "2015"));
    }
}
