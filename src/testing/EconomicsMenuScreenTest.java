package testing;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by arunitaroy on 10/12/2016.
 */
public class EconomicsMenuScreenTest {

    @Test
    public BorderPane setLeftPanelTest() throws Exception{
        BorderPane leftSide = new BorderPane();
        return leftSide;

    }

    @Test
    public VBox setRightPanelTest() throws Exception {
        VBox rightSide = new VBox();
        return rightSide;
    }

    @Test
    public void getGraphSettingsViewTest() throws Exception {
        CheckBox United_Kingdom = new CheckBox("United Kingdom");
        assertNull("United Kingdom", United_Kingdom);
        CheckBox United_States = new CheckBox("United States");
        assertNull("United States", United_States);
        CheckBox Romania = new CheckBox("Romania");
        assertNull("Romania", Romania);
        CheckBox Turkey = new CheckBox("Turkey");
        assertNull("Turkey", Turkey);
        CheckBox Switzerland = new CheckBox("Switzerland");
        assertNull("Switzerland", Switzerland);
        CheckBox Canada = new CheckBox("Canada");
        assertNull("Canada", Canada);
        CheckBox Germany = new CheckBox("Germany");
        assertNull("Germany", Germany);
        CheckBox China = new CheckBox("China");
        assertNull("China", China);
        CheckBox Japan = new CheckBox("Japan");
        assertNull("Japan", Japan);
        CheckBox Russia = new CheckBox("Russia");
        assertNull("Russia", Russia);
    }

    @Test
    public void getWolfram() throws Exception {

    }

    @Test
    public void setWolframQueryResults() throws Exception {

    }

    @Test
    public ComboBox getIndicatorSelection() throws Exception {
        ComboBox cbIndicators = new ComboBox();
        return cbIndicators;
    }

    @Test
    public TextField getTextSearch() throws Exception {
        TextField textSearch = new TextField();
        return textSearch;
    }

    @Test
    public void imagePopupWindowShow() throws Exception {

    }

    @Test
    public CheckBox getCheckBoxes() throws Exception {
        CheckBox checkBox = new CheckBox();
        return checkBox;
    }

    @Test
    public Button getGraphSearchBtn() throws Exception {
        Button btn = new Button();
        return btn;
    }

    @Test
    public Button getSearchBtn() throws Exception {
        Button getGraphSearchBtn = new Button();
        return getGraphSearchBtn;
    }

}