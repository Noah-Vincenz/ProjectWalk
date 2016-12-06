package Noah;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
 
/**
 * Created by Noah on 28/11/2016.
 */
public class OneCountryOneIndicatorLineChart {
	
	private Map <String, TreeMap<String, Indicator>> map;
	private Set <String> codesList; //Just one Code in this case, because one country
	private String countryCode;
	private String firstYear;
	private int noOfYears;
	private String finalYear;
	private String indicator;
 
	public OneCountryOneIndicatorLineChart(Stage stageMain, HashMap<String, TreeMap<String, Indicator>> hashMapIn) {
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
        lineChart.setTitle("Line Chart");
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
        scene.getStylesheets().add("styling2.css");
        stageMain.show();
	}
}
