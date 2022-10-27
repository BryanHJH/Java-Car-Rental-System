package Controllers;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.scene.paint.Color;

public class ForgotPasswordController {
    
    @FXML
    private Button sendLinkButton, returnButton;

    @FXML
    private Label successLabel;

    @FXML
    private TextField usernameTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void sendLink(ActionEvent event) {
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9]*@[a-z]{1,}\\.[a-z]{2, 3}\b");
        Matcher emailMatcher = emailPattern.matcher(usernameTextField.getText());
        boolean matchFound = emailMatcher.find();

        if (matchFound) {
            successLabel.setTextFill(Color.GREEN);
            successLabel.setText("Link sent");
        } else {
            successLabel.setTextFill(Color.RED);
            successLabel.setText("Email format is wrong!");
        }
    }

    public void returnToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Pages/LoginPage.fxml"));
        stage =  (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
