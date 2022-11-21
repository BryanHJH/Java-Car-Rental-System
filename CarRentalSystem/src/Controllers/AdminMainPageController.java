package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Class.Car;
import Class.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminMainPageController implements Initializable {

    // Car Table
    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<Car, String> plateNumberColumn, carBrandColumn;

    @FXML
    private TableColumn<Car, Integer> noSeatsColumn, rentPriceColumn, carryLoadColumn, comfortColumn;

    // Customer Table
    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> customerNameColumn, identificationCardColumn, emailColumn, usernameColumn, passwordColumn;

    @FXML
    private TextField searchCarTextField, searchCustomerTextField;

    @FXML
    private Button searchCarButton, clearButton, addCarButton, removeCarButton, logoutButton, viewHistoryButton, searchCustomerButton, resetPasswordButton, salesReportButton, revenueReportButton;

    private Stage stage;
    private Parent root;
    private Scene scene;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }

    public void searchCar(ActionEvent e) {

    }

    public void clear(ActionEvent e) {

    }

    public void addCar(ActionEvent e) {

    }

    public void removeCar(ActionEvent e) {

    }

    public void logout(ActionEvent e) {

    }

    public void searchCustomer(ActionEvent e) {

    }

    public void viewHistory(ActionEvent e) {

    }

    public void resetPassword(ActionEvent e) {

    }

    public void generateSalesReport(ActionEvent e) {

    }

    public void generateRevenueReport(ActionEvent e) {
        
    }
}
