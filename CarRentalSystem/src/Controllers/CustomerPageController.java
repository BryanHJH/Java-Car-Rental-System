package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class CustomerPageController implements Initializable {
    
    @FXML
    private ComboBox<String> carTypeCombo;

    @FXML
    private Label carTypeLabel;

    @FXML
    private Tab catalog;

    @FXML
    private Tab changePersonalInfo;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        carTypeCombo.setItems(FXCollections.observableArrayList("Hatchback", "Minivan", "Sedan", "SUV"));
    }
}