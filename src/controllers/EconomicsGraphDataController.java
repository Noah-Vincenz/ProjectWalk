package controllers;

import com.wolfram.alpha.WAQueryResult;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Billionaire;
import model.Indicator;
import src.model.Networking;
import model.SearchData;
import views.EconomicsMainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

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
    private String indicator;
    private Stage stageMain;
    private SearchData modelSearch;
    private static ObservableList<PieChart.Data> list = FXCollections.observableList(new ArrayList<PieChart.Data>());

    //Graph Data Controller
    public EconomicsGraphDataController(EconomicsMainView viewMain) {
        view = viewMain;
        setSelectionButtonListener();
        modelSearch = new SearchData();
        searchWolframEnabled();
        billionairesDailyView();
        displayBillionaireData();
    }


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
                if (visualisationSelected.getSelectionModel().getSelectedItem() != null) {
                    String graphSelected = visualisationSelected.getSelectionModel().getSelectedItem().toString();
                    if (graphSelected == "GDP") {
                        indicatorCode = "NY.GDP.MKTP.KD.ZG";
                    } else if (graphSelected == "GDP Per Capita") {
                        indicatorCode = "NY.GDP.PCAP.KD.ZG";
                    } else if (graphSelected == "Unemployment") {
                        indicatorCode = "SL.UEM.TOTL.ZS";
                    } else if (graphSelected == "Consumer Price Index(Inflation") {
                        indicatorCode = "FP.CPI.TOTL.ZG";
                    } else if (graphSelected == "Imports of Goods") {
                        indicatorCode = "NE.IMP.GNFS.ZS";
                    } else if (graphSelected == "Exports of Goods") {
                        indicatorCode = "NE.EXP.GNFS.ZS";
                    } else if (graphSelected == "Real Interest Rates") {
                        indicatorCode = "FR.INR.RINR";
                    } else if (graphSelected == "Tax Rates") {
                        indicatorCode = "IC.TAX.TOTL.CP.ZS";
                    }
                }
                try {
                    model = Networking.getInstance().getRangeOfIndicatorsForCountries(countriesSelectedList, indicatorCode, "1980", "2015");
                    if(countriesSelectedList.size() == 1) {

                        view.getLeftSide().setCenter(getOneCountryGraph());
                        btnSwitchGraph = new Button("Switch Graph Type");
                        btnSwitchGraph.getStyleClass().add("btn-success");

                        btnSwitchGraph.setAlignment(Pos.CENTER);
                        btnSwitchGraph.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if(view.getLeftSide().getCenter() instanceof LineChart) {
                                    view.getLeftSide().setCenter(getOneCountryGraphBarGraph());
                                } else {
                                    view.getLeftSide().setCenter(getOneCountryGraph());
                                }
                            }
                        });
                        view.getLeftSide().setBottom(btnSwitchGraph);
                      //  OneCountryOneIndicatorLineChart o = new OneCountryOneIndicatorLineChart(getOneCountryGraph());
                    } else if(countriesSelectedList.size() > 1) {
                        view.getLeftSide().setCenter(getMultiCountryGraph());
                  //      MultCountryMultYearOneIndBarChart o = new MultCountryMultYearOneIndBarChart(getMultiCountryGraph());
                    }
                } catch (Exception e) {

                }
            }
        });
    }


    public BarChart<String,Number> getOneCountryGraphBarGraph() {
        map = model;
        codesList = map.keySet();
        countryCode = (String) codesList.toArray()[0];
        firstYear = (String) map.get(countryCode).keySet().toArray()[0]; //1st year
        noOfYears = map.values().toArray()[0].toString().split("}").length;
        finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1); //last year
        indicator = map.get(countryCode).get(firstYear).getName();
        //  o.setTitle(countryCode + "'s " + indicator + " from " + firstYear + " until " + finalYear);
        final CategoryAxis xAxis = new CategoryAxis(); //to make categories (ie months in this case)
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel(indicator);
        //creating the chart
        final BarChart<String,Number> lineChart = new BarChart<String,Number>(xAxis,yAxis);
        lineChart.setTitle(indicator + countryCode);
        //defining a series
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName(countryCode);
        //populating the series with data
        for(int i= 0; i < noOfYears; ++i) {
            String year = (String) map.get(countryCode).keySet().toArray()[i];
            series.getData().add(new XYChart.Data(year, map.get(countryCode).get(year).getValue()));
        }
        lineChart.getData().add(series);
        return lineChart;
    }

    public LineChart<String,Number> getOneCountryGraph() {
        map = model;
        codesList = map.keySet();
        countryCode = (String) codesList.toArray()[0];
        firstYear = (String) map.get(countryCode).keySet().toArray()[0]; //1st year
        noOfYears = map.values().toArray()[0].toString().split("}").length;
        finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1); //last year
        indicator = map.get(countryCode).get(firstYear).getName();
      //  o.setTitle(countryCode + "'s " + indicator + " from " + firstYear + " until " + finalYear);
        final CategoryAxis xAxis = new CategoryAxis(); //to make categories (ie months in this case)
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel(indicator);
        //creating the chart
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        lineChart.setTitle(indicator + " in " + countryCode);
        //defining a series
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName(countryCode);
        //populating the series with data
        for(int i= 0; i < noOfYears; ++i) {
            String year = (String) map.get(countryCode).keySet().toArray()[i];
            series.getData().add(new XYChart.Data(year, map.get(countryCode).get(year).getValue()));
        }
        lineChart.getData().add(series);
        return lineChart;
    }

    public BarChart<String,Number> getMultiCountryGraph() {
        map = model;
        codesList = map.keySet();
        countryCode = (String) codesList.toArray()[0]; //just random countryCode
        firstYear = (String) map.get(countryCode).keySet().toArray()[0]; //1st year
        noOfYears = map.values().toArray()[0].toString().split("}").length;
        finalYear = Integer.toString(Integer.parseInt(firstYear) + noOfYears - 1);
        noOfCountries = codesList.size();
        indicator = map.get(countryCode).get(firstYear).getName();
        stageMain.setTitle(indicator + " from " + firstYear + " until " + finalYear);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
        barChart.setTitle("Country Summary");
        xAxis.setLabel("Country");
        yAxis.setLabel(indicator);
        for(int i= 0; i < noOfYears; ++i) {
            XYChart.Series series = new XYChart.Series();
            series.setName(Integer.toString(Integer.parseInt(firstYear)+i));
            for (int j = 0; j < noOfCountries; ++j) { //-----
                String countryCode = (String) codesList.toArray()[j];
                String year = Integer.toString(Integer.parseInt(firstYear)+i);
                series.getData().add(new XYChart.Data(countryCode, map.get(countryCode).get(year).getValue()));
            }
            barChart.getData().addAll(series);
        }
        return barChart;
    }

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

    public void billionairesDailyView() {
        ArrayList<Billionaire> billionaires = Networking.getInstance().getBillionairesRange(1);
        view.billionairesDailyView(billionaires.get(0));
    }
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

