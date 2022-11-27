package Controllers;

import java.io.IOException;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import Class.Admin;
import Class.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AdminAddCarPageController {
    
    @FXML
    private TextField plateNumberTextField, carBrandTextField, carTypeTextField, carSeatTextField, carPriceTextField;

    @FXML
    private RadioButton autoRadioButton, manualRadioButton;

    @FXML
    private ToggleGroup carTransmissionGroup;

    @FXML
    private Button addButton, cancelButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Admin receiveAdminData(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Admin admin = (Admin) stage.getUserData();
        return admin;
    }

    public void add(ActionEvent event) throws IOException {

        Admin tmpAdmin = receiveAdminData(event);

        String plateNumber = plateNumberTextField.getText();
        String carBrand = carBrandTextField.getText();
        String carType = carTypeTextField.getText();
        RadioButton selectedButton = (RadioButton) carTransmissionGroup.getSelectedToggle();
        String carTransmission = selectedButton.getText().toUpperCase();
        int carSeats = Integer.parseInt(carSeatTextField.getText());
        int carPrice = Integer.parseInt(carPriceTextField.getText());

        Car newCar = new Car(plateNumber, carBrand, carSeats, carType, carPrice, carTransmission);
        


        Parent root = FXMLLoader.load(getClass().getResource("/Pages/AdminMainPage.fxml"));
        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cancel(ActionEvent event) {

    }

}
