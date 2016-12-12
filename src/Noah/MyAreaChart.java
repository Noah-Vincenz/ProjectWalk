package Noah;

/**
 * Created by Noah on 06/12/2016.
 */

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import model.Indicator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;

public class MyAreaChart {

    private Map<String, TreeMap<String, Indicator>> map;
    private Set<String> codesList;
    private String countryCode;
    private String firstYear;
    private int noOfYears;
    private String finalYear;
    private int noOfCountries;
    private String indicator;
    private XYChart.Series series;

    /**
     * Constructor for a MyAreaChart
     *
     * @param stageMain stage
     * @param hashMapIn hashMap to be passed in as parameter, containing the country codes as keys and
     *                  TreeMaps of <String, Indicator> as Values
     */
    public MyAreaChart (Stage stageMain, HashMap<String, TreeMap<String, Indicator>> hashMapIn) {
        map = hashMapIn;
        codesList = map.keySet();
        countryCode = (String) codesList.toArray()[0];
        firstYear = (String) map.get(countryCode).keySet().toArray()[0]; //1st year
        noOfYears = map.values().toArray()[0].toString().split("}").length;
        finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1); //last year
        noOfCountries = codesList.size();
        indicator = map.get(countryCode).get(firstYear).getName();
        stageMain.setTitle(countryCode + "'s " + indicator + " from " + firstYear + " until " + finalYear);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel(indicator);
        final AreaChart<String,Number> areaChart = new AreaChart<String,Number>(xAxis,yAxis);
        stageMain.setTitle(countryCode + "'s " + indicator + " from " + firstYear + " until " + finalYear);
        areaChart.setTitle("Area chart");
        for (int i = 0; i < noOfCountries; ++i) {
            XYChart.Series series = new XYChart.Series<String, Number>();
            String countryCode = (String) codesList.toArray()[i];
            series.setName(countryCode);
            for(int j = 0; j < noOfYears; ++j) {
                String year = Integer.toString(Integer.parseInt(firstYear)+j);
                series.getData().add(new XYChart.Data(year, map.get(countryCode).get(year).getValue()));
            }
            areaChart.getData().add(series);
        }
        Scene scene = new Scene(areaChart,800,600);
        stageMain.setScene(scene);
        scene.getStylesheets().add("styling.css");
        stageMain.show();
    }
}