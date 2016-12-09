package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class EconomicsMenuScreen {
    private ComboBox cbIndicators;
    private ArrayList<CheckBox> checkBoxes;
    private Button btn;
    private Button searchBtn;

    private SplitPane root;

    public EconomicsMenuScreen(Stage primaryStage) {
        root = new SplitPane();
        setLeftPanel();
        setRightPanel();
        Scene scene = new Scene(root, 930, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void setLeftPanel() {
        BorderPane leftSide = new BorderPane();
        Label centerLabel = new Label("The Economics of the World");
        centerLabel.setStyle("-fx-font-size:34px; -fx-font-family:\"Helvetica\";");
        centerLabel.setTextFill(Color.WHITE);
        leftSide.setCenter(centerLabel);
        leftSide.setStyle("-fx-background-image: url(\"https://upload.wikimedia.org/wikipedia/commons/6/6e/London_Thames_Sunset_panorama_-_Feb_2008.jpg\"); -fx-background-size:cover;");
        root.getItems().add(leftSide);
    }

    public void setRightPanel() {
        VBox rightSide = new VBox();
        rightSide.setPadding(new Insets(50, 50, 50, 50));
        rightSide.setSpacing(10);
        rightSide.setStyle("-fx-background-color:#FFFFFF;");
        getGraphSettingsView();
        rightSide.getChildren().addAll(getWolfram(),getGraphSettingsView());
        root.getItems().add(rightSide);
    }

    public VBox getGraphSettingsView() {
        CheckBox United_Kingdom = new CheckBox("United Kingdom");
        CheckBox United_States = new CheckBox("United States");
        CheckBox Romania = new CheckBox("Romania");
        CheckBox Turkey = new CheckBox("Turkey");
        CheckBox Switzerland = new CheckBox("Switzerland");
        CheckBox Canada = new CheckBox("Canada");
        CheckBox Germany = new CheckBox("Germany");
        CheckBox China = new CheckBox("China");
        CheckBox Japan = new CheckBox("Japan");
        CheckBox Russia = new CheckBox("Russia");
        GridPane gpMid = new GridPane();
        Text txtCountries = new Text("Select Countries Below:");
        txtCountries.setFont(Font.font("Helvetica", 20));
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
        VBox graphOptions = new VBox();
        graphOptions.setPadding(new Insets(50,0,0,0));
        graphOptions.setSpacing(10);
        cbIndicators = new ComboBox();
        cbIndicators.getItems().addAll("GDP","GDP Per Capita", "Unemployment",
                "Consumer Price Index(Inflation)", "Imports of Goods", "Exports of Goods",
                "Real Interest Rates", "Tax Rates");
        btn = new Button("Explore Visualizations'");
        btn.setTextFill(Color.WHITE);
        btn.setStyle("-fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #FE9486 100%),\n" +
                "        #9d4024,\n" +
                "        #d86e3a,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #FE9486); -fx-font-size:18px; -fx-font-family:\"Helvetica\";");
        btn.setMinWidth(200);
        Text txtVisualisationToExplore = new Text("Select Visualisation to Explore:");
        txtVisualisationToExplore.setFont(Font.font("Helvetica", 20));

        graphOptions.getChildren().addAll(txtVisualisationToExplore,cbIndicators,gpMid,btn);

        //Array CheckBoxes
        checkBoxes = new ArrayList<CheckBox>();
        checkBoxes.add(United_Kingdom);
        checkBoxes.add(United_States);
        checkBoxes.add(Romania);
        checkBoxes.add(Turkey);
        checkBoxes.add(Switzerland);
        checkBoxes.add(Canada);
        checkBoxes.add(Germany);
        checkBoxes.add(China);
        checkBoxes.add(Japan);
        checkBoxes.add(Russia);
        return graphOptions;
    }

    public ComboBox getIndicatorSelection() {
        return cbIndicators;
    }

    public VBox getWolfram() {
        VBox searchWolfram = new VBox();
        Label searchTitle = new Label("Have a Question? Ask Me?");
        searchTitle.setStyle("-fx-font-size:20px; -fx-font-family:\"Helvetica\";");

        searchWolfram.getChildren().add(searchTitle);
        searchWolfram.setAlignment(Pos.CENTER);
        searchWolfram.setSpacing(20);
        TextField textSearch = new TextField();

        searchBtn = new Button("Search");
        searchBtn.setTextFill(Color.WHITE);
        searchBtn.setStyle("-fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #FE9486 100%),\n" +
                "        #9d4024,\n" +
                "        #d86e3a,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #FE9486); -fx-font-size:18px; -fx-font-family:\"Helvetica\";");
        searchWolfram.getChildren().add(textSearch);
        searchWolfram.getChildren().add(searchBtn);
        return searchWolfram;
    }

    public ArrayList<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public Button getSearchBtn() {
        return btn;
    }



}
