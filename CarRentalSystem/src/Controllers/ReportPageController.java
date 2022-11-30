package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Class.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReportPageController {
    @FXML
    private Label reportLabel;

    @FXML
    private PieChart reportPieChart;

    // public Store receiveStoreDate(ActionEvent e) {
    //     Node node = (Node) e.getSource();
    //     Stage stage = (Stage) node.getScene().getWindow();
    //     Store store = (Store) stage.getUserData();
    //     return store;
    // }

}
