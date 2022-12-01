package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import Class.Admin;
import Class.Booking;
import Class.Car;
import Class.Log;
import Class.Store;
import Class.User;
import javafx.application.Platform;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
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
    private Button addCarButton, approveButton, clearCarButton, clearCustomerButton, clearBookingButton, logoutButton, rejectButton, removeCarButton, searchBookingButton, searchCarButton, searchCustomerButton, refreshChartButton;

    @FXML
    private PieChart chargesPieChart, salesPieChart;

    @FXML
    private Label adminLabelCar, adminLabelCustomer, adminLabelBooking, adminLabelReport;

    private Stage stage;
    private Scene scene;

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");
    static File bookingFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Booking.txt");
    static File logFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Logs.txt");

    Store store = new Store(adminFile, customerFile, carFile, bookingFile, logFile);

    /**
     * Function name: incrementValue
     * @param <K>
     * @param map
     * @param key
     * 
     * What it does:
     *  1. Checks for key in HashMap
     *  2. Increments the value for the key if found
     */
    public static<K> void incrementValue(Map<K, Integer> map, K key) {
        Integer count = map.containsKey(key) ? map.get(key) : 0;
        map.put(key, count + 1);
    }

    /**
     * Function name: receiveAdminData
     * @param event
     * @return Admin
     * 
     * What it does: <br>
     *  1. Gets Admin object from Login screen
     */
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
        bookingTable.setPlaceholder(new Label("No booking records to display"));
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
        bookingTable.getItems().setAll(obsBookingList);

        bookingTable.setRowFactory(row -> new TableRow<Booking>(){
            @Override
            public void updateItem(Booking item, boolean empty){
                super.updateItem(item, empty);
        
                if (item == null || empty) {
                    setStyle("");
                } else {
                    //Now 'item' has all the info of the Person in this row
                    if (item.getBookingStatus().toLowerCase().equals("pending")) {
                        //We apply now the changes in all the cells of the row
                        for(int i=0; i<getChildren().size();i++){
                            ((Labeled) getChildren().get(i)).setTextFill(Color.ORANGE);
                        }                        
                    } else {
                        if(getTableView().getSelectionModel().getSelectedItems().contains(item)){
                            for(int i=0; i<getChildren().size();i++){
                                ((Labeled) getChildren().get(i)).setTextFill(Color.WHITE);;
                            }
                        }
                        else{
                            for(int i=0; i<getChildren().size();i++){
                                ((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);;
                            }
                        }
                    }
                }
            }
        });
        
        // Charges Pie Chart
        // Getting the neccesary information (Total Rental Fees charged and Fines charged)
        ArrayList<Booking> bookings = store.getBookings();
        int totalRent = 0, totalFines = 0;

        // Getting the Rental and Fines charged from Booking class
        for (Booking booking: bookings) {
            totalRent += booking.getTotalRent();
            totalFines += booking.getTotalFines();
        }
        
        // Adding labels to the Pie Chart
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Rental");
        labels.add("Fines");
        
        // Adding the values to the Pie Chart
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(totalRent);
        values.add(totalFines);

        // Formatting the Labels and Values for the Pie Chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (int i = 0; i < labels.size(); i++) {
            pieChartData.add(new PieChart.Data(labels.get(i), values.get(i)));
        }

        // Preparing and showing the Pie Chart
        chargesPieChart.setData(pieChartData);
        chargesPieChart.setTitle("Total Rental and Fines charged");
        chargesPieChart.setLabelLineLength(50);
        chargesPieChart.setLabelsVisible(true);
        chargesPieChart.setVisible(true);

        for (PieChart.Data data: pieChartData) {
            Tooltip tooltip = new Tooltip(Double.toString(data.getPieValue()));
            Tooltip.install(data.getNode(), tooltip);

            data.pieValueProperty().addListener((observable, oldPieValue, newPieValue)->{
                tooltip.setText(Integer.toString((int) newPieValue));
            });
        }

        // Sales Pie Chart
        // Getting all the car plates of booked cars
        ArrayList<String> carPlates = new ArrayList<>();
        for (Booking b: bookings) {
            carPlates.add(b.getPlateNumber());
        }

        // Getting all the cars that have been booked
        ArrayList<Car> bookedCars = new ArrayList<>();
        for (String carPlate: carPlates) {
            try {
                bookedCars.add(store.findCar(carPlate));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // Getting all the 
        HashMap<String, Integer> carTypeSales = new HashMap<>();
        for (Car car: bookedCars) {
            if (carTypeSales.containsKey(car.getCarType())) {
                continue;
            } else {
                carTypeSales.put(car.getCarType(), 0);
            }
        }

        for (Car car: bookedCars) {
            incrementValue(carTypeSales, car.getCarType());
        }

        ObservableList<PieChart.Data> salesPieChartData = FXCollections.observableArrayList();

        for (String i: carTypeSales.keySet()) {
            salesPieChartData.add(new PieChart.Data(i, carTypeSales.get(i)));
        }
        
        // Preparing and showing the Pie Chart
        salesPieChart.setData(salesPieChartData);
        salesPieChart.setTitle("Types of Cars rented");
        salesPieChart.setLabelLineLength(50);
        salesPieChart.setLabelsVisible(true);
        salesPieChart.setVisible(true);

        for (PieChart.Data data: salesPieChartData) {
            Tooltip tooltip = new Tooltip(Double.toString(data.getPieValue()));
            Tooltip.install(data.getNode(), tooltip);

            data.pieValueProperty().addListener((observable, oldPieValue, newPieValue)->{
                tooltip.setText(Integer.toString((int) newPieValue));
            });
        }

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                clearBookingButton.fire();
            }
            
        });
    }

    /**
     * Function name: logout
     * @param e
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Brings user back to Login screen
     */
    public void logout(ActionEvent e) throws IOException {
        
        Admin tmpAdmin = receiveAdminData(e);
        Log log = new Log(LocalDate.now(), tmpAdmin.getEmail(), "Logout Successful");
        store.addLog(log);

        Parent root = FXMLLoader.load(getClass().getResource("/Pages/LoginPage.fxml"));
        stage =  (Stage)((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Function name: searchCar
     * @param e
     * 
     * What it does: <br> 
     *  1. Gets the text from the search bar <br>
     *  2. Throw an alert if search bar is empty but user still pressed search button <br>
     *  3. Find the car that matches the text from the search bar and add it to an ArrayList <br>
     *  4. Set the TableView contents to the ArrayList with the search results
     */
    public void searchCar(ActionEvent e) {
        ArrayList<Car> tmpCars = store.getCars();
        ArrayList<Car> searchedCars = new ArrayList<>();
        String searchedCar = searchCarTextField.getText().toLowerCase().trim();

        if (searchCustomerTextField.getText().isEmpty()) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Nothing to search, please give an input.");
            a.show();
        }

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

    /**
     * Function name: clearCar
     * @param e
     * 
     * What it does: <br>
     *  1. Clear the search bar <br>
     *  2. Revert the TableView contents to all the Car objects of the organization
     */
    public void clearCar(ActionEvent e) {
        searchCarTextField.clear();
        ArrayList<Car> tmpCars = store.getCars();
        carTable.getItems().clear();
        carTable.getItems().addAll(tmpCars);
    }


    /**
     * Function name: addCar
     * @param e
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Brings the user to the Add Car page
     */
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

    /**
     * Function name: removeCar
     * @param e
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Removes the selected Car object from the ArrayList <br>
     *  2. Save the new ArrayList to Car.txt 
     */
    public void removeCar(ActionEvent e) throws IOException {

        Admin tmpAdmin = receiveAdminData(e);
        Log log = new Log(LocalDate.now(), tmpAdmin.getEmail(), "Remove car successful");
        store.addLog(log);

        Car tmpCar = carTable.getSelectionModel().getSelectedItem();
        store.removeCar(tmpCar);
        clearCar(e);
    }

    /**
     * Function name: searchCustomer
     * @param e
     * 
     * What it does: <br> 
     *  1. Gets the text from the search bar <br>
     *  2. Throw an alert if search bar is empty but user still pressed search button <br>
     *  3. Find the Customer that matches the text from the search bar and add it to an ArrayList <br>
     *  4. Set the TableView contents to the ArrayList with the search results
     */
    public void searchCustomer(ActionEvent e) {
        ArrayList<User> tmpCustomers = store.getCustomers();
        ArrayList<User> searchedCustomers = new ArrayList<>();
        String searchedCustomer = searchCustomerTextField.getText().toLowerCase().trim();

        if (searchCustomerTextField.getText().isEmpty()) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Nothing to search, please give an input.");
            a.show();
        }

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

    /**
     * Function name: clearCustomer
     * @param e
     * 
     * What it does: <br>
     *  1. Clear the search bar <br>
     *  2. Revert the TableView contents to all the Customer objects of the organization
     */
    public void clearCustomer(ActionEvent e) {
        searchCustomerTextField.clear();
        ArrayList<User> tmpCustomers = store.getCustomers();
        customerTable.getItems().clear();
        customerTable.getItems().addAll(tmpCustomers);
    }

    /**
     * Function name: searchBooking
     * @param e
     * 
     * What it does: <br> 
     *  1. Gets the text from the search bar <br>
     *  2. Throw an alert if search bar is empty but user still pressed search button <br>
     *  3. Find the Booking that matches the text from the search bar and add it to an ArrayList <br>
     *  4. Set the TableView contents to the ArrayList with the search results
     */
    public void searchBooking(ActionEvent e) {
        ArrayList<Booking> tmpBookings = store.getBookings();
        ArrayList<Booking> searchedBookings = new ArrayList<>();
        String searchedBooking = searchBookingTextField.getText().toLowerCase().trim();

        if (searchCustomerTextField.getText().isEmpty()) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Nothing to search, please give an input.");
            a.show();
        }

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

    /**
     * Function name: clearBooking
     * @param e
     * 
     * What it does: <br>
     *  1. Clear the search bar <br>
     *  2. Revert the TableView contents to all the Booking objects of the organization
     */
    public void clearBooking(ActionEvent e) {
        searchBookingTextField.clear();
        ArrayList<Booking> tmpBookings = store.getBookings();
        bookingTable.getItems().clear();
        bookingTable.getItems().addAll(tmpBookings);

        bookingTable.setRowFactory(row -> new TableRow<Booking>(){
            @Override
            public void updateItem(Booking item, boolean empty){
                super.updateItem(item, empty);
        
                if (item == null || empty) {
                    setStyle("");
                } else {
                    //Now 'item' has all the info of the Person in this row
                    if (item.getBookingStatus().toLowerCase().equals("pending")) {
                        //We apply now the changes in all the cells of the row
                        for(int i=0; i<getChildren().size();i++){
                            ((Labeled) getChildren().get(i)).setTextFill(Color.ORANGE);
                        }                        
                    } else {
                        if(getTableView().getSelectionModel().getSelectedItems().contains(item)){
                            for(int i=0; i<getChildren().size();i++){
                                ((Labeled) getChildren().get(i)).setTextFill(Color.WHITE);;
                            }
                        }
                        else{
                            for(int i=0; i<getChildren().size();i++){
                                ((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);;
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Function name: approve
     * @param e
     * @throws IllegalAccessException
     * @throws IOException
     * @throws ParseException
     * 
     * What it does: <br>
     *  1. If booking type is "Booking", state is changed to "Approved" <br>
     *  2. If booking type is "Damaged", state is changed to "Paid" <br>
     *  3. If booking type is "Return", statis is changed to "Approved" <br>
     *  4. Booking will add dates to the corresponding car while Return will remove dates
     */
    public void approve(ActionEvent e) throws IllegalAccessException, IOException, ParseException {
        Admin tmpAdmin = receiveAdminData(e);
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();

        if (selectedBooking.getBookingStatus().toLowerCase().trim().equals("rejected")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Rejected bookings cannot be modified!");
            alert.show();
        } else {
            switch (selectedBooking.getBookingType().toLowerCase().trim()) {
                case "booking": 
                    selectedBooking = tmpAdmin.approve(selectedBooking, true);
                    store.rentCar(store.findCar(selectedBooking.getPlateNumber()), selectedBooking.getBookingStart(), selectedBooking.getBookingEnd());
                    break;
                case "damaged": 
                    selectedBooking = tmpAdmin.approve(selectedBooking, true);
                    store.returnCar(store.findCar(selectedBooking.getPlateNumber()), selectedBooking.getBookingStart(), selectedBooking.getBookingEnd());
                    break;
                case "return": 
                    selectedBooking = tmpAdmin.approveReturn(selectedBooking, true);
                    store.returnCar(store.findCar(selectedBooking.getPlateNumber()), selectedBooking.getBookingStart(), selectedBooking.getBookingEnd());
                    break;
            }
    
            for (Booking booking: store.getBookings()) {
                if (booking.getEmail().equals(selectedBooking.getEmail()) &&
                    booking.getPlateNumber().equals(selectedBooking.getPlateNumber()) &&
                    booking.getBookingStart().isEqual(selectedBooking.getBookingStart())) {
                    store.removeBooking(booking);
                    store.addBooking(selectedBooking);
                }
            }
            Log log = new Log(LocalDate.now(), tmpAdmin.getEmail(), "Approval successful");
            store.addLog(log);
            clearBooking(e);
        }
    }

    /**
     * Function name: reject
     * @param e
     * @throws IllegalAccessException
     * @throws IOException
     * @throws ParseException
     * 
     * What it does: <br>
     *  1. If booking type is "Booking", state is changed to "Rejected" <br>
     *  2. If booking type is "Damaged", state is changed to "Pending" <br>
     *  3. If booking type is "Return", type is changed to "Damaged" and state is changed to "Pending" <br>
     *  4. Damaged will impose fines
     */
    public void reject(ActionEvent e) throws IllegalAccessException, IOException {
        Admin tmpAdmin = receiveAdminData(e);
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();

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
                case "damaged":
                    if (selectedBooking.getBookingStatus().toLowerCase().trim().equals("pending")) {
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText("You cannot reject a pending fine payment");
                        a.show();
                    }
                    selectedBooking = tmpAdmin.approve(selectedBooking, false);
                case "return":
                    selectedBooking = tmpAdmin.approveReturn(selectedBooking, false);
                    break;
            }
    
            for (Booking booking: store.getBookings()) {
                if (booking.getEmail().equals(selectedBooking.getEmail()) &&
                    booking.getPlateNumber().equals(selectedBooking.getPlateNumber()) &&
                    booking.getBookingStart().isEqual(selectedBooking.getBookingStart())) {
                    store.removeBooking(booking);
                    store.addBooking(selectedBooking);
                }
            }
            clearBooking(e);
        }
    }

    /**
     * Function name: refreshChart
     * @param e
     * 
     * What it does:
     *  1. Refreshes the chart with new data
     */
    public void refreshChart(ActionEvent e) {
        // Charges Pie Chart
        ArrayList<Booking> bookings = store.getBookings();
        int totalRent = 0, totalFines = 0;

        for (Booking booking: bookings) {
            totalRent += booking.getTotalRent();
            totalFines += booking.getTotalFines();
        }
        
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Rental");
        labels.add("Fines");
        
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(totalRent);
        values.add(totalFines);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (int i = 0; i < labels.size(); i++) {
            pieChartData.add(new PieChart.Data(labels.get(i), values.get(i)));
        }

        chargesPieChart.setData(pieChartData);
        chargesPieChart.setTitle("Total Rental and Fines charged");
        chargesPieChart.setLabelLineLength(50);
        chargesPieChart.setLabelsVisible(true);
        chargesPieChart.setVisible(true);

        for (PieChart.Data data: pieChartData) {
            Tooltip tooltip = new Tooltip(Double.toString(data.getPieValue()));
            Tooltip.install(data.getNode(), tooltip);

            data.pieValueProperty().addListener((observable, oldPieValue, newPieValue)->{
                tooltip.setText(Integer.toString((int) newPieValue));
            });
        }

        // Sales Pie Chart
        ArrayList<String> carPlates = new ArrayList<>();
        for (Booking b: bookings) {
            carPlates.add(b.getPlateNumber());
        }

        // Getting all the cars that have been booked
        ArrayList<Car> bookedCars = new ArrayList<>();
        for (String carPlate: carPlates) {
            try {
                bookedCars.add(store.findCar(carPlate));
            } catch (FileNotFoundException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        }

        // Getting all the 
        HashMap<String, Integer> carTypeSales = new HashMap<>();
        for (Car car: bookedCars) {
            if (carTypeSales.containsKey(car.getCarType())) {
                continue;
            } else {
                carTypeSales.put(car.getCarType(), 0);
            }
        }

        for (Car car: bookedCars) {
            incrementValue(carTypeSales, car.getCarType());
        }

        ObservableList<PieChart.Data> salesPieChartData = FXCollections.observableArrayList();

        for (String i: carTypeSales.keySet()) {
            salesPieChartData.add(new PieChart.Data(i, carTypeSales.get(i)));
        }
        
        // Preparing and showing the Pie Chart
        salesPieChart.setData(salesPieChartData);
        salesPieChart.setTitle("Types of Cars rented");
        salesPieChart.setLabelLineLength(50);
        salesPieChart.setLabelsVisible(true);
        salesPieChart.setVisible(true);

        for (PieChart.Data data: salesPieChartData) {
            Tooltip tooltip = new Tooltip(Double.toString(data.getPieValue()));
            Tooltip.install(data.getNode(), tooltip);

            data.pieValueProperty().addListener((observable, oldPieValue, newPieValue)->{
                tooltip.setText(Integer.toString((int) newPieValue));
            });
        }
    }
}
