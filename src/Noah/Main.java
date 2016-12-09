package Noah;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Networking;
import views.OneCountryOneIndicatorLineChart;

/**
 * Created by Noah on 28/11/2016.
 */
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
    public void start(Stage primaryStage) throws Exception {
    	
		String[] listOfCodes = {"MLT", "RO", "LIE"};
		String inflation = "FP.CPI.TOTL.ZG"; //consumer prices
		String gdpInUS = "NY.GDP.MKTP.CD";
		String exportsOfGNS = "NE.EXP.GNFS.ZS"; //% of GDP
		//1 country, 1 indicator and how it has changed over time
//		OneCountryOneIndicatorLineChart lc = new OneCountryOneIndicatorLineChart (primaryStage, Networking.getInstance().getRangeOfIndicatorsForCountries(listOfCodes, inflation, "1980", "2015"));
		//2 multiple countries, 1 indicator, multiple years
		//MultCountryMultYearOneIndBarChart bc = new MultCountryMultYearOneIndBarChart (primaryStage, Networking.getInstance().getRangeOfIndicatorsForCountries(listOfCodes, gdpInUS, "2000", "2015"));              
    }
}
