package root;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.Random;

import javafx.scene.Node;
import javafx.stage.Stage;


public class startScreenController {
    @FXML
    private Pane pane;
    @FXML
    private Button btn_Login;
    @FXML
    private Button btn_Cancel;
// Event Listener on Button[#button1].onAction
    /**
     * This is the ActionEvent method to change the screen to fxml which shows screen of all the units
     * Else 1st Unit screen
     * @param event
     * @throws IOException
     */
    @FXML
    private void setBtn_Login(ActionEvent event) throws IOException {

        Parent homepage=FXMLLoader.load(getClass().getResource("fxml.fxml"));
        Scene homepageScene=new Scene (homepage);
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(homepageScene);
        stage.show();

    }

    /**
     * This is the ActionEvent method to change the screen to fxml which shows who is the beginner method. If we choose two players button, two players mode begin.
     * @param event
     * @throws IOException
     */
    @FXML
    private void setBtn_Cancel(ActionEvent event) throws IOException {
        Parent homepage=FXMLLoader.load(getClass().getResource("fxml.fxml"));
        Scene homepageScene=new Scene (homepage);
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(homepageScene);
        stage.show();
    }
    /**
     * The method to return the int n.
     * @return
     */

};
