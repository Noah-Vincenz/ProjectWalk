package Noah;

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
import model.Indicator;

/**
 * Created by Noah on 28/11/2016.
 */
public class MyLineChart {
	
	private Map <String, TreeMap<String, Indicator>> map;
	private Set <String> codesList; //Just one Code in this case, because one country
	private String countryCode;
	private String firstYear;
	private int noOfYears;
	private String finalYear;
    private int noOfCountries;
	private String indicator;

	public MyLineChart(Stage stageMain, HashMap<String, TreeMap<String, Indicator>> hashMapIn) {
		map = hashMapIn;
		codesList = map.keySet();
		countryCode = (String) codesList.toArray()[0];
        firstYear = (String) map.get(countryCode).keySet().toArray()[0]; //1st year
        noOfYears = map.values().toArray()[0].toString().split("}").length;
        finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1); //last year
        noOfCountries = codesList.size();
        indicator = map.get(countryCode).get(firstYear).getName();
    	stageMain.setTitle(countryCode + "'s " + indicator + " from " + firstYear + " until " + finalYear);
        final CategoryAxis xAxis = new CategoryAxis(); //to make categories (ie months in this case)
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        lineChart.setTitle("Line Chart");
        xAxis.setLabel("Year");
        yAxis.setLabel(indicator);
        for (int i = 0; i < noOfCountries; ++i) { //-----
            XYChart.Series series = new XYChart.Series<String, Number>();
            series.setName((String) codesList.toArray()[i]);
            for(int j = 0; j < noOfYears; ++j) {
                String countryCode = (String) codesList.toArray()[i];
                String year = Integer.toString(Integer.parseInt(firstYear)+j);
                series.getData().add(new XYChart.Data(year, map.get(countryCode).get(year).getValue()));
            }
            lineChart.getData().addAll(series);
        }
        Scene scene  = new Scene(lineChart,800,600);
        stageMain.setScene(scene);
        scene.getStylesheets().add("styling.css");
        stageMain.show();
	}
}
