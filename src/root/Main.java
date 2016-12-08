package root;

import Noah.Networking;
import Noah.OneCountryOneIndicatorLineChart;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.util.ArrayList;

public class Main extends Application {
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
    @Override
    public void start(final Stage primaryStage) throws Exception{
        VBox searchWolfram = new VBox();
        Label searchTitle = new Label("Have a Question? Ask Me?");
        searchTitle.setStyle("-fx-font-size:20px; -fx-font-family:\"Helvetica\";");

        searchWolfram.getChildren().add(searchTitle);
        searchWolfram.setAlignment(Pos.CENTER);
        searchWolfram.setSpacing(20);
        TextField textSearch = new TextField();

        Button searchBtn = new Button("Search");
        searchBtn.setTextFill(Color.WHITE);
        searchBtn.setStyle("-fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #FE9486 100%),\n" +
                "        #9d4024,\n" +
                "        #d86e3a,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #FE9486); -fx-font-size:18px; -fx-font-family:\"Helvetica\";");
        searchWolfram.getChildren().add(textSearch);
        searchWolfram.getChildren().add(searchBtn);

        Button btn = new Button("Explore Visualizations'");
        btn.setTextFill(Color.WHITE);
        btn.setStyle("-fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #FE9486 100%),\n" +
                "        #9d4024,\n" +
                "        #d86e3a,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #FE9486); -fx-font-size:18px; -fx-font-family:\"Helvetica\";");
        btn.setMinWidth(200);
        Label label = new Label("The Economics of the World");
        label.setStyle("-fx-font-size:34px; -fx-font-family:\"Helvetica\";");
        label.setTextFill(Color.WHITE);
        
        SplitPane root = new SplitPane();
        BorderPane informational = new BorderPane();
        informational.setCenter(label);
        TextArea t1 = new TextArea();
        informational.setMinSize(100,50);

        VBox optionsToExplore = new VBox();
        optionsToExplore.setPadding(new Insets(50, 50, 50, 50));
        optionsToExplore.setSpacing(10);
        Label options = new Label("Select Visualisation to Explore");
        options.setStyle("-fx-font-size:20px; -fx-font-family:\"Helvetica\";");
        options.setTextFill(Color.BLACK);
       // optionsToExplore.getChildren().add(options);
        optionsToExplore.setMinSize(100,50);
        Text t = new Text();
        t.setFont(new Font(12));
        t.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.\nCurabitur vel tellus a ante ornare pellentesque. Duis venenatis\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien");
     //   optionsToExplore.getChildren().add(t);
        Text t2 = new Text();
        t2.setFont(new Font(12));
        t2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.\nCurabitur vel tellus a ante ornare pellentesque. Duis venenatis\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien\nibh tristique, mattis ipsum eu, tempor turpis. In non tellus sapien");
       // optionsToExplore.getChildren().add(t2);

        VBox graphOptions = new VBox();
        graphOptions.setPadding(new Insets(50,0,0,0));
        graphOptions.setSpacing(10);
        Label labelGDP = new Label("GDP");
        Label labelGDPPerCapita = new Label("GDP Per Capita");
        Label labelUnemployment = new Label("Unemployment");
        Label labelInflation = new Label("Consumer Price Index(Inflation)");
        Label labelImportOfGoods = new Label("Imports of Goods");
        Label labelExportOfGoods = new Label("Exports of Goods");
        Label labelInterestRates = new Label("Real Interest Rates");
        Label labelTaxRates = new Label("Tax Rates");
        cbIndicators = new ComboBox();
        cbIndicators.getItems().addAll("GDP","GDP Per Capita", "Unemployment",
                "Consumer Price Index(Inflation)", "Imports of Goods", "Exports of Goods",
                "Real Interest Rates", "Tax Rates");
        GridPane gpMid = new GridPane();

        Text txtCountries = new Text("Select Countries Below:");
        txtCountries.setFont(Font.font("Helvetica", 20));
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
        gpMid.setPadding(new Insets(10,0,10,0));

        btn.setOnAction(new EventHandler<ActionEvent>() {
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
                    String indicatorCode = "";
                    if(graphSelected == "GDP") {
                        indicatorCode = "NY.GDP.MKTP.KD.ZG";
                    } else if(graphSelected == "GDP Per Capita") {
                        indicatorCode = "NY.GDP.PCAP.KD.ZG";
                    } else if(graphSelected == "Unemployment") {
                        indicatorCode = "SL.UEM.TOTL.ZS";
                    } else if(graphSelected == "Consumer Price Index(Inflation") {
                        indicatorCode = "FP.CPI.TOTL.ZG";
                    } else if(graphSelected == "Imports of Goods") {
                        indicatorCode = "NE.IMP.GNFS.ZS";
                    } else if(graphSelected == "Exports of Goods") {
                        indicatorCode = "NE.EXP.GNFS.ZS";
                    } else if(graphSelected == "Real Interest Rates") {
                        indicatorCode = "FR.INR.RINR";
                    } else if(graphSelected == "Tax Rates") {
                        indicatorCode = "IC.TAX.TOTL.CP.ZS";
                    }
                    OneCountryOneIndicatorLineChart lc = new OneCountryOneIndicatorLineChart (Networking.getInstance().getRangeOfIndicatorsForCountries(countries, indicatorCode, "1980", "2015"));
                }


            }
        });
        graphOptions.getChildren().addAll(options,cbIndicators,gpMid);

        optionsToExplore.getChildren().addAll(searchWolfram,graphOptions);

        optionsToExplore.getChildren().add(btn);


//        root.getItems().add(new Button("Button One"));

        root.getItems().addAll(informational, optionsToExplore);

//        root.setAlignment(Pos.CENTER);

        ImagePattern pattern = new ImagePattern(new Image("images/pexels-photo-25349-2.jpg"), 0, 0, 1, 1, true);
        informational.setStyle("-fx-background-image: url(\"https://upload.wikimedia.org/wikipedia/commons/6/6e/London_Thames_Sunset_panorama_-_Feb_2008.jpg\"); -fx-background-size:cover;");
        optionsToExplore.setStyle("-fx-background-color:#FFFFFF;");


        Scene scene = new Scene(root, 930, 750);
        scene.setFill(pattern);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menuOware = new Menu("Oware");
        MenuItem exitMainMenu = new MenuItem("Exit to Main Menu");
        MenuItem restartGameMenu = new MenuItem("Restart Game");
//        exitMainMenu.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent t) {
//                OwareMenuScreen view = new OwareMenuScreen(mainStage);
//            }
//        });
//        restartGameMenu.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent t) {
//                controller.resetGame();
//            }
//        });
        menuOware.getItems().add(exitMainMenu);
        menuOware.getItems().add(restartGameMenu);
        menuBar.getMenus().addAll(menuOware);
        root.getChildren().add( menuBar);
        root.setAlignment(Pos.TOP_CENTER);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
