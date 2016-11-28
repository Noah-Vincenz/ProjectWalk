package root;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Controller {
    /**
     * Sample Skeleton for 'startScreen.fxml' Controller Class
     */

        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="anchorPane"
        private AnchorPane anchorPane; // Value injected by FXMLLoader

        @FXML // fx:id="background_pane"
        private Pane background_pane; // Value injected by FXMLLoader

        @FXML // fx:id="label_Name"
        private Label label_Name; // Value injected by FXMLLoader

        @FXML // fx:id="txtName"
        private TextField txtName; // Value injected by FXMLLoader

        @FXML // fx:id="pwdField_Password"
        private PasswordField pwdField_Password; // Value injected by FXMLLoader

        @FXML // fx:id="btn_Login"
        private Button btn_Login; // Value injected by FXMLLoader

        @FXML // fx:id="btn_Cancel"
        private Button btn_Cancel; // Value injected by FXMLLoader

        @FXML // This method is called by the FXMLLoader when initialization is complete


        void initialize() {
            assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'LoginScreenFinal.fxml'.";
            assert background_pane != null : "fx:id=\"background_pane\" was not injected: check your FXML file 'LoginScreenFinal.fxml'.";
            assert label_Name != null : "fx:id=\"label_Name\" was not injected: check your FXML file 'LoginScreenFinal.fxml'.";
            assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'LoginScreenFinal.fxml'.";
            assert pwdField_Password != null : "fx:id=\"pwdField_Password\" was not injected: check your FXML file 'LoginScreenFinal.fxml'.";
            assert btn_Login != null : "fx:id=\"btn_Login\" was not injected: check your FXML file 'LoginScreenFinal.fxml'.";
            assert btn_Cancel != null : "fx:id=\"btn_Cancel\" was not injected: check your FXML file 'LoginScreenFinal.fxml'.";

        }
    }

