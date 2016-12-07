package root;

import Noah.Networking;
import Noah.OneCountryOneIndicatorLineChart;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;

public class GraphScreenController extends Application {

    private CheckBox United_Kingdom;
    private CheckBox United_States;
    private CheckBox Romania;
    private CheckBox Turkey;
    private CheckBox Switzerland;
    private CheckBox Canada;
    private CheckBox Germany;
    private CheckBox China;
    private CheckBox Japan;
    private CheckBox Russia;
    private ComboBox cbIndicators;
    private Stage current;

    @Override
    public void start(Stage primaryStage) {
        current = primaryStage;
        BorderPane bpMain = new BorderPane();
        GridPane gpTop = new GridPane();
        GridPane gpAll = new GridPane();
        GridPane gpMid = new GridPane();
        GridPane gpBot = new GridPane();

        Text txtCountries = new Text("Select Countries Below:");
        txtCountries.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        United_Kingdom = new CheckBox("United Kingdom");
        United_States = new CheckBox("United States");
        Romania = new CheckBox("Romania");
        Turkey = new CheckBox("Turkey");
        Switzerland = new CheckBox("Switzerland");
        Canada = new CheckBox("Canada");
        Germany = new CheckBox("Germany");
        China = new CheckBox("China");
        Japan = new CheckBox("Japan");
        Russia = new CheckBox("Russia");

        gpMid.add(txtCountries, 0, 0);
        gpMid.add(United_Kingdom, 0, 1);
        gpMid.add(Romania, 1, 1);
        gpMid.add(United_States, 0, 2);
        gpMid.add(Turkey, 1, 2);
        gpMid.add(Switzerland, 0, 3);
        gpMid.add(Canada, 1, 3);
        gpMid.add(Germany, 0, 4);
        gpMid.add(China, 1, 4);
        gpMid.add(Japan, 0, 5);
        gpMid.add(Russia, 1, 5);

        gpMid.setHgap(5);
        gpMid.setVgap(10);
        gpMid.setPadding(new Insets(10,10,10,10));

        cbIndicators = new ComboBox();
        cbIndicators.getItems().addAll("GDP","GDP Per Capita", "Unemployment",
                "Consumer Price Index(Inflation)", "Imports of Goods", "Exports of Goods",
                "Real Interest Rates", "Tax Rates");
        cbIndicators.setVisibleRowCount(4);
        gpBot.add(cbIndicators, 0, 0);
        gpBot.setHgap(10);
        gpBot.setVgap(10);
        gpBot.setPadding(new Insets(10,10,10,10));

        gpAll.setHgap(10);
        gpAll.setVgap(10);
        gpAll.setPadding(new Insets(10,10,10,10));

        gpAll.add(gpMid, 0, 0);
        Text txtIndicaters = new Text("Select An Indicator:");
        txtIndicaters.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        gpAll.add(txtIndicaters, 0, 1);
        gpAll.add(gpBot, 0, 2);

        Button btCreateGraph = new Button("Create Graph");
        btCreateGraph.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ArrayList<String> countries = new ArrayList<String>();
                if(United_Kingdom.isSelected()) {
                    countries.add("GB");
                }
                if(Romania.isSelected()) {
                    countries.add("RO");
                }
                if(United_States.isSelected()) {
                    countries.add("USA");
                }
                if(Turkey.isSelected()) {
                    countries.add("TUR");
                }
                if(Switzerland.isSelected()) {
                    countries.add("CHE");
                }
                if(Canada.isSelected()) {
                    countries.add("CAN");
                }
                if(Germany.isSelected()) {
                    countries.add("DE");
                }
                if(China.isSelected()) {
                    countries.add("CHN");
                }
                if(Japan.isSelected()) {
                    countries.add("JPN");
                }
                if(Russia.isSelected()) {
                    countries.add("RUS");
                }
                if(cbIndicators.getSelectionModel().getSelectedItem() != null) {
                    String graphSelected = cbIndicators.getSelectionModel().getSelectedItem().toString();
                    OneCountryOneIndicatorLineChart lc = new OneCountryOneIndicatorLineChart (current, Networking.getInstance().getRangeOfIndicatorsForCountries(countries, "NY.GDP.MKTP.CD", "1980", "2015"));
                }


            }
        });
        gpAll.add(btCreateGraph, 0, 3);

        gpTop.setHgap(10);
        gpTop.setVgap(10);
        gpTop.setPadding(new Insets(10,10,10,10));

        Button btHome = new Button("Home");
        btHome.setMinSize(80,35);
        gpTop.add(btHome, 0, 0);

        Rectangle graphSpace = new Rectangle();
        graphSpace.setX(50);
        graphSpace.setY(50);
        graphSpace.setWidth(700);
        graphSpace.setHeight(500);
        graphSpace.setArcWidth(20);
        graphSpace.setArcHeight(20);

        bpMain.setTop(gpTop);
        bpMain.setLeft(gpAll);
        bpMain.setCenter(graphSpace);

        bpMain.setPrefSize(1050, 600);
        Scene scene = new Scene(bpMain);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}