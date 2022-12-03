package Controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Class.Admin;
import Class.Customer;
import Class.GsonLocalDateAdapter;
import Class.Log;
import Class.Store;
import Class.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginPageController {
    
    @FXML
    private Label errorLabel;

    @FXML
    private TextField usernameTextField, passwordTextField;

    @FXML
    private Button loginButton, registerButton, forgotPasswordButton;

    private Stage stage;
    private Scene scene;

    AdminMainPageController controller = new AdminMainPageController();

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");
    static File bookingFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Booking.txt");
    static File logFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Logs.txt");

    Store store = new Store(adminFile, customerFile, carFile, bookingFile, logFile);

    /**
     * Name: readAdminFile
     * @param file
     * @return User[]
     * @throws FileNotFoundException
     */
    public static User[] readAdminFile(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(file);
        User[] userList = (User[]) gson.fromJson(reader, Admin[].class);
        return userList;
    }

    /**
     * Name: readCustomerFile
     * @param file
     * @return User[]
     * @throws FileNotFoundException
     */
    public static User[] readCustomerFile(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(file);
        User[] userList = (User[]) gson.fromJson(reader, Customer[].class);
        return userList;
    }

    /**
     * Function name: saveUsers
     * @param file
     * @param arr
     * @throws IOException
     */
    public static void saveUsers(File file, ArrayList<Log> arr) throws IOException {
        FileWriter fwriter = new FileWriter(file);
        
        try (BufferedWriter writer = new BufferedWriter(fwriter)) {
            Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
            gson.toJson(arr, writer);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function name: login
     * @param event
     * 
     * What it does: <br>
     *  1. First check the username that is input (use regex) - as long as the email format is xxxx@car.com then it means its an Admin, otherwise they're customers <br>
     *  2. Then depending on their role (Admin or Customer) read the correct file and store it as a User[] using readFile function above <br>
     *  3. Use the login function written in their respective class (Admin or Customer class) to authenticate the user <br>
     *  4. If authentication is successful, bring them to the landing page with the catalog (both Admin and Customer) <br>
     *  5. If authentication fails, show a warning message, set the text of errorLabel to some type of warning. <br>
     * @throws IOException
     */
    public void login(ActionEvent event) throws Exception {

        // Setting up regex to identify Admin emails
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9]*@car\\.[a-zA-Z]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(usernameTextField.getText());
        boolean matchFound = emailMatcher.find();

        if (matchFound) { // Admin login

            User[] adminList = readAdminFile(adminFile);
            for (Admin admin: (Admin[]) adminList) {

                if (admin.getEmail().equals(usernameTextField.getText())) {

                    boolean loginAttempt = admin.login(admin, passwordTextField.getText());
                    User tmpAdmin = store.findAdmin(usernameTextField.getText());

                    if (loginAttempt) { // if login successful
                        
                        Log log = new Log(LocalDate.now(), tmpAdmin.getEmail(), "Login Successful");
                        store.addLog(log);

                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();

                        Parent root = FXMLLoader.load(getClass().getResource("/Pages/AdminMainPage.fxml"));
                        stage.setUserData(tmpAdmin);
                        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } else {

                        String message = "Email and/or Password is incorrect";
                        errorLabel.setTextFill(Color.RED);
                        errorLabel.setText(message);

                    }
                    
                } else { // if login unsuccessful

                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Email and/or Password is incorrect");
                    
                }   

            }

        } else { // Customer login
            User[] customerList = readCustomerFile(customerFile);
            for (Customer customer: (Customer[]) customerList) {

                if (customer.getEmail().equals(usernameTextField.getText())) {

                    boolean loginAttempt = customer.login(customer, passwordTextField.getText());
                    Customer tmpCustomer = store.findCustomer(usernameTextField.getText());

                    if (loginAttempt) { // if login successful
                        
                        Log log = new Log(LocalDate.now(), tmpCustomer.getEmail(), "Login Successful");
                        store.addLog(log);

                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        
                        // FXMLLoader loader = new 
                        Parent root = FXMLLoader.load(getClass().getResource("/Pages/CustomerPage.fxml"));

                        stage.setUserData(tmpCustomer);
                        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } else {

                        errorLabel.setTextFill(Color.RED);
                        errorLabel.setText("Email and/or Password is incorrect");

                    }
                    
                } else { // if login unsuccessful

                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Email and/or Password is incorrect");
                    
                }   
            }
        }
    }

    /**
     * Function name: register
     * @param event
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Brings the user to the Register page
     */
    public void register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Pages/RegisterPage.fxml"));
        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Function name: forgotPassword
     * @param event
     * @throws IOException
     * 
     * What it does: <br>
     *  If the user forgets their password, they just click on the button and they will be prompted to another page to provide their email for a Password Reset link to be sent
     */
    public void forgotPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Pages/ForgotPassword.fxml"));
        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String returnUsername() {
        return usernameTextField.getText();
    }

}
