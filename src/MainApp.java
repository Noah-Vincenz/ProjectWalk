/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import views.EconomicsMenuScreen;
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
        EconomicsMenuScreen o = new EconomicsMenuScreen(primaryStage);
    }

    public MainApp() {


    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}