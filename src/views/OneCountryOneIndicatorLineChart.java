package views;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import model.*;
 
/**
 * Created by controllers on 28/11/2016.
 */
public class OneCountryOneIndicatorLineChart {
	
	private Map <String, TreeMap<String, Indicator>> map;
	private Set <String> codesList; //Just one Code in this case, because one country
	private String countryCode;
	private String firstYear;
	private int noOfYears;
	private String finalYear;
	private String indicator;
 
	public OneCountryOneIndicatorLineChart(HashMap<String, TreeMap<String, Indicator>> hashMapIn) {
        Stage stageMain = new Stage();

        map = hashMapIn;
		codesList = map.keySet();
		countryCode = (String) codesList.toArray()[0];
        firstYear = (String) map.get(countryCode).keySet().toArray()[0]; //1st year
        noOfYears = map.values().toArray()[0].toString().split("}").length;
        finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1); //last year
		indicator = map.get(countryCode).get(firstYear).getName();
    	stageMain.setTitle(countryCode + "'s " + indicator + " from " + firstYear + " until " + finalYear);
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis(); //to make categories (ie months in this case)
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel(indicator);
        //creating the chart
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        lineChart.setTitle(indicator);
        //defining a series
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName(countryCode);  
        //populating the series with data
        for(int i= 0; i < noOfYears; ++i) {
        	String year = (String) map.get(countryCode).keySet().toArray()[i];
        	series.getData().add(new XYChart.Data(year, map.get(countryCode).get(year).getValue()));
        } 
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        stageMain.setScene(scene);
        scene.getStylesheets().add("resources/css/styling2.css");
        stageMain.show();
	}
}
