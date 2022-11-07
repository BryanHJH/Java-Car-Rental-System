package Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.jasypt.util.password.BasicPasswordEncryptor;

import com.google.gson.Gson;

public class Customer extends User{
    
    private String rentDate;
    private String returnDate;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
    
    public Customer(String fullname, String identification, String email, String contact, String username, String password) {
        super(fullname, identification, email, contact, username, password);
        this.bookingHistory = new ArrayList<Customer>();
    }



    //used for login
    @Override
    public boolean login(User[] arr, String email, String password) {

        boolean result = false;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        for (User user: arr) {
            if (user.getEmail().equals(email)) {
                if (passwordEncryptor.checkPassword(password, user.getPassword())) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        return result;
    }
    
    public static ArrayList<User> delete(User[] arr, String username) {

        ArrayList<User> newUserList = new ArrayList<>();
        
        for (User user: arr) {
            if (user.getUsername().toLowerCase().equals(username)) {
                continue;
            } else {
                newUserList.add(user);
            }
        }

        return newUserList;
    }

    /*
    * Function name: viewCatalog
    * 
    * What it does:
    * 1. Let Customer to browse available car
    */
    @Override
    public void viewCatalog() {
        // TODO Auto-generated method stub
        
    }


    public void changePersonalInfo() {
    /*
    * Function name: changePersonalInfo
    * 
    * Discuss with Bryan: 2 ways to change info
    *    First: Use buttons to let customer choose what info to change
    *    
    *    Second: Use a single page to let customer change every info, but maybe
    *    we can set the text field to contain the old information so customer can leave
    *    it if they only want to change certain info
    * 
    * What it does:
    *  1. Read Customer.txt
    *  2. Overwrite the old information to new information into Customer.txt
    */
        
        // set the text field to contain old information using get method

        // then overwrite old information with new information with identification as reference (meaning identification can't be changed)
        
    }

    public void bookCar() throws IOException{
    /*
    * Function name: bookCar
    * 
    * Discuss with Bryan: According to README.MD, you mentioned that we can try to 
    * make the approval done automatically, so can I assume that after customer selected
    * the car and date, then it proceeds to payment page.
    *
    * What it does:
    *  1. Show the information of car, amount, rent date, return date
    *  2. After confirmation, send a notification (either via email or text message)
    *  3. Then, remove date from car catalog (not sure about this)
    */

        // after customer chose the car, read the car information and write into the booking history
        File file = new File("C:\\Users\\PC\\OneDrive - Asia Pacific University\\Y2S1\\OODJ\\javafx\\project\\assignment\\assignment\\src\\databases\\customerHistory.txt");


        FileWriter fwriter = new FileWriter(file);
        
        try (BufferedWriter writer = new BufferedWriter(fwriter)) {
            Gson gson = new Gson();
            gson.toJson(historyArr, writer);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    

/*
 * Function name: returnCar
 * 
 * What it does:
 *  1. Let the dealer approve the return
 *  2. If approved, send a notification, then, add the date to car catalog?
 *  3. If rejected, semd a notification with a fine imposed
 */


    

}