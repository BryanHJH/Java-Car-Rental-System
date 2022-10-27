package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;

import Classes.Admin;
import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {
    
    @FXML
    private Label errorLabel;

    @FXML
    private TextField usernameTextField, passwordTextField;

    @FXML
    private Button loginButton, forgotPasswordButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");

    public static User[] readFile(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(file);
        User[] userList = (User[]) gson.fromJson(reader, Admin[].class);
        return userList;
    }

    /**
     * Function name: login
     * @param event
     * 
     * What it does:
     *  1. First check the username that is input (use regex) - as long as the email format is xxxx@staff.com then it means its an Admin, otherwise they're customers
     *  2. Then depending on their role (Admin or Customer) read the correct file and store it as a User[] using readFile function above
     *  3. Use the login function written in their respective class (Admin or Customer class) to authenticate the user
     *  4. If authentication is successful, bring them to the landing page with the catalog (both Admin and Customer)
     *  5. If authentication fails, show a warning message, set the text of errorLabel to some type of warning.
     */
    public void login(ActionEvent event) {

    }

    /**
     * Function name: forgotPassword
     * @param event
     * @throws IOException
     * 
     * What it does:
     *  If the user forgets their password, they just click on the button and they will be prompted to another page to provide their email for a Password Reset link to be sent
     */
    public void forgotPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Pages/ForgotPassword.fxml"));
        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
