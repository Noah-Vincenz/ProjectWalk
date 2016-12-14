package views.charts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Indicator;

/**
 * Created by Noah on 28/11/2016.
 */
public class MyBarChart {

	private static MyBarChart instance = null;

	private MyBarChart() {
		// Exists only to defeat instantiation.
	}

	public static MyBarChart getInstance() {
		if (instance == null) {
			instance = new MyBarChart();
		}
		return instance;
	}


	public BarChart<String, Number> getBarChart(HashMap<String, TreeMap<String, Indicator>> hashMapIn) {

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
		finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1);
		noOfCountries = codesList.size();
		indicator = map.get(countryCode).get(firstYear).getName();
//    	stageMain.setTitle(indicator + " from " + firstYear + " until " + finalYear);
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
		barChart.setTitle(indicator);
		xAxis.setLabel("Country");
		yAxis.setLabel(indicator);
		for(int i= 0; i < noOfYears; ++i) {
			XYChart.Series series = new XYChart.Series();
			series.setName(Integer.toString(Integer.parseInt(firstYear)+i));
			for (int j = 0; j < noOfCountries; ++j) {
				countryCode = (String) codesList.toArray()[j];
				String year = Integer.toString(Integer.parseInt(firstYear)+i);
				if (map.get(countryCode).get(year) != null) {
					series.getData().add(new XYChart.Data(countryCode, map.get(countryCode).get(year).getValue()));
				}

			}
			barChart.getData().add(series);
		}

		return barChart;
	}
}
