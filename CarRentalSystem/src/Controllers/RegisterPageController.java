package Controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Class.Customer;
import Class.Log;
import Class.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RegisterPageController {
    
    @FXML
    private TextField fullNameTextField, identificationTextField, contactTextField, emailTextField, usernameTextField;

    @FXML
    private PasswordField passwordField, confirmPasswordField;

    @FXML
    private Button registerButton, cancelButton;

    private Stage stage;
    private Scene scene;

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");
    static File bookingFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Booking.txt");
    static File logFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Logs.txt");

    Store store = new Store(adminFile, customerFile, carFile, bookingFile, logFile);

    /**
     * Function name: register
     * @param event
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Gets all the text from the text and password fields <br>
     *  2. Validates the contents extracted from the text and password fields <br>
     *  3a. If all are ok, add the new user to Customer.txt <br>
     *  3b. If some fields are wrong, throw an Alert window
     */
    public void register(ActionEvent event) throws IOException {

        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9]*@[a-zA-Z]{1,}\\.[a-zA-Z]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(emailTextField.getText());
        boolean matchFound = emailMatcher.find();

        if (!matchFound) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Your email format is wrong! Please try again.");
            a.show();
        } else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Passwords do not match! Please try again.");
            a.show();
        } else if ( fullNameTextField.getText().isEmpty() ||
                    identificationTextField.getText().isEmpty() ||
                    contactTextField.getText().isEmpty() ||
                    emailTextField.getText().isEmpty() ||
                    usernameTextField.getText().isEmpty() ||
                    passwordField.getText().isEmpty() ||
                    confirmPasswordField.getText().isEmpty()) {

            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Empty fields found, please provide all necessary information to proceed");
            a.show();
        } else {
            
            Log log = new Log(LocalDate.now(), emailTextField.getText(), "Registration Successful");
            store.addLog(log);

            Customer newCustomer = new Customer(fullNameTextField.getText(), identificationTextField.getText(), emailTextField.getText(), contactTextField.getText(), usernameTextField.getText(), passwordField.getText());
            store.addCustomer(newCustomer);
            cancel(event);
        }

    }

    /**
     * Function name: cancel
     * @param event
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Brings the user back to the Login page
     */
    public void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Pages/LoginPage.fxml"));
        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
