package app_launcher;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controllers.EconomicsGraphDataController;
import views.EconomicsMainView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main application that will be compiled and executed through command line
 * @author Jaiten
 */
public class MainApp extends Application {

    /**
     * start the game
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        EconomicsMainView view = new EconomicsMainView(primaryStage);

        EconomicsGraphDataController o = new EconomicsGraphDataController(view);
    }

    public MainApp() {}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}