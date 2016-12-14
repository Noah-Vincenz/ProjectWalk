package views;

import com.wolfram.alpha.WAQueryResult;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;
import javafx.scene.shape.Circle;

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
    private boolean q1;
    private boolean q2;
    private boolean q3;
    private boolean q4;
    private boolean q5;
    private boolean q6;


    /**
     * Constructor for Economics Main View
     * @param Stage
     */
    public EconomicsMainView(Stage primaryStage) {
        root = new SplitPane();
        root.setDividerPositions(0.7f);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        setLeftPanel();
        setRightPanel();
        Scene scene = new Scene(root, primaryScreenBounds.getWidth(), 750);
        File file = new File("src/resources/css/styling2.css");
        try{
            URL url = file.toURI().toURL();
            scene.getStylesheets().add(url.toExternalForm());
        } catch(MalformedURLException ex) {

        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Sets Left Panel Styling
     */
    public void setLeftPanel() {
        leftSide = new BorderPane();
        Label centerLabel = new Label("The Economics of the World");
        centerLabel.getStyleClass().add("largeLabelTxt");
        centerLabel.setTextFill(Color.WHITE);
        leftSide.setCenter(centerLabel);
        leftSide.getStyleClass().add("leftSideBg");
        root.getItems().add(leftSide);
    }

    /**
     * returns the leftSide of the primaryStage
     * @return BorderPane
     */
    public BorderPane getLeftSide() {
        return leftSide;
    }

    /**
     * Creates a BorderPane called bpRevisionGuide and a VBox called All_Units.
     * Creates strings containing information on each topic.
     * Hyperlinks of topic names and text titles of topics are added All_Units.
     * All_Units is added to setLeft of BorderPane.
     * Then a TextArea is added to the center of BorderPane
     * ActionListeners are created for each HyperLink and based on which is pressed
     * the text of the TextArea is set to the strings containing the relavent data.
     * Some designing on All_Units, TextArea, and BorderPane to make it more appealing.
     * @return BorderPane
     */
    public BorderPane revGuide() {
        BorderPane bpRevisionGuide = new BorderPane();
        VBox All_Units = new VBox();

        String tmomp = ("                                                                     The Measurement of Macroeconomic Performance \n\n" +
                "Macroeconomic Indicators: GDP, GDP per capita, Consumer Price Index, Retail Price Indices(CPI/RPI), Unemployment, and the Balance of Payments on current accounts.\n\n" +
                "Need to make data comparisons with other economies based on the data that these indicators provide.\n\n" +
                "The Economic Cycle: The economic growth of the economy between periods of expansion(growth) and contraction(recession). There are factors that such as GDP, interest rates, and consumer spending can help determine which stage in the economic cycle the country is currently in. \n\n" +
                "Main factor causing the economic cycle is too much demand and not enough supply, hence an increase in prices of goods, causing a growth in the economy. The cycle carries on until the price is too much for consumers to afford or the supply increases productivity to match the demand.\n\n");
        String hdmw = ("                                                                     How does Macroeconomics Work?\n\n" +
                "Aggregate Supply: Is the relationship between a nation's overall price level and the quantity of the goods/services produced by the nation's suppliers. This is affected by the following three factors: \n\n" +
                "Supply Shocks - These are sudden increases/decreases in supply that occur temporarily. For example, good or bad weather can affect productivity.\n\n" +
                "Resource Price Changes - This can only affect the supply if it remains long-term.\n\n" +
                "Changes in Expectations For Inflation - Suppliers can become less willing to sell their supply, if they know that the price for their goods will increase due to inflation.\n\n" +
                "Aggregate Demand: Is the relationship between a nation's overall demand for goods/services. This is affected by the following factors and many more:\n\n" +
                "Real Interest Rate Changes - When interest rates are low, people tend to spend more and and take loans, so demand increases. When it is high, people tend to store their money in banks and spend less, so demand decreases.\n\n" +
                "The Wealth Effect - If real household wealth increases, then aggregate demand will increase.\n\n" +
                "Changes in Income of Foreigners - If the income of foreigners increases, then aggregate demand for domestically-produced goods and services should increase.\n\n");
        String ep = ("                                                                     Economic Performance\n\n" +
                "The Objectives of Government Economic Policy - Consistent prices to keep low inflation. Balanced and controlled economic growth. Either full employment or minimum unemployment. A level-headed balance of payments.\n" +
                "\nEconomic Growth - Rapid economic growth means aggregate demand also known as GDP causes an increase in real GDP means there is an increase in the value of national output / national expenditure. Some benefits of economic growth are Increase in incomes, employment and tax revenue.\n" +
                "\nInflation and Deflation - Inflation -  A consistent increase in the price of goods and services over time.  During inflationary times, money loses its \"buying\" or \"purchasing\" power, and it takes more units of currency to purchase the same units of goods or services.  Over time, inflation lowers the value of each unit of currency.\n" +
                "\nDeflation -  A consistent decrease in the price of goods and services over time.  During deflationary times, money increases in its \"buying\" or \"purchasing\" power, and it takes less units of currency to purchase the same units of goods or services.  Over time, deflation increases the value of each unit of currency.\n" +
                "Employment and Unemployment - The economic objective of the economy as always been full employment, but it is not possible to give a numerical definition to unemployment rate.  \n" +
                "\nUnemployment is the number of people who are actively searching for work. Unemployment can be reduced by retraining employees.\n" +
                "\nUnemployment Effects - Negative Multiplier Effect - Unemployed individuals, as mentioned above, are not able to spend as much money on goods and services. Loss of tax revenue as less people are working hence less people are paying their taxes. Society becauses less equal and the gap between the wealth and income of the employed and unemployed become greater. This can lead to higher crime rates, lower quality of healthy goods and living.\n" +
                "\nThe Balance of Payments on Current Account - The current account on the balance of payments measures the inflow and outflow of goods, services and investment incomes.\n\n");
        String mp = ("                                                                     Macroeconomic Policy\n" +
                "\nFiscal Policy - Fiscal policy is the means by which a government adjusts its spending levels and tax rates to monitor and influence a nation's economy. It is the sister strategy to monetary policy through which a central bank influences a nation's money supply.\n" +
                "\nMonetary Policy - Monetary policy is the macroeconomic policy laid down by the central bank. It involves management of money supply and interest rate and is the demand side economic policy used by the government of a country to achieve macroeconomic objectives like inflation, consumption, growth and liquidity.\n" +
                "\nSupply side Policy - Supply Side Policies are government attempts to increase productivity and shift Aggregate Supply (AS) to the right.\n" +
                "\nExchange Rate Policy - An exchange-rate regime is the way an authority manages its currency in relation to other currencies and the foreign exchange market. It is closely related to monetary policy and the two are generally dependent on many of the same factors.\n");
        String tie = ("                                                                     The International Economy\n" +
                "\nGlobalisation - Economic globalization is the increasing economic integration and interdependence of national, regional, and local economies across the world through an intensification of cross-border movement of goods, services, technologies and capital.\n" +
                "\nTrade - Is a basic economic concept involving the buying and selling of goods and services, with compensation paid by a buyer to a seller, or the exchange of goods or services between parties.\n" +
                "\nThe Balance of Payments - The balance of payments, also known as balance of international payments, encompasses all transactions between a country's residents and its nonresidents involving goods, services and income; financial claims on and liabilities to the rest of the world; and transfers such as gifts.\n" +
                "\nExchange Rate Systems - A fixed exchange rate, sometimes called a pegged exchange rate, is a type of exchange rate regime where a currency's value is fixed against either the value of another single currency, to a basket of other currencies, or to another measure of value, such as gold.\n" +
                "\nA floating exchange rate or fluctuating exchange rate is a type of exchange-rate regime in which a currency's value is allowed to fluctuate in response to foreign-exchange market mechanisms. A currency that uses a floating exchange rate is known as a floating currency.");

        TextArea mainDefinitions = new TextArea();

        All_Units.setPadding(new Insets(10));
        All_Units.setSpacing(8);

        Hyperlink unit_2_and_4_topics[] = new Hyperlink[]{
                new Hyperlink("The Measurement of Macroeconomic Performance"),
                new Hyperlink("How Does Macroeconomics Work?"),
                new Hyperlink("Economic Performance"),
                new Hyperlink("Macroeconomic Policy"),
                new Hyperlink("The International Economy")};

        Text unit_2_Title = new Text("Topics to Revise:");
        unit_2_Title.setFont(Font.font("Times New Roman", 20));
        All_Units.getChildren().add(unit_2_Title);
        for (int i = 0; i < 5; i++) {
            VBox.setMargin(unit_2_and_4_topics[i], new Insets(0, 0, 0, 8));
            All_Units.getChildren().add(unit_2_and_4_topics[i]);
        }
        All_Units.setAlignment(Pos.CENTER);
        mainDefinitions.setWrapText(true);
        mainDefinitions.setEditable(false);

        mainDefinitions.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        unit_2_and_4_topics[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainDefinitions.setText(tmomp);
            }
        });
        unit_2_and_4_topics[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainDefinitions.setText(hdmw);
            }
        });
        unit_2_and_4_topics[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainDefinitions.setText(ep);
            }
        });
        unit_2_and_4_topics[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainDefinitions.setText(mp);
            }
        });
        unit_2_and_4_topics[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainDefinitions.setText(tie);
            }
        });
        bpRevisionGuide.setLeft(All_Units);
        bpRevisionGuide.setCenter(mainDefinitions);
        return bpRevisionGuide;
    }

    /**
     * Creates a GridPane called gp_Quiz1.
     * Creates a VBox for each selection of radiobuttons.
     * Create ToggleGroup for each selection of radiobuttons for each question.
     * Create Text questions.
     * add the questions and Vboxes to the GridPane
     * Created action listeners for each one and used boolean values to keep track
     * of the correct selected radiobutton for each question.
     * Created button called results and added an action listener to it that uses
     * if statements to check if the boolean values are true and if so counter is
     * increased. At the end a Alert message gets created to inform the user of how
     * many questions they answered correctly.
     * @return ScrollPane
     */
    public ScrollPane quizGuide() {
        GridPane gp_Quiz1 = new GridPane();
        VBox choices1 = new VBox();
        VBox choices2 = new VBox();
        VBox choices3 = new VBox();
        VBox choices4 = new VBox();
        VBox choices5 = new VBox();
        VBox choices6 = new VBox();

        Button btn_Results = new Button("Results");
        btn_Results.setTextFill(Color.WHITE);
        btn_Results.setStyle("-fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #FE9486 100%),\n" +
                "        #9d4024,\n" +
                "        #d86e3a,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #FE9486); -fx-font-size:18px; -fx-font-family:\"Helvetica\";");
        btn_Results.setMinWidth(120);

        ToggleGroup tg1 = new ToggleGroup();
        Text txt_Question1 = new Text("1) What will increase aggregate demand?");
        txt_Question1.setFont(Font.font("Times New Roman", 18));
        RadioButton rdChoicesA = new RadioButton("Saving Rise");
        rdChoicesA.setToggleGroup(tg1);
        RadioButton rdChoicesAb = new RadioButton("Exports Fall");
        rdChoicesAb.setToggleGroup(tg1);
        RadioButton rdChoicesAc = new RadioButton("Imports Rise");
        rdChoicesAc.setToggleGroup(tg1);
        RadioButton rdChoicesAd = new RadioButton("Investments Rises");
        rdChoicesAd.setToggleGroup(tg1);
        choices1.getChildren().addAll(rdChoicesA, rdChoicesAb ,rdChoicesAc ,rdChoicesAd);
        choices1.setSpacing(15);

        ToggleGroup tg2 = new ToggleGroup();
        Text txt_Question2 = new Text("2) What will decrease aggregate demand?");
        txt_Question2.setFont(Font.font("Times New Roman", 18));
        RadioButton rdChoicesB = new RadioButton("Saving Rise");
        rdChoicesB.setToggleGroup(tg2);
        RadioButton rdChoicesBb = new RadioButton("Interest Rates Fall");
        rdChoicesBb.setToggleGroup(tg2);
        RadioButton rdChoicesBc = new RadioButton("Investment Rises");
        rdChoicesBc.setToggleGroup(tg2);
        RadioButton rdChoicesBd = new RadioButton("Imports Fall");
        rdChoicesBd.setToggleGroup(tg2);
        choices2.getChildren().addAll(rdChoicesB, rdChoicesBb ,rdChoicesBc ,rdChoicesBd);
        choices2.setSpacing(15);

        ToggleGroup tg3 = new ToggleGroup();
        Text txt_Question3 = new Text("3) A shift to the left in the AD curve is most likely to be caused by falling:");
        txt_Question3.setFont(Font.font("Times New Roman", 18));
        RadioButton rdChoicesC = new RadioButton("Imports");
        rdChoicesC.setToggleGroup(tg3);
        RadioButton rdChoicesCb = new RadioButton("Exports");
        rdChoicesCb.setToggleGroup(tg3);
        RadioButton rdChoicesCc = new RadioButton("Tax Rates");
        rdChoicesCc.setToggleGroup(tg3);
        RadioButton rdChoicesCd = new RadioButton("Savings");
        rdChoicesCd.setToggleGroup(tg3);
        choices3.getChildren().addAll(rdChoicesC, rdChoicesCb ,rdChoicesCc ,rdChoicesCd);
        choices3.setSpacing(15);

        ToggleGroup tg4 = new ToggleGroup();
        Text txt_Question4 = new Text("4) The long run aggregate supply curve is affected by: ");
        txt_Question4.setFont(Font.font("Times New Roman", 18));
        RadioButton rdChoicesD = new RadioButton("Prices");
        rdChoicesD.setToggleGroup(tg4);
        RadioButton rdChoicesDb = new RadioButton("Wages");
        rdChoicesDb.setToggleGroup(tg4);
        RadioButton rdChoicesDc = new RadioButton("Exchange Rates");
        rdChoicesDc.setToggleGroup(tg4);
        RadioButton rdChoicesDd = new RadioButton("Technology");
        rdChoicesDd.setToggleGroup(tg4);
        choices4.getChildren().addAll(rdChoicesD, rdChoicesDb ,rdChoicesDc ,rdChoicesDd);
        choices4.setSpacing(15);

        ToggleGroup tg5 = new ToggleGroup();
        Text txt_Question5 = new Text("5) If real income per head rises in one country, the most likely result is:");
        txt_Question5.setFont(Font.font("Times New Roman", 18));
        RadioButton rdChoicesE = new RadioButton("Rising Imports");
        rdChoicesE.setToggleGroup(tg5);
        RadioButton rdChoicesEb = new RadioButton("Falling Investment");
        rdChoicesEb.setToggleGroup(tg5);
        RadioButton rdChoicesEc = new RadioButton("Rising Exports");
        rdChoicesEc.setToggleGroup(tg5);
        RadioButton rdChoicesEd = new RadioButton("Falling Aggregate Demand");
        rdChoicesEd.setToggleGroup(tg5);
        choices5.getChildren().addAll(rdChoicesE, rdChoicesEb ,rdChoicesEc ,rdChoicesEd);
        choices5.setSpacing(15);

        ToggleGroup tg6 = new ToggleGroup();
        Text txt_Question6 = new Text("6) Fiscal policy is associated with? ");
        txt_Question6.setFont(Font.font("Times New Roman", 18));
        RadioButton rdChoicesF = new RadioButton("Changing Interest Rates");
        rdChoicesF.setToggleGroup(tg6);
        RadioButton rdChoicesFb = new RadioButton("Changes in Government Borrowing");
        rdChoicesFb.setToggleGroup(tg6);
        RadioButton rdChoicesFc = new RadioButton("Reducing Money Supply");
        rdChoicesFc.setToggleGroup(tg6);
        RadioButton rdChoicesFd = new RadioButton("Lowering Exchange Rates");
        rdChoicesFd.setToggleGroup(tg6);
        choices6.getChildren().addAll(rdChoicesF, rdChoicesFb ,rdChoicesFc ,rdChoicesFd);
        choices6.setSpacing(15);

        gp_Quiz1.setHgap(10);
        gp_Quiz1.setVgap(10);
        gp_Quiz1.setPadding(new Insets(10, 10, 10, 10));

        gp_Quiz1.add(txt_Question1, 0, 0);
        gp_Quiz1.add(choices1, 0, 1);
        gp_Quiz1.add(txt_Question2, 0, 2);
        gp_Quiz1.add(choices2, 0, 3);
        gp_Quiz1.add(txt_Question3, 0 , 4);
        gp_Quiz1.add(choices3, 0, 5);
        gp_Quiz1.add(txt_Question4, 0, 6);
        gp_Quiz1.add(choices4, 0, 7);
        gp_Quiz1.add(txt_Question5, 0, 8);
        gp_Quiz1.add(choices5, 0, 9);
        gp_Quiz1.add(txt_Question6, 0, 10);
        gp_Quiz1.add(choices6, 0, 11);
        gp_Quiz1.add(btn_Results, 1, 12);
        gp_Quiz1.setPrefSize(600,500);
        ScrollPane spQuiz = new ScrollPane(gp_Quiz1);

        tg1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton rt = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                if (rt.getText() == "Investments Rises") {
                    q1 = true;
                }
                else {
                    q1 = false;
                }
            }
        });

        tg2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton rt = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                if (rt.getText() == "Saving Rise") {
                    q2 = true;
                }
                else {
                    q2 = false;
                }
            }
        });

        tg3.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton rt = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                if (rt.getText() == "Exports") {
                    q3 = true;
                }
                else {
                    q3 = false;
                }
            }
        });
        tg4.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton rt = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                if (rt.getText() == "Technology") {
                    q4 = true;
                }
                else {
                    q4 = false;
                }
            }
        });
        tg5.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton rt = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                if (rt.getText() == "Rising Imports") {
                    q5 = true;
                }
                else {
                    q5 = false;
                }
            }
        });
        tg6.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton rt = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                if (rt.getText() == "Changes in Government Borrowing") {
                    q6 = true;
                }
                else {
                    q6 = false;
                }
            }
        });

        btn_Results.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int counter = 1;
                if (q1 == true){
                    counter++;
                }
                if (q2 == true) {
                    counter++;
                }
                if (q3 == true) {
                    counter++;
                }
                if (q4 == true) {
                    counter++;
                }
                if (q5 == true) {
                    counter++;
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Quiz Results");
                alert.setContentText("You managed to correctly answer " + counter + " out of 6 questions.");
                alert.showAndWait();
            }
        });

        return spQuiz;
    }

    /**
     * The method setRightPanel creates 3 Menus, 'Revision', 'About' and 'Help'.
     * Revision has two Menu items, Revision Guide and Quiz.
     * To create the view for Revision Guide, the method revGuide is called to create BorderPane
     * with the relevant information. An action listener is set for Revision Guide so when it is called
     * a scene and stage with information will be shown. The same goes for Quiz except it is in a ScrollPane.
     *
     * The About has one MenuItem called Credits and this basically creates a BorderPane
     * with labels and text containing the relevant information and puts that into a scene.
     * That scene is then set to a new stage and it is shown when the action listener is activated.
     *
     * The Help has one MenuItem called Tutorial and this basically creates a BorderPane
     * with labels and text containing information on how to use the application and puts that into a scene.
     * That scene is then set to a new stage and it is shown when the action listener is activated.
     *
     * These menus are then added to the right Panel.
     * Then the method graphsettingsView gets called and added to the right Panel.
     */
    public void setRightPanel() {
        rightSide = new VBox();
        Menu menu0 = new Menu("Revision");
        MenuItem revisionGuide = new MenuItem("Revision Guide");
        MenuItem quiz = new MenuItem("Quiz");

        BorderPane bpRevisionGuide = revGuide();
        Scene scene1 = new Scene(bpRevisionGuide, 900, 400);
        Stage secondStage = new Stage();
        secondStage.setScene(scene1);

        revisionGuide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondStage.show();
            }
        });
        ScrollPane spQuiz = quizGuide();
        Scene scene2 = new Scene(spQuiz,700,600);
        Stage thirdStage = new Stage();
        thirdStage.setScene(scene2);

        quiz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                thirdStage.show();
            }
        });
        menu0.getItems().addAll(revisionGuide,quiz);
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
        menuBar.getMenus().addAll(menu0,menu1, menu2);
        rightSide.getChildren().add(menuBar);
        rightSide.setSpacing(10);
        rightSide.getStyleClass().add("bg-white");
        VBox v = getGraphSettingsView();
        v.setPadding(new Insets(30, 0, 0, 20));
        rightSide.getChildren().addAll(v,getWolfram());
        root.getItems().add(rightSide);
    }

    /**
     * The aboutView method creates a BorderPane with labels and text containing the relevant information
     * and puts that into a scene.
     * That scene is then set to a new stage and it is shown when the action listener is activated.
     * Some functionality such as setCloseRequest is also added.
     */
    public void aboutView() {
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        Label ll = new Label("Credits:");
        Text t2 = new Text ("This application was developed by the following members");
        t2.setFont(Font.font("Times New Roman", 15));
        Label l2 = new Label(
                "Ion-Alexandru Clapa \n" +
                        "Noah-Vincenz (Noah) Nöh\n" +
                        "Okan Hoplar\n" +
                        "Jaiten Gill\n" +
                        "Arunita Roy\n");

        Text t3 = new Text ("This application used the following libraries:");
        t3.setFont(Font.font("Times New Roman", 15));
        Label l3 = new Label(
                "WolframAlpha - http://www.wolframalpha.com/\n" +
                        "Json - http://www.json.org/\n" +
                        "WorldBank - http://www.worldbank.org/\n" +
                        "Commons Codec - https://commons.apache.org/proper/commons-codec/\n" +
                        "Commons Logging - https://commons.apache.org/proper/commons-logging/\n" +
                        "HTTP Client and Core - https://hc.apache.org/downloads.cgi\n");

        Text t4 = new Text ("The following resources were used to create this application: ");
        t4.setFont(Font.font("Times New Roman", 15));

        Label l4 = new Label("\nhttps://upload.wikimedia.org/wikipedia/commons/6/6e/London_Thames_Sunset_panorama_-_Feb_2008.jpg");
        v.getChildren().addAll(ll,t2,l2, t3,l3, t4, l4);
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

    /**
     * Creates a scene and stage containing labels and texts to infrom the user
     * of how to use the application correctly.
     */
    public void helpView() {
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        Text t1 = new Text ("Help: ");
        t1.setFont(Font.font("Times New Roman", 15));
        Label ll = new Label(
                "1) The left side of the application shows data using data visualisations such as Line/Bar/Pie charts. \n" +
                        "2) At the top of the right side we have three menus the user can click on and menu items will pop down. \n" +
                        "3) Once you select an menu item a new stage will popup, and it will show data relevant to what you selected. \n" +
                        "4) If you clicked on Revision Guide, you will encounter an interactive stage, where you can select a topic" +
                        "on the left side of the stage and relevant information will popup on the right side of the stage \n" +
                        "5) If you clicked on Quiz, you will encounter another interactive quiz where you will be required to select" +
                        "answers to 6 questions and once you click on the result button, another window will popup informing you" +
                        "of how many questions you answered correctly.\n" +
                        "6) Below those options you will see text field informing you of the top billionaire and a button called" +
                        "'View Billionaires Origins of Wealth'. Once this button is clicked a pie chart on the left side of the application" +
                        "should popup with the relevant information\n" +
                        "7) You are required to select an indicator as well as at least one country in order to use the Explore Visualisations option.\n" +
                        "8) You are required to write a query before you press the 'Search' button so there is data to be retrieved. Once 'Search'" +
                        "button is clicked a new stage will pop up with the relevan information and a button called 'next Fact' will also" +
                        "allow you to navigate through different facts.");

        v.getChildren().addAll(t1, ll);
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

    /**
     * The method setBillionairesView takes an arraylist of billionaires and a Piechart in its parameter.
     * It goes through the list of billionaires using a for loop and creates a VBox
     * that stores the relevant data of the amount of money they make based on the industry.
     * Creates a BorderPane called h and stores the PieChart in center and then
     * it is added to the center of leftSide BorderPane which is the left side of the primary stage.
     * @param ArrayList<Billionaire>
     * @param Piechart
     */
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

    /**
     * The method billionairesDailyView takes into its parameter a Billionaire
     * and creat
     * @param Billionaire
     */
    public void billionairesDailyView(Billionaire b) {
        VBox v = new VBox();
        Label title = new Label("Today's Wealthiest Person on Earth");
        v.setPadding(new Insets(30, 0, 0, 20));

        HBox person = new HBox();
        person.setPadding(new Insets(20,0,20,0));
        VBox personText = new VBox();
        Label name = new Label(b.getName());

        Label networth = new Label("Net-Worth of " + String.valueOf(b.getWorthValue()) + "B");
        name.getStyleClass().add("mediumLabelTxt");
        networth.getStyleClass().add("mediumLabelTxt");

        person.setSpacing(5);
        personText.getChildren().addAll(name,networth);
        if(b.getImageURL() != null) {
            ImageView i = new ImageView(new Image(b.getImageURL()));
            i.setFitHeight(70);
            i.setFitWidth(70);
            i.setPreserveRatio(true);
            person.getChildren().addAll(i,personText);
        } else {
            person.getChildren().addAll(personText);
        }
       

    
        title.getStyleClass().add("h1");
        billionairesViewBtn = new Button("View Billionaires Origins of Wealth");

        billionairesViewBtn.setTextFill(Color.WHITE);
        billionairesViewBtn.getStyleClass().add("btn-success");
        billionairesViewBtn.setMinWidth(200);
        v.getChildren().addAll(title,person,billionairesViewBtn);
        rightSide.getChildren().add(1,v);
    }

    /**
     * The method getGraphSettingsView creates a GridPane containing all the countries
     * which are CheckBoxes. Then combobox of Indicators and corresponding buttons are added.
     * Then the CheckBoxes created earlier are added to an array of CheckBoxes.
     * Lastly the GridPane is added to a VBox and returned.
     * @return VBox
     */
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

    /**
     * The Method getIndicatorSelection returns the Combobox of indicators.
     * @return ComboBox
     */
    public ComboBox getIndicatorSelection() {
        return cbIndicators;
    }

    /**
     * This method uses the searchWolfram to create the
     * @return VBox
     */
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

    /**
     * The method getTextSearch returns a textfield for the searchbutton.
     * @return TextField
     */
    public TextField getTextSearch() {
        return textSearch;
    }

    /**
     * Sets Wolfram Query UI
     * @param WAQueryResult
     */
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


    /**
     * Image Pop-Up Shows Image View
     * @param ImageView
     */
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

    /**
     * Method getbillionairesViewBtn returns a billionaires button.
     * @return Button
     */
    public Button getbillionairesViewBtn() { return billionairesViewBtn; }

    /**
     * Method getCheckBoxes returns an arrayList of checkboxes.
     * @return ArrayList<CheckBox>
     */
    public ArrayList<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    /**
     * Method getGraphSearchBtn returns the search button for the grapth.
     * @return Button
     */
    public Button getGraphSearchBtn() {
        return btn;
    }

    /**
     * Method getSearchBtn returns the search button.
     * @return Button
     */
    public Button getSearchBtn() {
        return searchBtn;
    }
}