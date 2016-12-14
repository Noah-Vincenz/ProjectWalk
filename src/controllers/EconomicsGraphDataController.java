package controllers;

import views.charts.MyBarChart;
import views.charts.MyLineChart;
import com.wolfram.alpha.WAQueryResult;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import model.Billionaire;
import model.Indicator;
import model.Networking;
import model.SearchData;
import views.EconomicsMainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;
import javafx.scene.control.Alert;

/**
 * Created by Jaitens on 08/12/2016.
 */
public class EconomicsGraphDataController {
    private EconomicsMainView view;
    private HashMap model;
    private Set<String> codesList; //Just one Code in this case, because one country
    private String countryCode;
    private String firstYear;
    private Map <String, TreeMap<String, Indicator>> map;
    private int noOfCountries;
    private int noOfYears;
    private String finalYear;
    private Button btnSwitchGraph;

    private SearchData modelSearch;
    private static ObservableList<PieChart.Data> list = FXCollections.observableList(new ArrayList<PieChart.Data>());


    /**
     * The EconomicsGraphDataController takes object EconomicsMainView which is a
     * stage as a parameter uses setSelectionButtonListener, searchWolframEnabled,
     * billionairesDailyView and displayBillionaireData to set the stage up properly.
     * @param EconomicsMainView
     */
    public EconomicsGraphDataController(EconomicsMainView viewMain) {
        view = viewMain;
        setSelectionButtonListener();
        modelSearch = new SearchData();
        searchWolframEnabled();
        billionairesDailyView();
        displayBillionaireData();
    }

