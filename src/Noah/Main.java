package Noah;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Noah on 28/11/2016.
 */
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
    public void start(Stage primaryStage) throws Exception {
    	
		Networking nw = new Networking();
		String[] listOfCodes = {"DE", "USA", "RUS", "CHN", "CHE"};
		String inflation = "FP.CPI.TOTL.ZG"; //consumer prices // ------does not work
		String gdpInUS = "NY.GDP.MKTP.CD";
		String exportsOfGNS = "NE.EXP.GNFS.ZS"; //% of GDP
		//1 country, 1 indicator and how it has changed over time
		//OneCountryOneIndicatorLineChart lc = new OneCountryOneIndicatorLineChart (primaryStage, nw.getRangeOfIndicatorsForCountries(listOfCodes, exportsOfGNS, "1980", "2015")); 
		//2 multiple countries, 1 indicator, multiple years(max 3) // -------is it better to change into 3 years as parameter so not 1 year apart?
		MultCountryMultYearOneIndBarChart bc = new MultCountryMultYearOneIndBarChart (primaryStage, nw.getRangeOfIndicatorsForCountries(listOfCodes, gdpInUS, "2013", "2015"));              
    }
}
