package views;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;

/**
 * Created by Noah on 28/11/2016.
 */
public class OneCountryOneIndicatorLineChart {
    private Stage stageMain;

    /**
     * The OneCountryOneIndicatorLineChart takes a LineChart in the parameter and
     * creates a scene with that chart, which then gets set to a new stage.
     * It then adds the design from the styling.css file to that scene to make it
     * look more appealing and lastly the stage is shown.
     * @param lineChart
     */
    public OneCountryOneIndicatorLineChart(LineChart<String,Number> lineChart) {
        stageMain = new Stage();
        Scene scene = new Scene(lineChart, 800, 600);
        stageMain.setScene(scene);
        scene.getStylesheets().add("resources/css/styling2.css");
        stageMain.show();
    }

}