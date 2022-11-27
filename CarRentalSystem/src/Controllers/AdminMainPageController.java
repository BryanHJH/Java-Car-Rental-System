package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.Action;

import Class.Booking;
import Class.Car;
import Class.Customer;
import Class.Store;
import Class.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.NumberStringConverter;

public class AdminMainPageController implements Initializable {

    // Car Table
    @FXML
    private TableView<Car> carTable;

    @FXML
    TableColumn<Car, String> plateNumberColumn, carBrandColumn, carTypeColumn, carTransmissionColumn;

    @FXML
    TableColumn<Car, Integer> noSeatsColumn, rentPriceColumn;

    // Customer Table
    @FXML
    private TableView<User> customerTable;

    @FXML
    TableColumn<User, String> customerNameColumn, identificationCardColumn, contactColumn, emailColumn, usernameColumn, passwordColumn;

    // Booking Table
    @FXML
    private TableView<Booking> bookingTable;

    @FXML
    TableColumn<Booking, String> bookingTypeColumn, bookingStatusColumn, custEmailColumn, custIDColumn, carPlateColumn;

    @FXML
    TableColumn<Booking, Long> bookingIDColumn;

    @FXML
    TableColumn<Booking, LocalDate> bookingStartColumn, bookingEndColumn;

    @FXML
    TableColumn<Booking, Integer> totalPriceColumn;

    // Search Text Fields
    @FXML
    private TextField searchCarTextField, searchCustomerTextField, searchBookingTextField;

    @FXML
    private Button addCarButton, approveButton, clearButton, logoutButton, rejectButton, removeCarButton, searchBookingButton, searchCarButton, searchCustomerButton, salesReportButton, revenueReportButton;

    private Stage stage;
    private Parent root;
    private Scene scene;

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");
    static File bookingFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Booking.txt");


    Store store = new Store(adminFile, customerFile, carFile, bookingFile);

    /** public void setEditableTable(TableView table, String tableType, boolean editable) {
        table.setEditable(editable);
        table.setPlaceholder(new Label("No " + tableType + " records to display"));
    } **/
    
    /** public void initializeTableColumn(TableColumn carBrandColumn, String variableName) {
        tblCol.setCellValueFactory(new PropertyValueFactory<>(variableName));
        tblCol.setCellFactory(TextFieldTableCell.forTableColumn());
    } **/

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        // Car Table
        carTable.setEditable(true);

        // Setting the columns to be editable and populating the Car table
        carTable.setPlaceholder(new Label("No car records to display"));

        plateNumberColumn.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));

        carBrandColumn.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        carBrandColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        carBrandColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Car,String>>() {

            @Override
            public void handle(CellEditEvent<Car, String> event) {
                Car tmpCar = carTable.getSelectionModel().getSelectedItem();
                String newCarBrand = event.getNewValue();

                ArrayList<Car> carList = store.getCars();

                try {
                    for (Car car: carList) {
                        if (car.getPlateNumber().equals(tmpCar.getPlateNumber())) {
                            car.setCarBrand(newCarBrand);
                            break;
                        }
                    }
                    Store.saveCars(carFile, carList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
        });

        carTypeColumn.setCellValueFactory(new PropertyValueFactory<>("carType"));
        carTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        carTypeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Car,String>>() {

            @Override
            public void handle(CellEditEvent<Car, String> event) {
                Car tmpCar = carTable.getSelectionModel().getSelectedItem();
                String newCarType = event.getNewValue();

                ArrayList<Car> carList = store.getCars();

                try {
                    for (Car car: carList) {
                        if (car.getPlateNumber().equals(tmpCar.getPlateNumber())) {
                            car.setCarType(newCarType);
                            break;
                        }
                    }
                    Store.saveCars(carFile, carList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        });

        noSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("carSeat"));
        noSeatsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        noSeatsColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Car,Integer>>() {
            @Override
            public void handle(CellEditEvent<Car, Integer> event) {
                Car tmpCar = carTable.getSelectionModel().getSelectedItem();
                Integer newCarSeat = event.getNewValue();

                ArrayList<Car> carList = store.getCars();

                try {
                    for (Car car: carList) {
                        if (car.getPlateNumber().equals(tmpCar.getPlateNumber())) {
                            car.setCarSeat(newCarSeat);
                            break;
                        }
                    }
                    Store.saveCars(carFile, carList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        });

        rentPriceColumn.setCellValueFactory(new PropertyValueFactory<>("carPrice"));
        rentPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        rentPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Car,Integer>>() {

            @Override
            public void handle(CellEditEvent<Car, Integer> event) {
                Car tmpCar = carTable.getSelectionModel().getSelectedItem();
                Integer newCarPrice = event.getNewValue();

                ArrayList<Car> carList = store.getCars();

                try {
                    for (Car car: carList) {
                        if (car.getPlateNumber().equals(tmpCar.getPlateNumber())) {
                            car.setCarPrice(newCarPrice);
                            break;
                        }
                    }
                    Store.saveCars(carFile, carList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        });

        carTransmissionColumn.setCellValueFactory(new PropertyValueFactory<>("carTransmission"));
        carTransmissionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        carTransmissionColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Car,String>>() {

            @Override
            public void handle(CellEditEvent<Car, String> event) {
                Car tmpCar = carTable.getSelectionModel().getSelectedItem();
                String newCarTransmission = event.getNewValue();

                ArrayList<Car> carList = store.getCars();

                try {
                    for (Car car: carList) {
                        if (car.getPlateNumber().equals(tmpCar.getPlateNumber())) {
                            car.setCarTransmission(newCarTransmission);
                            break;
                        }
                    }
                    Store.saveCars(carFile, carList);
                } catch (IOException e) {
                    e.printStackTrace();
                }                
            }
            
        });
        
        List<Car> carList = store.getCars();
        ObservableList<Car> obsCarList = FXCollections.observableArrayList(carList);
        carTable.setItems(obsCarList);



        
        // Customer Table
        customerTable.setEditable(true);
        customerTable.setPlaceholder(new Label("No customer records to display"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        customerNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        identificationCardColumn.setCellValueFactory(new PropertyValueFactory<>("identification"));
        identificationCardColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        contactColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        List<User> customerList = store.getCustomers();
        ObservableList<User> obsCustList = FXCollections.observableArrayList(customerList);
        customerTable.setItems(obsCustList);

        // Booking Table
        bookingIDColumn.setCellValueFactory(new PropertyValueFactory<>("identifier"));
        bookingTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bookingType"));
        bookingStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));
        custEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        custIDColumn.setCellValueFactory(new PropertyValueFactory<>("identification"));
        carPlateColumn.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
        bookingStartColumn.setCellValueFactory(new PropertyValueFactory<>("bookingStart"));
        bookingEndColumn.setCellValueFactory(new PropertyValueFactory<>("bookingEnd"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        List<Booking> bookingList = store.getBookings();
        ObservableList<Booking> obsBookingList = FXCollections.observableArrayList(bookingList);
        bookingTable.setItems(obsBookingList);
        

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

    public void searchBooking(ActionEvent e) {

    }

    public void approve(ActionEvent e) {

    }

    public void reject(ActionEvent e) {

    }

    public void generateSalesReport(ActionEvent e) {

    }

    public void generateRevenueReport(ActionEvent e) {
        
    }
}
