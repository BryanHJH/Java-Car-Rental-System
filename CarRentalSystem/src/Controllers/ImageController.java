package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Class.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ImageController implements Initializable {
    
    @FXML
    private Button backButton;

    @FXML
    private ImageView reportImageView;

    private Scene scene;

    private Admin receiveAdminData(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Admin admin = (Admin) stage.getUserData();
        return admin;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            reportImageView.setImage(new Image(new FileInputStream("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Reports\\Reports.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void back(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/Pages/AdminMainPage.fxml"));
        Admin tmpAdmin = receiveAdminData(e);
        stage.setUserData(tmpAdmin);
        stage =  (Stage)((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
