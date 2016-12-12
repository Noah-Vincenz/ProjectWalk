package views.charts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Indicator;

/**
 * Created by Noah on 28/11/2016.
 */
public class MyLineChart {

    private static MyLineChart instance = null;

    private MyLineChart() {
        // Exists only to defeat instantiation.
    }

    public static MyLineChart getInstance() {
        if (instance == null) {
            instance = new MyLineChart();
        }
        return instance;
    }
    public LineChart<String,Number> getLineChart(HashMap<String, TreeMap<String, Indicator>> hashMapIn) {
        Map <String, TreeMap<String, Indicator>> map;
        Set <String> codesList;
        String countryCode;
        String firstYear;
        int noOfYears;
        String finalYear;
        int noOfCountries;
        String indicator;

        map = hashMapIn;
        codesList = map.keySet();
        countryCode = (String) codesList.toArray()[0];
        firstYear = (String) map.get(countryCode).keySet().toArray()[0]; //1st year
        noOfYears = map.values().toArray()[0].toString().split("}").length;
        finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1); //last year
        noOfCountries = codesList.size();
        indicator = map.get(countryCode).get(firstYear).getName();
        final CategoryAxis xAxis = new CategoryAxis(); //to make categories (ie months in this case)
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        lineChart.setTitle("Line Chart");
        xAxis.setLabel("Year");
        yAxis.setLabel(indicator);
        for (int i = 0; i < noOfCountries; ++i) {
            XYChart.Series series = new XYChart.Series<String, Number>();
            series.setName((String) codesList.toArray()[i]);
            for(int j = 0; j < noOfYears; ++j) {
                countryCode = (String) codesList.toArray()[i];
                String year = Integer.toString(Integer.parseInt(firstYear)+j);
                series.getData().add(new XYChart.Data(year, map.get(countryCode).get(year).getValue()));
            }
            lineChart.getData().add(series);
        }

        return lineChart;
    }
}