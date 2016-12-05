package root;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraphScreenController {

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

    private ObservableList<String> options =
            FXCollections.observableArrayList(
                    "GDP",
                    "Option 2",
                    "Option 3"
            );
    @FXML
    private final ComboBox listBoxIndicators = new ComboBox();

    @FXML
    private Button btnApply;

    @FXML
    private AnchorPane paneGraph;

    @FXML
    public void initialize() {
        assert spltPaneBody != null : "fx:id=\"spltPaneBody\" was not injected: check your FXML file 'GraphScreen.fxml'.";
        assert paneText != null : "fx:id=\"paneText\" was not injected: check your FXML file 'GraphScreen.fxml'.";
        assert paneIndicators != null : "fx:id=\"paneIndicators\" was not injected: check your FXML file 'GraphScreen.fxml'.";
        assert lblIndicators != null : "fx:id=\"LblIndicators\" was not injected: check your FXML file 'GraphScreen.fxml'.";
        assert listBoxIndicators != null : "fx:id=\"listBoxIndicators\" was not injected: check your FXML file 'GraphScreen.fxml'.";
//            listBoxIndicators.getItems().removeAll((Collection<?>) listBoxIndicators.getItems())
//            listBoxIndicators.getItems().addAll(options);

        listBoxIndicators.getItems().removeAll(listBoxIndicators.getItems());
        listBoxIndicators.getItems().addAll("Option A", "Option B", "Option C");
        listBoxIndicators.getSelectionModel().select("Option B");
        assert btnApply != null : "fx:id=\"btnApply\" was not injected: check your FXML file 'GraphScreen.fxml'.";
        assert paneGraph != null : "fx:id=\"paneGraph\" was not injected: check your FXML file 'GraphScreen.fxml'.";
    }
}
