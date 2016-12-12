package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

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
        if (instance == null) {
            instance = new Networking();
        }
        return instance;
    }

    /**
     * Method to get a list of all the countries in the world
     *
     * @return ArrayList of object Country
     */

    public static ArrayList<Country> getListOfCountries() throws JSONException, IOException {
        ArrayList<Country> countries = new ArrayList<Country>();

        String urlString = "http://api.worldbank.org/countries?format=json&per_page=10000";
        StringBuilder stringBuilder = new StringBuilder();

        JSONArray jsonArray = getJSONForURL(urlString);

        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject current = jsonArray.getJSONObject(i);
            String code = current.getString("iso2Code");
            String name = current.getString("name");
            countries.add(new Country(name, code));
        }

        return countries;
    }

    /**
     * Method to get the latest value for a certain indicator for different countries
     *
     * @param countryCodes  - array of strings containing the country codes (i.e. DE)
     * @param indicatorCode - code string of the indicator
     * @return a map that has the country code as the key and as the object an instance of Indicator
     */

    public static HashMap<String, Indicator> getLastIndicatorForCountries(ArrayList<String> countryCodes, String indicatorCode) throws IOException, JSONException {

        HashMap<String, Indicator> toReturn = new HashMap<String, Indicator>();

        for (int i = 0; i < countryCodes.size(); ++i) {
            String currentCountry = countryCodes.get(i);

            String urlString = "http://api.worldbank.org/countries/" + currentCountry + "/indicators/" + indicatorCode + "?format=json&per_page=10000";

            System.out.println("URL: " + urlString);

            JSONArray jsonArray = getJSONForURL(urlString);

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

    public static HashMap<String, TreeMap<String, Indicator>> getRangeOfIndicatorsForCountries(ArrayList<String> countryCodes, String indicatorCode, String beginYear, String endYear) throws JSONException, IOException {

        HashMap<String, TreeMap<String, Indicator>> toReturn = new HashMap<String, TreeMap<String, Indicator>>();

        for (int i = 0; i < countryCodes.size(); ++i) {
            String currentCountry = countryCodes.get(i);

            String urlString = "http://api.worldbank.org/countries/" + currentCountry + "/indicators/" + indicatorCode + "?format=json&per_page=1000&date=" + beginYear + ":" + endYear;
            System.out.println("URL: " + urlString);

            JSONArray jsonArray = getJSONForURL(urlString);

            System.out.println(jsonArray);

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

        }

        return toReturn;
    }

    /**
     * Method that will return a JSON string from a given URL String
     *
     * @param urlString - the URL String
     * @return a String containing the JSON from the URL
     */

    private static JSONArray getJSONForURL(String urlString) throws IOException, JSONException {
        if (DataSaver.getInstance().checkForFile(urlString)) {
            return DataSaver.getInstance().getJSONFromFile(urlString);
        } else {
            StringBuilder stringBuilder = new StringBuilder();

            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            JSONArray toReturn;
            if (!urlString.contains("worldbank")) {
                toReturn = new JSONArray(stringBuilder.toString());
            } else {
                toReturn = new JSONArray(stringBuilder.toString()).getJSONArray(1);
            }
            DataSaver.getInstance().saveJSON(toReturn.toString(), urlString);
            return toReturn;
        }
    }

    public static void main(String args[]) {
        System.out.println(Networking.getInstance().getBillionairesRange(30));
    }

    public ArrayList<Billionaire> getBillionairesRange(int range) {
        String urlString = "http://www.forbes.com/ajax/list/data?year=2015&uri=billionaires&type=person";

        try {
            JSONArray jsonArray = getJSONForURL(urlString);

            // Check if the data was get from the local storage

            ArrayList<Billionaire> billionaires  = new ArrayList<Billionaire>();

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject current = jsonArray.getJSONObject(i);
                if (!current.isNull("name") && !current.isNull("source") && !current.isNull("industry") && !current.isNull("realTimeWorth")) {
                    Billionaire currentBillionaire = new Billionaire(current.getString("name"),
                            current.getString("source"),
                            current.getString("industry"),
                            current.getDouble("realTimeWorth"));
                    billionaires.add(currentBillionaire);
                }
            }

            Collections.sort(billionaires, new Comparator<Billionaire>() {
                @Override
                public int compare(Billionaire o1, Billionaire o2) {
                    if (o1.getWorthValue() < o2.getWorthValue()) {
                        return 1;
                    } else if (o1.getWorthValue() == o2.getWorthValue()) {
                        return 0;
                    }
                    return -1;
                }
            });

            ArrayList<Billionaire> toReturn = new ArrayList<Billionaire>();
            for (int i = 0; i < range; ++i) {
                toReturn.add(billionaires.get(i));
            }

            return toReturn;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
