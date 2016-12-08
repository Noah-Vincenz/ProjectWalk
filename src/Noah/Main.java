package Noah;

import com.sun.org.apache.bcel.internal.generic.IndexedInstruction;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Indicator;
import model.Networking;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Noah on 28/11/2016.
 */
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
    public void start(Stage primaryStage) throws Exception {
    	
		String[] listOfCodes = {"USA", "GB", "DE"};
		String inflation = "FP.CPI.TOTL.ZG"; //consumer prices
		String gdpInUS = "NY.GDP.MKTP.CD";
		String exportsOfGNS = "NE.EXP.GNFS.ZS"; //% of GDP
		//1 country, 1 indicator and how it has changed over time
		//MyLineChart lc = new MyLineChart (primaryStage, Networking.getInstance().getRangeOfIndicatorsForCountries(listOfCodes, inflation, "1980", "2015"));
		//1 country, 1 indicator and how it has changed over time
		MyAreaChart ac = new MyAreaChart (primaryStage, Networking.getInstance().getRangeOfIndicatorsForCountries(listOfCodes, inflation, "1980", "2015"));
		//multiple countries, 1 indicator, multiple years
		//MyBarChart bc = new MyBarChart (primaryStage, Networking.getInstance().getRangeOfIndicatorsForCountries(listOfCodes, inflation, "2000", "2015"));
    }
}
