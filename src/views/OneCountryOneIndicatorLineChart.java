package views;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;

/**
 * Created by controllers on 28/11/2016.
 */
public class OneCountryOneIndicatorLineChart {
    private Stage stageMain;

    public OneCountryOneIndicatorLineChart(LineChart<String,Number> lineChart) {
        stageMain = new Stage();
        Scene scene = new Scene(lineChart, 800, 600);
        stageMain.setScene(scene);
        scene.getStylesheets().add("resources/css/styling2.css");
        stageMain.show();
    }

}
