package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Class.Admin;
import Class.Booking;
import Class.Car;
import Class.Store;
import Class.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

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
    TableColumn<Booking, LocalDate> bookingStartColumn, bookingEndColumn;

    @FXML
    TableColumn<Booking, Integer> totalPriceColumn;

    // Search Text Fields
    @FXML
    private TextField searchCarTextField, searchCustomerTextField, searchBookingTextField;

    @FXML
    private Button addCarButton, approveButton, clearButton, logoutButton, rejectButton, removeCarButton, searchBookingButton, searchCarButton, searchCustomerButton, salesReportButton, revenueReportButton;

    @FXML
    private Label adminLabelCar, adminLabelCustomer, adminLabelBooking, adminLabelReport;

    private Stage stage;
    private Parent root;
    private Scene scene;

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");
    static File bookingFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Booking.txt");

    Store store = new Store(adminFile, customerFile, carFile, bookingFile);

    private Admin receiveAdminData(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Admin admin = (Admin) stage.getUserData();
        return admin;
    }

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
        customerNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User,String>>() {

            @Override
            public void handle(CellEditEvent<User, String> event) {
                User tmpCustomer = customerTable.getSelectionModel().getSelectedItem();
                String newCustomerName = event.getNewValue();

                ArrayList<User> customerList = store.getCustomers();

                try {
                    for (User customer: customerList) {
                        if (customer.getIdentification().equals(tmpCustomer.getIdentification())) {
                            customer.setFullname(newCustomerName);
                            break;
                        }
                    }
                    Store.saveUsers(customerFile, customerList);
                } catch (IOException e) {
                    e.printStackTrace();
                }                
            }
            
        });

        identificationCardColumn.setCellValueFactory(new PropertyValueFactory<>("identification"));

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User,String>>() {

            @Override
            public void handle(CellEditEvent<User, String> event) {
                User tmpCustomer = customerTable.getSelectionModel().getSelectedItem();
                String newCustomerEmail = event.getNewValue();

                ArrayList<User> customerList = store.getCustomers();

                try {
                    for (User customer: customerList) {
                        if (customer.getIdentification().equals(tmpCustomer.getIdentification())) {
                            customer.setEmail(newCustomerEmail);
                            break;
                        }
                    }
                    Store.saveUsers(customerFile, customerList);
                } catch (IOException e) {
                    e.printStackTrace();
                }     
            }
            
        });

        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        contactColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        contactColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User,String>>() {

            @Override
            public void handle(CellEditEvent<User, String> event) {
                User tmpCustomer = customerTable.getSelectionModel().getSelectedItem();
                String newCustomerContact = event.getNewValue();

                ArrayList<User> customerList = store.getCustomers();

                try {
                    for (User customer: customerList) {
                        if (customer.getIdentification().equals(tmpCustomer.getIdentification())) {
                            customer.setContact(newCustomerContact);
                            break;
                        }
                    }
                    Store.saveUsers(customerFile, customerList);
                } catch (IOException e) {
                    e.printStackTrace();
                }                     
            }
            
        });

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usernameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User,String>>() {

            @Override
            public void handle(CellEditEvent<User, String> event) {
                User tmpCustomer = customerTable.getSelectionModel().getSelectedItem();
                String newCustomerUsername = event.getNewValue();

                ArrayList<User> customerList = store.getCustomers();

                try {
                    for (User customer: customerList) {
                        if (customer.getIdentification().equals(tmpCustomer.getIdentification())) {
                            customer.setUsername(newCustomerUsername);
                            break;
                        }
                    }
                    Store.saveUsers(customerFile, customerList);
                } catch (IOException e) {
                    e.printStackTrace();
                }     
            }
            
        });

        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        List<User> customerList = store.getCustomers();
        ObservableList<User> obsCustList = FXCollections.observableArrayList(customerList);
        customerTable.setItems(obsCustList);

        // Booking Table
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

    public void logout(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Pages/LoginPage.fxml"));
        stage =  (Stage)((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchCar(ActionEvent e) {
        ArrayList<Car> tmpCars = store.getCars();
        ArrayList<Car> searchedCars = new ArrayList<>();
        String searchedCar = searchCarTextField.getText().toLowerCase().trim();

        for (Car car: tmpCars) {
            if (car.getPlateNumber().toLowerCase().trim().equals(searchedCar) || 
                car.getCarBrand().toLowerCase().trim().equals(searchedCar) || 
                car.getCarType().toLowerCase().trim().equals(searchedCar)) {
                    searchedCars.add(car);
                }
        }

        carTable.getItems().removeAll(tmpCars);
        carTable.getItems().addAll(searchedCars);
    }

    public void clearCar(ActionEvent e) {
        searchCarTextField.clear();
        ArrayList<Car> tmpCars = store.getCars();
        carTable.getItems().clear();
        carTable.getItems().addAll(tmpCars);
    }

    public void addCar(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/Pages/AdminAddCarPage.fxml"));
        stage.setUserData(store);
        stage =  (Stage)((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        clearCar(e);
    }

    public void removeCar(ActionEvent e) throws IOException {
        Car tmpCar = carTable.getSelectionModel().getSelectedItem();
        store.removeCar(tmpCar);
        clearCar(e);
    }

    public void searchCustomer(ActionEvent e) {
        ArrayList<User> tmpCustomers = store.getCustomers();
        ArrayList<User> searchedCustomers = new ArrayList<>();
        String searchedCustomer = searchCustomerTextField.getText().toLowerCase().trim();

        for (User customer: tmpCustomers) {
            if (customer.getFullname().toLowerCase().trim().equals(searchedCustomer) || 
                customer.getUsername().toLowerCase().trim().equals(searchedCustomer) || 
                customer.getEmail().toLowerCase().trim().equals(searchedCustomer)) {
                    searchedCustomers.add(customer);
                }
        }

        customerTable.getItems().removeAll(tmpCustomers);
        customerTable.getItems().addAll(searchedCustomers);
    }

    public void clearCustomer(ActionEvent e) {
        searchCustomerTextField.clear();
        ArrayList<User> tmpCustomers = store.getCustomers();
        customerTable.getItems().clear();
        customerTable.getItems().addAll(tmpCustomers);
    }

    public void searchBooking(ActionEvent e) {
        ArrayList<Booking> tmpBookings = store.getBookings();
        ArrayList<Booking> searchedBookings = new ArrayList<>();
        String searchedBooking = searchBookingTextField.getText().toLowerCase().trim();

        for (Booking booking: tmpBookings) {
            if (booking.getBookingType().toLowerCase().trim().equals(searchedBooking) ||
                booking.getBookingStatus().toLowerCase().trim().equals(searchedBooking) ||
                booking.getEmail().toLowerCase().trim().equals(searchedBooking) ||
                booking.getPlateNumber().toLowerCase().trim().equals(searchedBooking) ||
                booking.getIdentification().toLowerCase().trim().equals(searchedBooking)) {
                    searchedBookings.add(booking);
                }
        }

        bookingTable.getItems().removeAll(tmpBookings);
        bookingTable.getItems().addAll(searchedBookings);
    }

    public void clearBooking(ActionEvent e) {
        searchBookingTextField.clear();
        ArrayList<Booking> tmpBookings = store.getBookings();
        bookingTable.getItems().clear();
        bookingTable.getItems().addAll(tmpBookings);
    }

    public void approve(ActionEvent e) throws IllegalAccessException, IOException {
        Admin tmpAdmin = receiveAdminData(e);
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
        ArrayList<Booking> oldBookings = store.getBookings();
        ArrayList<Booking> updatedBookings = new ArrayList<>();

        if (selectedBooking.getBookingStatus().toLowerCase().trim().equals("rejected")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Rejected bookings cannot be modified!");
            alert.show();
        } else {
            
            switch (selectedBooking.getBookingType().toLowerCase().trim()) {
                case "booking": 
                    selectedBooking = tmpAdmin.approve(selectedBooking, true);
                    break;
    
                case "return": 
                    selectedBooking = tmpAdmin.approveReturn(selectedBooking, true);
                    break;
            }
    
            for (Booking booking: oldBookings) {
                if (booking.getEmail().equals(selectedBooking.getEmail()) &&
                    booking.getPlateNumber().equals(selectedBooking.getPlateNumber()) &&
                    booking.getBookingStart().isEqual(selectedBooking.getBookingStart())) {
                    updatedBookings.add(selectedBooking);
                    continue;
                } else {
                    updatedBookings.add(booking);
                }
            }
    
            Store.saveBookings(bookingFile, updatedBookings);
            clearBooking(e);

        }

    }

    public void reject(ActionEvent e) throws IllegalAccessException, IOException {
        Admin tmpAdmin = receiveAdminData(e);
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
        ArrayList<Booking> oldBookings = store.getBookings();
        ArrayList<Booking> updatedBookings = new ArrayList<>();

        if (selectedBooking.getBookingStatus().toLowerCase().trim().equals("approved") ||
            selectedBooking.getBookingStatus().toLowerCase().trim().equals("returned") ||
            selectedBooking.getBookingStatus().toLowerCase().trim().equals("paid")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Approved bookings cannot be modified!");
            alert.show();
        } else {
            
            switch (selectedBooking.getBookingType().toLowerCase().trim()) {
                case "booking":
                    selectedBooking = tmpAdmin.approve(selectedBooking, false);
                    break;
                case "return":
                    selectedBooking = tmpAdmin.approveReturn(selectedBooking, false);
                    break;
            }
    
            for (Booking booking: oldBookings) {
                if (booking.getEmail().equals(selectedBooking.getEmail()) &&
                    booking.getPlateNumber().equals(selectedBooking.getPlateNumber()) &&
                    booking.getBookingStart().isEqual(selectedBooking.getBookingStart())) {
                    updatedBookings.add(selectedBooking);
                    continue;
                } else {
                    updatedBookings.add(booking);
                }
            }
    
            Store.saveBookings(bookingFile, updatedBookings);
            clearBooking(e);

        }

    }

    public void generateSalesReport(ActionEvent e) {

    }

    public void generateRevenueReport(ActionEvent e) {
        
    }
}
