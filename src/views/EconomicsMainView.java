package views;

import com.wolfram.alpha.WAQueryResult;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.image.*;
import com.wolfram.alpha.*;
import model.Billionaire;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class EconomicsMainView {
    private ComboBox cbIndicators;
    private ArrayList<CheckBox> checkBoxes;
    private Button btn;
    private Button searchBtn;
    private TextField textSearch;
    private VBox rightSide;
    private Button billionairesViewBtn;
    private BorderPane leftSide;
    private SplitPane root;

    public EconomicsMainView(Stage primaryStage) {
        root = new SplitPane();
        root.setDividerPositions(0.7f);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        setLeftPanel();
        setRightPanel();
        Scene scene = new Scene(root, primaryScreenBounds.getWidth(), 750);
        scene.getStylesheets().add("resources/css/styling2.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void setLeftPanel() {
        leftSide = new BorderPane();
        Label centerLabel = new Label("The Economics of the World");
        centerLabel.getStyleClass().add("largeLabelTxt");
        centerLabel.setTextFill(Color.WHITE);
        leftSide.setCenter(centerLabel);
        leftSide.getStyleClass().add("leftSideBg");
        root.getItems().add(leftSide);
    }

    public BorderPane getLeftSide() {
        return leftSide;
    }


    public void setRightPanel() {
        rightSide = new VBox();
        Menu menu1 = new Menu("About");
        MenuItem mAbout = new MenuItem("Credits");
        mAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aboutView();
            }
        });
        menu1.getItems().addAll(mAbout);
        Menu menu2 = new Menu("Help");
        MenuItem mHelp = new MenuItem("Tutorial");
        mHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                helpView();
            }
        });
        menu2.getItems().addAll(mHelp);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2);
        rightSide.getChildren().add(menuBar);
        rightSide.setSpacing(10);
        rightSide.getStyleClass().add("bg-white");
        VBox v = getGraphSettingsView();
        v.setPadding(new Insets(30, 0, 0, 20));
        rightSide.getChildren().addAll(v,getWolfram());
        root.getItems().add(rightSide);
    }


    public void aboutView() {
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        Label l = new Label("Credit:");
        v.getChildren().add(l);
        Scene scene = new Scene(v);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(
                e -> {
                    e.consume();
                    stage.close();
                }
        );
        stage.showAndWait();
    }

    public void helpView() {
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        Label l = new Label("Help:");
        v.getChildren().add(l);
        Scene scene = new Scene(v);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(
                e -> {
                    e.consume();
                    stage.close();
                }
        );
        stage.showAndWait();
    }

    public void setBillionairesView(ArrayList<Billionaire> billionaires, PieChart bc) {
        BorderPane h = new BorderPane();
        for(int i = 0; i<billionaires.size(); i++) {
            Billionaire b = billionaires.get(i);
            VBox v = new VBox();
            v.getChildren().addAll(new Label(String.valueOf(i+1) + "." + b.getName()),new Label(b.getIndustry()),new Label(String.valueOf(b.getWorthValue())));
        }
        h.setCenter(bc);
        leftSide.setCenter(h);
    }

    public void billionairesDailyView(Billionaire b) {
        VBox v = new VBox();
        Label title = new Label("Today's Wealthiest Person on Earth");
        v.setPadding(new Insets(30, 0, 0, 20));

        VBox person = new VBox();
        person.setPadding(new Insets(20,0,20,0));
        Label name = new Label(b.getName());
        Label networth = new Label("Net-Worth of " + String.valueOf(b.getWorthValue()) + "B");
        name.getStyleClass().add("mediumLabelTxt");
        networth.getStyleClass().add("mediumLabelTxt");

        person.setSpacing(5);
        person.getChildren().addAll(name,networth);

        title.getStyleClass().add("h1");
        billionairesViewBtn = new Button("View Billionaires Origins of Wealth");

        billionairesViewBtn.setTextFill(Color.WHITE);
        billionairesViewBtn.getStyleClass().add("btn-success");
        billionairesViewBtn.setMinWidth(200);
        v.getChildren().addAll(title,person,billionairesViewBtn);
        rightSide.getChildren().add(1,v);
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
        graphOptions.setPadding(new Insets(0,0,0,0));
        graphOptions.setSpacing(10);
        cbIndicators = new ComboBox();
        cbIndicators.getItems().addAll("GDP","GDP Per Capita", "Unemployment",
                "Consumer Price Index(Inflation)", "Imports of Goods", "Exports of Goods",
                "Real Interest Rates", "Tax Rates");
        btn = new Button("Explore Visualizations'");
        btn.setTextFill(Color.WHITE);
        btn.getStyleClass().add("btn-success");
        btn.setMinWidth(200);
        Text txtVisualisationToExplore = new Text("Select Visualisation to Explore:");
        txtVisualisationToExplore.getStyleClass().add("h1");
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
        searchTitle.getStyleClass().add("h1");

        searchWolfram.getChildren().add(searchTitle);
        searchWolfram.setPadding(new Insets(30, 30, 0, 20));
        searchWolfram.setSpacing(20);
        textSearch = new TextField();
        textSearch.getStyleClass().add("input-txt");
        textSearch.promptTextProperty().setValue("Minimum Wage in Mexico");
        searchBtn = new Button("Search");
        searchBtn.setTextFill(Color.WHITE);
        searchBtn.getStyleClass().add("btn-success");
        searchBtn.setMinWidth(200);
        searchWolfram.getChildren().add(textSearch);
        searchWolfram.getChildren().add(searchBtn);
        return searchWolfram;
    }

    public TextField getTextSearch() {
        return textSearch;
    }

    public void setWolframQueryResults(WAQueryResult queryResult) {
        Stage stageResults = new Stage();
        BorderPane rootQueryResults = new BorderPane();
        Scene scene = new Scene(rootQueryResults, 470, 350);
        stageResults.setTitle("Search");
        stageResults.setScene(scene);
        stageResults.show();

        ArrayList<VBox> listOfPods = new ArrayList<VBox>();
        for (WAPod pod : queryResult.getPods()) {
            if (!pod.isError()) {

                VBox hb = new VBox();
                hb.getChildren().add(new Label(pod.getTitle()));
                for (WASubpod subpod : pod.getSubpods()) {

                    for (Object element : subpod.getContents()) {
                        System.out.println(element);
                        if (element instanceof WAPlainText) {
                            hb.getChildren().add(new Label(((WAPlainText) element).getText()));
                            hb.getChildren().add(new Label(""));
                        } else if(element instanceof WAImage) {
                            System.out.println(((WAImage) element).getURL());
                            Image imageWA = new Image(((WAImage) element).getURL());
                            ImageView img2 = new ImageView(imageWA);
                            img2.setFitHeight(400);
                            img2.setFitWidth(450);

                            img2.setPreserveRatio(true);
                            img2.setOnMousePressed(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    imagePopupWindowShow(new ImageView(imageWA));
                                }
                            });
                            hb.getChildren().add(img2);
                        }
                    }
                }
                listOfPods.add(hb);
            }
            rootQueryResults.setCenter(listOfPods.get(0));
            Button btn2 = new Button("Next Fact");
            btn2.getStyleClass().add("btn-success");

            btn2.setOnAction(new EventHandler<ActionEvent>() {
                int count = 0;
                @Override
                public void handle(ActionEvent event) {
                    if(count > listOfPods.size() - 1) {
                        rootQueryResults.setCenter(listOfPods.get(0));
                        count = 0;
                    } else {
                        rootQueryResults.setCenter(listOfPods.get(count));
                        count++;
                    }
                }

            });
            rootQueryResults.setBottom(btn2);
        }
    }

    public void imagePopupWindowShow(ImageView imageView) {
        Image image;
        BorderPane pane;
        Scene scene;
        Stage stage;

        imageView.setFitHeight(400);
        imageView.setFitWidth(400);

        imageView.setPreserveRatio(true);

        pane = new BorderPane();
        pane.setCenter(imageView);
        scene = new Scene(pane);
        stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(
                e -> {
                    e.consume();
                    stage.close();
                }
        );
        stage.showAndWait();
    }

    public Button getbillionairesViewBtn() { return billionairesViewBtn; }

    public ArrayList<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public Button getGraphSearchBtn() {
        return btn;
    }

    public Button getSearchBtn() {
        return searchBtn;
    }
}
