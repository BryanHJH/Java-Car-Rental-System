package Controllers;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CustomerPageController implements Initializable {
    
    private int price = 150;
    private int finalPrice;

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
    private Button confirmBookingButton;

    @FXML
    private Button confirmChangesButton;

    @FXML
    private Label custContact;

    @FXML
    private TextField custContactTextField;

    @FXML
    private Label custEmailLabel;

    @FXML
    private TextField custEmailTextField;

    @FXML
    private Label custPasswordLabel;

    @FXML
    private TextField custPasswordTextField;

    @FXML
    private Label custUsernameLabel;

    @FXML
    private TextField custUsernameTextField;

    @FXML
    private Button logoutButton;

    @FXML
    private DatePicker rentDateDatePicker;

    @FXML
    private Label rentDateLabel;

    @FXML
    private DatePicker returnDateDatePicker;

    @FXML
    private Label returnDateLabel;

    @FXML
    public void listCars(ActionEvent event) {
        rentDateDatePicker.getEditor().clear();
        returnDateDatePicker.getEditor().clear();
        carPriceLabel1.setText("");
        
        if (carTypeCombo.getSelectionModel().getSelectedItem().equals("Hatchback")) {
            ObservableList<String> list = FXCollections.observableArrayList("Perodua Myvi", "Perodua Axia", "Proton Iriz", "Toyota Yaris");
            carNameCombo.setItems(list);
            carNameCombo.getSelectionModel().select("Perodua Myvi");
        }
        else if (carTypeCombo.getSelectionModel().getSelectedItem().equals("Minivan")) {
            ObservableList<String> list = FXCollections.observableArrayList("Toyota Hiace", "Proton Exora", "Toyota Inova", "Hyundai Starex");
            carNameCombo.setItems(list);
            carNameCombo.getSelectionModel().select("Toyota Hiace");
        }
        else if (carTypeCombo.getSelectionModel().getSelectedItem().equals("Sedan")) {
            ObservableList<String> list = FXCollections.observableArrayList("Perodua Bezza", "Honda City", "Toyota Camry", "Nissan Almera");
            carNameCombo.setItems(list);
            carNameCombo.getSelectionModel().select("Perodua Bezza");
        }
        else if (carTypeCombo.getSelectionModel().getSelectedItem().equals("SUV")) {
            ObservableList<String> list = FXCollections.observableArrayList("Proton X50", "Mazda CX-5", "Honda HR-V", "Nissan X-Trail");
            carNameCombo.setItems(list);
            carNameCombo.getSelectionModel().select("Proton X50");
        }
    }
    
    @FXML
    public void listSeat(ActionEvent event) {
        rentDateDatePicker.getEditor().clear();
        returnDateDatePicker.getEditor().clear();
        carPriceLabel1.setText("");

        String carName = carNameCombo.getSelectionModel().getSelectedItem();
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

    @FXML
    public void checkValidDate(ActionEvent event) throws NullPointerException{
        try {
            returnDateDatePicker.getEditor().clear();
            LocalDate rentDate = rentDateDatePicker.getValue();
            LocalDate today = LocalDate.now();
            long validDate = Duration.between(rentDate.atStartOfDay(), today.atStartOfDay()).toDays();

            if ((int)validDate > 0) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Rent date not valid");
                errorAlert.setContentText("Please select today or days after");
                errorAlert.showAndWait();

                rentDateDatePicker.getEditor().clear();
                carPriceLabel1.setText("");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void calculatePrice(ActionEvent event) throws NullPointerException{
        try {
            LocalDate rentDate = rentDateDatePicker.getValue();
            LocalDate returnDate = returnDateDatePicker.getValue();
            long daysBetween = Duration.between(rentDate.atStartOfDay(), returnDate.atStartOfDay()).toDays();

            if ((int)daysBetween <= 0) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Return date not valid");
                errorAlert.setContentText("Please select at least one day after the rent date");
                errorAlert.showAndWait();

                rentDateDatePicker.getEditor().clear();
                returnDateDatePicker.getEditor().clear();
                carPriceLabel1.setText("");
            }
            else {
                finalPrice = price*(int)daysBetween;
                carPriceLabel1.setText("RM " + String.valueOf(finalPrice));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void confirmBooking(ActionEvent event) {

    }
    
    @FXML
    public void confirmChanges(ActionEvent event) {

    }

    @FXML
    public void logout(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list = FXCollections.observableArrayList("Hatchback", "Minivan", "Sedan", "SUV");
        carTypeCombo.setItems(list);
        carTypeCombo.getSelectionModel().select(0);
        
        //Set default values to avoid empty fields
        ObservableList<String> list2 = FXCollections.observableArrayList("Perodua Myvi", "Perodua Axia", "Proton Iriz", "Toyota Yaris");
        carNameCombo.setItems(list2);
        carNameCombo.getSelectionModel().select(0);

        carSeatLabel1.setText(String.valueOf(5));
    }
}