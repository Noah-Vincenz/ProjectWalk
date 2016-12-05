package root;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class graphScreenController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private SplitPane spltPaneBody;

    @FXML
    private AnchorPane paneText;

    @FXML
    private AnchorPane paneIndicators;

    @FXML
    private Label lblIndicators;

    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "GDP",
                    "Option 2",
                    "Option 3"
            );
    @FXML
    private ComboBox<?> listBoxIndicators =new ComboBox(options)  ;

    @FXML
    private Button btnApply;

    @FXML
    private AnchorPane paneGraph;

    @FXML
    public void initialize() {
        assert spltPaneBody != null : "fx:id=\"spltPaneBody\" was not injected: check your FXML file 'graphScreen.fxml'.";
        assert paneText != null : "fx:id=\"paneText\" was not injected: check your FXML file 'graphScreen.fxml'.";
        assert paneIndicators != null : "fx:id=\"paneIndicators\" was not injected: check your FXML file 'graphScreen.fxml'.";
        assert lblIndicators != null : "fx:id=\"LblIndicators\" was not injected: check your FXML file 'graphScreen.fxml'.";
        assert listBoxIndicators != null : "fx:id=\"listBoxIndicators\" was not injected: check your FXML file 'graphScreen.fxml'.";
//            listBoxIndicators.getItems().removeAll((Collection<?>) listBoxIndicators.getItems())
           // listBoxIndicators.getItems().addAll(options);
        assert btnApply != null : "fx:id=\"btnApply\" was not injected: check your FXML file 'graphScreen.fxml'.";
        assert paneGraph != null : "fx:id=\"paneGraph\" was not injected: check your FXML file 'graphScreen.fxml'.";

    }
}
