package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class CustomerPageController implements Initializable {
    
    @FXML
    private ComboBox<String> carNameCombo;

    @FXML
    private Label carNameLabel;

    @FXML
    private Label carPriceLabel;

    @FXML
    private Label carPriceLabel1;

    @FXML
    private Label carSeatLabel;

    @FXML
    private Label carSeatLabel1;

    @FXML
    private ComboBox<String> carTypeCombo;

    @FXML
    private Label carTypeLabel;

    @FXML
    private Tab catalog;

    @FXML
    private Tab changePersonalInfo;

    @FXML
    private DatePicker rentDateDatePicker;

    @FXML
    private Label rentDateLabel;

    @FXML
    private DatePicker returnDateDatePicker;

    @FXML
    private Label returnDateLabel;

    @FXML
    void listCars(ActionEvent event) {
        String carType = carTypeCombo.getSelectionModel().getSelectedItem().toString();

        if (carType == "Hatchback") {
            ObservableList<String> list = FXCollections.observableArrayList("Perodua Myvi", "Perodua Axia", "Proton Iriz", "Toyota Yaris");
            carNameCombo.setItems(list);
            carNameCombo.getSelectionModel().selectFirst();
        }
        else if (carType == "Minivan") {
            ObservableList<String> list = FXCollections.observableArrayList("Toyota Hiace", "Proton Exora", "Toyota Inova", "Hyundai Starex");
            carNameCombo.setItems(list);
            carNameCombo.getSelectionModel().selectFirst();
        }
        else if (carType == "Sedan") {
            ObservableList<String> list = FXCollections.observableArrayList("Perodua Bezza", "Honda City", "Toyota Camry", "Nissan Almera");
            carNameCombo.setItems(list);
            carNameCombo.getSelectionModel().selectFirst();
        }
        else if (carType == "SUV") {
            ObservableList<String> list = FXCollections.observableArrayList("Proton X50", "Mazda CX-5", "Honda HR-V", "Nissan X-Trail");
            carNameCombo.setItems(list);
            carNameCombo.getSelectionModel().selectFirst();
        }
    }
    
    @FXML
    void listSeat(ActionEvent event) {
        String carName = carNameCombo.getSelectionModel().getSelectedItem().toString();
        int price = 150;
        int seat = 5;

        if (carName == "Perodua Myvi") {
            price = 150;
        }
        else if (carName == "Perodua Axia") {
            price = 140;
        }
        else if (carName == "Proton Iriz") {
            price = 170;
        }
        else if (carName == "Toyota Yaris") {
            price = 200;
        }
        else if (carName == "Toyota Hiace") {
            price = 450;
            seat = 10;
        }
        else if (carName == "Proton Exora") {
            price = 370;
            seat = 7;
        }
        else if (carName == "Toyota Inova") {
            price = 400;
            seat = 7;
        }
        else if (carName == "Hyundai Starex") {
            price = 500;
            seat = 8;
        }
        else if (carName == "Perodua Bezza") {
            price = 200;
        }
        else if (carName == "Honda City") {
            price = 260;
        }
        else if (carName == "Toyota Camry") {
            price = 320;
        }
        else if (carName == "Nissan Almera") {
            price = 250;
        }
        else if (carName == "Proton X-50") {
            price = 350;
        }
        else if (carName == "Mazada CX-5") {
            price = 500;
        }
        else if (carName == "Honda CR-V") {
            price = 390;
        }
        else if (carName == "Nissan X-Trail") {
            price = 370;
        }

        carSeatLabel1.setText(String.valueOf(seat));
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list = FXCollections.observableArrayList("Hatchback", "Minivan", "Sedan", "SUV");
        carTypeCombo.setItems(list);
        carTypeCombo.getSelectionModel().selectFirst();
        
        //Set default values to avoid emptt fields
        ObservableList<String> list2 = FXCollections.observableArrayList("Perodua Myvi", "Perodua Axia", "Proton Iriz", "Toyota Yaris");
        carNameCombo.setItems(list2);
        carNameCombo.getSelectionModel().selectFirst();

        carSeatLabel1.setText(String.valueOf(5));
    }
}