package Controllers;

import java.io.IOException;

import Class.Car;
import Class.Store;
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

    /**
     * Function name: receiveStoreData
     * @param event
     * @return Store
     * 
     * What it does: <br>
     *  1. Gets Store object from previous screen
     */
    private Store receiveStoreData(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Store store = (Store) stage.getUserData();
        return store;
    }

    /**
     * Function name: add
     * @param event
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Gets all the contents of TextFields <br>
     *  2. Create new Car object <br>
     *  3. Save the Car object to Car.txt
     */
    public void add(ActionEvent event) throws IOException {
        Store tmpStore = receiveStoreData(event);

        String plateNumber = plateNumberTextField.getText();
        String carBrand = carBrandTextField.getText();
        String carType = carTypeTextField.getText();
        RadioButton selectedButton = (RadioButton) carTransmissionGroup.getSelectedToggle();
        String carTransmission = selectedButton.getText().toUpperCase();
        int carSeats = Integer.parseInt(carSeatTextField.getText());
        int carPrice = Integer.parseInt(carPriceTextField.getText());

        Car newCar = new Car(plateNumber, carBrand, carSeats, carType, carPrice, carTransmission);
        tmpStore.addCar(newCar);

        cancel(event);
    }

    /**
     * Function name: cancel
     * @param event
     * @throws IOException
     * What it does: <br>
     *  1. Brings user back to the previous screen
     */
    public void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Pages/AdminMainPage.fxml"));
        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
