package views.charts;

/**
 * Created by Noah on 06/12/2016.
 */

import javafx.scene.chart.CategoryAxis;
import model.Indicator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;

public class MyAreaChart {

    private static MyAreaChart instance = null;

    private MyAreaChart() {
        // Exists only to defeat instantiation.
    }

    public static MyAreaChart getInstance() {
        if (instance == null) {
            instance = new MyAreaChart();
        }
        return instance;
    }

    public AreaChart<String,Number> getAreaChart(HashMap<String, TreeMap<String, Indicator>> hashMapIn) {
        Map<String, TreeMap<String, Indicator>> map;
        Set<String> codesList;
        String countryCode;
        String firstYear;
        int noOfYears;
        String finalYear;
        int noOfCountries;
        String indicator;
        XYChart.Series series;

        map = hashMapIn;
        codesList = map.keySet();
        countryCode = (String) codesList.toArray()[0];
        firstYear = (String) map.get(countryCode).keySet().toArray()[0]; //1st year
        noOfYears = map.values().toArray()[0].toString().split("}").length;
        finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1); //last year
        noOfCountries = codesList.size();
        indicator = map.get(countryCode).get(firstYear).getName();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel(indicator);
        final AreaChart<String,Number> areaChart = new AreaChart<String,Number>(xAxis,yAxis);
        areaChart.setTitle("Area chart");
        for (int i = 0; i < noOfCountries; ++i) {
            series = new XYChart.Series<String, Number>();
            countryCode = (String) codesList.toArray()[i];
            series.setName(countryCode);
            for(int j = 0; j < noOfYears; ++j) {
                String year = Integer.toString(Integer.parseInt(firstYear)+j);
                series.getData().add(new XYChart.Data(year, map.get(countryCode).get(year).getValue()));
            }
            areaChart.getData().add(series);
        }

        return areaChart;
    }

//    public static void main(String args[]) {
//        String[] listOfCodes = {"USA", "GB", "DE"};
//        String inflation = "FP.CPI.TOTL.ZG"; //consumer prices
//        String gdpInUS = "NY.GDP.MKTP.CD";
//        String exportsOfGNS = "NE.EXP.GNFS.ZS"; //% of GDP
//        //1 country, 1 indicator and how it has changed over time
//        //MyLineChart lc = new MyLineChart (primaryStage, Networking.getInstance().getRangeOfIndicatorsForCountries(listOfCodes, inflation, "1980", "2015"));
//        //1 country, 1 indicator and how it has changed over time
//        MyAreaChart ac = new MyAreaChart (primaryStage, Networking.getInstance().getRangeOfIndicatorsForCountries(listOfCodes, inflation, "1980", "2015"));
//        //multiple countries, 1 indicator, multiple years
//        //MyBarChart bc = new MyBarChart (primaryStage, Networking.getInstance().getRangeOfIndicatorsForCountries(listOfCodes, inflation, "2000", "2015"));
//    }
}