    /**
     * The setSelectionButtonListener method gets called whenever the search button is clicked,
     * it checks to see which combobox for each country is selected and based on that it adds it
     * to the arrayList countriesSelectedList containing the selected countries.
     *
     * It then compares every indicators name till it finds the selected indicator and makes the
     * indicatorCode equal to the corresponding code for the name.
     *
     * Then, it checks to see if the selected number of countries is equal to 1 and if so it creates
     * a line graph based on the indicator chosen. Else, it creates a bar chart containing all the
     * countries chosen and the corresponding indicator.
     */
    public void setSelectionButtonListener() {
        view.getGraphSearchBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<CheckBox> countriesCheckBoxes = view.getCheckBoxes();
                ArrayList<String> countriesSelectedList = new ArrayList<String>();
                for (int i = 0; i < countriesCheckBoxes.size(); i++) {
                    CheckBox countriesSelected = countriesCheckBoxes.get(i);
                    if (countriesSelected.getText() == "United Kingdom" && countriesSelected.isSelected()) {
                        countriesSelectedList.add("GB");
                        System.out.println("Selected");
                    }
                    if (countriesSelected.getText() == "Romania" && countriesSelected.isSelected()) {
                        countriesSelectedList.add("RO");
                    }
                    if (countriesSelected.getText() == "United States" && countriesSelected.isSelected()) {
                        countriesSelectedList.add("USA");
                    }
                    if (countriesSelected.getText() == "Turkey" && countriesSelected.isSelected()) {
                        countriesSelectedList.add("TUR");
                    }
                    if (countriesSelected.getText() == "Switzerland" && countriesSelected.isSelected()) {
                        countriesSelectedList.add("CHE");
                    }
                    if (countriesSelected.getText() == "Canada" && countriesSelected.isSelected()) {
                        countriesSelectedList.add("CAN");
                    }
                    if (countriesSelected.getText() == "Germany" && countriesSelected.isSelected()) {
                        countriesSelectedList.add("DE");
                    }
                    if (countriesSelected.getText() == "China" && countriesSelected.isSelected()) {
                        countriesSelectedList.add("CHN");
                    }
                    if (countriesSelected.getText() == "Russia" && countriesSelected.isSelected()) {
                        countriesSelectedList.add("RUS");
                    }
                }
                String indicatorCode = "";
                ComboBox visualisationSelected = view.getIndicatorSelection();
                if(visualisationSelected.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Please enter an indicator");
                    alert.setContentText("You haven't entered an indicator you'd like to view!");
                    alert.showAndWait();
                }
                if (visualisationSelected.getSelectionModel().getSelectedItem() != null) {
                    String graphSelected = visualisationSelected.getSelectionModel().getSelectedItem().toString();
                    System.out.println("Graph selected: " + graphSelected);
                    if (graphSelected.equals("GDP")) {
                        indicatorCode = "NY.GDP.MKTP.KD.ZG";
                    } else if (graphSelected.equals("GDP Per Capita")) {
                        indicatorCode = "NY.GDP.PCAP.KD.ZG";
                    } else if (graphSelected.equals("Unemployment")) {
                        indicatorCode = "SL.UEM.TOTL.ZS";
                    } else if (graphSelected.equals("Consumer Price Index(Inflation)")) {
                        indicatorCode = "FP.CPI.TOTL.ZG";
                    } else if (graphSelected.equals("Imports of Goods")) {
                        indicatorCode = "NE.IMP.GNFS.ZS";
                    } else if (graphSelected.equals("Exports of Goods")) {
                        indicatorCode = "NE.EXP.GNFS.ZS";
                    } else if (graphSelected.equals("Real Interest Rates")) {
                        indicatorCode = "FR.INR.RINR";
                    } else if (graphSelected.equals("Tax Rates")) {
                        indicatorCode = "IC.TAX.TOTL.CP.ZS";
                    }
                }

                System.out.println("Indicator code: " + indicatorCode);

                final String finalIndicatorCode = indicatorCode;

                try {
                    model = Networking.getInstance().getRangeOfIndicatorsForCountries(countriesSelectedList, indicatorCode, "1980", "2015");
                    if(countriesSelectedList.size() == 1) {

                        view.getLeftSide().setCenter(MyLineChart.getInstance().getLineChart(Networking.getInstance().getRangeOfIndicatorsForCountries(countriesSelectedList, indicatorCode, "1980", "2015")));
                        btnSwitchGraph = new Button("Switch Graph Type");
                        btnSwitchGraph.getStyleClass().add("btn-success");

                        btnSwitchGraph.setAlignment(Pos.CENTER);
                        btnSwitchGraph.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if(view.getLeftSide().getCenter() instanceof LineChart) {
                                    try {
                                        view.getLeftSide().setCenter(MyBarChart.getInstance().getBarChart(Networking.getInstance().getRangeOfIndicatorsForCountries(countriesSelectedList, finalIndicatorCode, "1980", "2015")));
                                    } catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        view.getLeftSide().setCenter(MyLineChart.getInstance().getLineChart(Networking.getInstance().getRangeOfIndicatorsForCountries(countriesSelectedList, finalIndicatorCode, "1980", "2015")));
                                    } catch(Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        });
                        view.getLeftSide().setBottom(btnSwitchGraph);
                    } else if(countriesSelectedList.size() > 1) {
                        view.getLeftSide().setBottom(null);
                        view.getLeftSide().setCenter(MyBarChart.getInstance().getBarChart(Networking.getInstance().getRangeOfIndicatorsForCountries(countriesSelectedList, indicatorCode, "1980", "2015")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * The displayBillionaireData creates a HashMap containing the string value which is the
     * industry of where they earn their money and double value of how much they earn.
     *
     * It uses a for loop to through the whole list of billionaires and checks to see which
     * industries earn them the most money. The data from the HashMap is then added to the PieChart.
     */
    public void displayBillionaireData() {
        ArrayList<Billionaire> billionaires = Networking.getInstance().getBillionairesRange(30);

        HashMap<String,Double> map = new HashMap<String,Double>();
        for(int i = 0; i<billionaires.size(); i++) {
            map.merge(billionaires.get(i).getIndustry(), billionaires.get(i).getWorthValue(), Double::sum);
        }

        PieChart pieChart = new PieChart();
        for (Map.Entry<String, Double> entry : map.entrySet()) {

            list.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        pieChart.setData(list);
        pieChart.setTitle("Where Billionaires Made There Money?");
        pieChart.setClockwise(false);
        pieChart.setLabelsVisible(false);

        view.getbillionairesViewBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(btnSwitchGraph != null) {
                    btnSwitchGraph.setVisible(false);
                }
                view.setBillionairesView(billionaires,pieChart);
            }
        });
    }

    /**
     * The billionairesDailyView method is very similar to the displayBillionaireData method except
     * it only gets the data for that day specifically.
     */
    public void billionairesDailyView() {
        ArrayList<Billionaire> billionaires = Networking.getInstance().getBillionairesRange(1);
        view.billionairesDailyView(billionaires.get(0));
    }

    /**
     * The searchWolframEnabled creates a action listener for the search button for the Wolfram Alpha Query
     * and it calls the getResult method on the query, which then sets the view with the relevant data.
     */
    public void searchWolframEnabled() {
        view.getSearchBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        WAQueryResult query = modelSearch.getResult(view.getTextSearch().getText());
                        view.setWolframQueryResults(query);
                    }
                });
            }
        });

    }
}
