package Noah;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.Indicator;

/**
 * Created by Noah on 28/11/2016.
 */	 
public class MyBarChart {
	
	private Map <String, TreeMap<String, Indicator>> map;
	private Set <String> codesList;
	private String countryCode;
	private String firstYear;
	private int noOfYears;
	private String finalYear;
	private int noOfCountries;
	private String indicator;

	/**
	 * Constructor for a MyBarChart
	 *
	 * @param stageMain stage
	 * @param hashMapIn hashMap to be passed in as parameter, containing the country codes as keys and
	 *                  TreeMaps of <String, Indicator> as Values
	 */
	public MyBarChart(Stage stageMain, HashMap<String, TreeMap<String, Indicator>> hashMapIn) {
		map = hashMapIn;
		codesList = map.keySet();
		countryCode = (String) codesList.toArray()[0];
        firstYear = (String) map.get(countryCode).keySet().toArray()[0]; //1st year
        noOfYears = map.values().toArray()[0].toString().split("}").length;
        finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1);
        noOfCountries = codesList.size();
		indicator = map.get(countryCode).get(firstYear).getName();
    	stageMain.setTitle(indicator + " from " + firstYear + " until " + finalYear);
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
        barChart.setTitle("Country Summary");
        xAxis.setLabel("Country");       
        yAxis.setLabel(indicator);
    	for(int i= 0; i < noOfYears; ++i) {
	        XYChart.Series series = new XYChart.Series();
	       	series.setName(Integer.toString(Integer.parseInt(firstYear)+i));
        	for (int j = 0; j < noOfCountries; ++j) {
        		String countryCode = (String) codesList.toArray()[j];
        		String year = Integer.toString(Integer.parseInt(firstYear)+i);
	        	series.getData().add(new XYChart.Data(countryCode, map.get(countryCode).get(year).getValue()));   
	        }
	        barChart.getData().add(series);
    	} 
        Scene scene  = new Scene(barChart,800,600);
        stageMain.setScene(scene);
        scene.getStylesheets().add("styling.css");
        stageMain.show();
	}	 
}
