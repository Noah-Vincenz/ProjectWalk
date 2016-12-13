package views;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.stage.Stage;
/**
 * Created by Noah on 28/11/2016.
 */	 
public class MultCountryMultYearOneIndBarChart {
	private Stage stageMain;
	
	public MultCountryMultYearOneIndBarChart(BarChart<String,Number> bc) {
		stageMain = new Stage();
        Scene scene  = new Scene(bc,800,600);
        stageMain.setScene(scene);
        scene.getStylesheets().add("resources/css/styling2.css");
        stageMain.show();
	}	 
}
