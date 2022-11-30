package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Class.Booking;
import Class.Car;
import Class.Customer;
import Class.Store;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerPageController implements Initializable {

    @FXML
    private ToggleGroup carTransmissionGroup;

    @FXML
    private RadioButton manualRadioButton, autoRadioButton;

    @FXML
    private Label welcomeMessage;

    @FXML
    private TextField seatTextField, rentalDurationTextField, custUsernameTextField, custEmailTextField, custContactTextField;

    @FXML
    private PasswordField custPasswordField;

    @FXML
    private TableView<Booking> bookingTable;

    @FXML
    private TableColumn<Booking, String> bookingTypeColumn, bookingStatusColumn, custEmailColumn, custIDColumn, carPlateColumn;

    @FXML
    private TableColumn<Booking, LocalDate> bookingStartColumn, bookingEndColumn;

    @FXML
    private TableColumn<Booking, Integer> totalPriceColumn;

    @FXML
    private DatePicker rentDateDatePicker;

    @FXML
    private ComboBox<String> carTypeCombo, carBrandCombo, carPlateCombo;

    @FXML
    private Button logoutButton, confirmChangesButton, clearButton, confirmBookingButton, returnButton;

    private Stage stage;
    private Parent root;
    private Scene scene;

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");
    static File bookingFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Booking.txt");

    Store store = new Store(adminFile, customerFile, carFile, bookingFile);
    ArrayList<String> availableCarBrands = new ArrayList<>();
    ArrayList<String> availableCarPlates = new ArrayList<>();
    ArrayList<String> availableCarTypes = new ArrayList<>();
    ArrayList<LocalDate> bookedDates = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
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
        bookingTable.getItems().addAll(obsBookingList);
        
        // Building the initial Combo Boxes
        rentDateDatePicker.setValue(LocalDate.now());
        ArrayList<Car> carList = store.getCars();

        for (Car car: carList) {
            if (car.checkAvailability(rentDateDatePicker.getValue())) {
                availableCarBrands.add(car.getCarBrand());
                availableCarPlates.add(car.getPlateNumber());
                availableCarTypes.add(car.getCarType());
                continue;
            }
        }
        
        carBrandCombo.getItems().addAll(availableCarBrands);
        carPlateCombo.getItems().addAll(availableCarPlates);
        carTypeCombo.getItems().addAll(availableCarTypes);


        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                clearButton.fire();
            }
            
        });
    }

    public static Customer receiveCustomerData(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Customer customer = (Customer) stage.getUserData();
        return customer;
    }

    public void displayWelcomeMessage(String username) {
        welcomeMessage.setText("Welcome, " + username);
    }

    public ArrayList<Car> validateCarAvailability() {

        LocalDate bookingEndDate = rentDateDatePicker.getValue().plusDays((long) Integer.parseInt(rentalDurationTextField.getText()));
        bookedDates = (ArrayList<LocalDate>) rentDateDatePicker.getValue().datesUntil(bookingEndDate).collect(Collectors.toList());
        bookedDates.add(bookingEndDate);

        ArrayList<Car> carList = store.getCars();
        ArrayList<Car> availableCars = new ArrayList<>();

        for (Car car: carList) {
            for (LocalDate bookingDate: bookedDates) {
                if (car.checkAvailability(bookingDate)) {
                    availableCars.add(car);
                } else {
                    availableCars.remove(car);
                    break;
                }
                continue;
            }
        }

        return availableCars;

    }

    public void buildTypeComboBox() {

        ArrayList<Car> availableCars = validateCarAvailability();
        RadioButton selectedRadio = (RadioButton) carTransmissionGroup.getSelectedToggle();
        String carTransmission = selectedRadio.getText().toUpperCase();

        availableCarTypes.clear();
        
        for (Car car: availableCars) {
            if (car.getCarTransmission().toUpperCase().equals(carTransmission)) {
                availableCarTypes.add(car.getCarType());
            }
        }

        HashSet<String> uniqueCarTypes = new HashSet<String>(availableCarTypes);

        carTypeCombo.getItems().setAll(uniqueCarTypes);

    }

    public void buildBrandComboBox() {

        ArrayList<Car> availableCars = validateCarAvailability();
        String carType = carTypeCombo.getValue().toLowerCase().trim();
        availableCarBrands.clear();

        for (Car car: availableCars) {
            if (car.getCarType().toLowerCase().trim().equals(carType)) {
                availableCarBrands.add(car.getCarBrand());
            }
        }

        HashSet<String> uniqueCarBrands = new HashSet<String>(availableCarBrands);


        carBrandCombo.getItems().setAll(uniqueCarBrands);

    }

    public void buildPlateComboBox() {
        ArrayList<Car> availableCars = validateCarAvailability();
        String carBrand = carBrandCombo.getValue().toLowerCase().trim();
        String carType = carTypeCombo.getValue().toLowerCase().trim();
        availableCarPlates.clear();

        for (Car car: availableCars) {
            if (car.getCarBrand().toLowerCase().trim().equals(carBrand) &&
                car.getCarType().toLowerCase().trim().equals(carType)) {
                availableCarPlates.add(car.getPlateNumber());
            }
        }

        HashSet<String> uniqueCarPlates = new HashSet<String>(availableCarPlates);

        carPlateCombo.getItems().setAll(uniqueCarPlates);
    }

    public void setSeatTextField() {
        ArrayList<Car> carList = store.getCars();
        String carPlate = carPlateCombo.getValue().toLowerCase().trim();

        for (Car car: carList) {
            if (car.getPlateNumber().toLowerCase().trim().equals(carPlate)) {
                seatTextField.setText(Integer.toString(car.getCarSeat()));
            }
        }
    }
    
    public void confirmBooking(ActionEvent event) throws IOException {

        Customer tmpCustomer = receiveCustomerData(event);

        LocalDate bookingStart = rentDateDatePicker.getValue();
        LocalDate bookingEnd = bookingStart.plusDays(Integer.parseInt(rentalDurationTextField.getText()));
        
        String carPlate = carPlateCombo.getValue();

        String custEmail = tmpCustomer.getEmail();
        String custId = tmpCustomer.getIdentification();

        Booking newBooking = new Booking("Booking", "Pending", custEmail, custId, carPlate, bookingStart, bookingEnd);
        store.addBooking(newBooking);
        clear(event);


    }
    
    public void confirmChanges(ActionEvent event) {

    }
    
    public void returnCar(ActionEvent event) {

    }
    

    public void clear(ActionEvent event) {

        // Clearing the Booking fields
        rentDateDatePicker.setValue(LocalDate.now());
        rentalDurationTextField.setText("0");
        carTypeCombo.getSelectionModel().clearSelection();
        carBrandCombo.getSelectionModel().clearSelection();
        carPlateCombo.getSelectionModel().clearSelection();
        seatTextField.clear();

        // Initializing the Booking table
        Customer tmpCustomer = receiveCustomerData(event);

        displayWelcomeMessage(tmpCustomer.getFullname());

        ArrayList<Booking> bookingList = store.getBookings();
        ArrayList<Booking> customerBookings = new ArrayList<>();
        for (Booking booking: bookingList) {
            if (booking.getEmail().toLowerCase().trim().equals(tmpCustomer.getEmail())) {
                customerBookings.add(booking);
            }
        }

        bookingTable.getItems().setAll(customerBookings);

        // Initializing the Profile page
        custUsernameTextField.setText(tmpCustomer.getUsername());
        custPasswordField.setText(tmpCustomer.getPassword());
        custEmailTextField.setText(tmpCustomer.getEmail());
        custContactTextField.setText(tmpCustomer.getContact());

    }

    public void logout(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Pages/LoginPage.fxml"));
        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}