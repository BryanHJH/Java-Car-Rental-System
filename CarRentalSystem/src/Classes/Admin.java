package Classes;

import java.util.ArrayList;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class Admin extends User {
    
    public Admin(String fullname, String identification, String email, String contact, String username, String password) {
        super(fullname, identification, email, contact, username, password);
    }

    @Override
    public boolean login(User[] arr, String username, String password) {

        boolean result = false;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        for (User user: arr) {
            if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                if (passwordEncryptor.checkPassword(password, user.getPassword())) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        return result;
    }
    
    @Override
    public ArrayList<User> delete(User[] arr, String username) {
        // TODO Auto-generated method stub

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

    @Override
    public void viewCatalog() {
        // TODO Auto-generated method stub
        
    }
    
    public void editCarInfo() {
        // TODO: Access the Car profile and change the Car details, require the fields of the Car class
    }

    public void checkRentalHistory() {
        // TODO: Read Customer.txt and get the history section of the field
    }

    /**
     * Name: approve
     * 
     * @param transactionType
     * 
     * What it does:
     *  1. Check what type of transaction is being done, booking or car return
     *  2. If booking, only approve if the car is actually available and is ready for rent, customer basic information are filled up, and customer have enough balance to pay for the entire rental period. Else reject
     *  3. If car return, only approve if the car is not damaged in any way. Else, impose fines. 
     */
    public void approve(String transactionType) {
        // TODO: Approve either the booking or return of the car
    }

    public void imposeFines() {
        // TODO: Get the result from approve, and if return car failed, impose fines to the customer, send notification and deduct from customer's balance
    }

    public void collectPayment() {
        // TODO: If approve successful and is booking, collect payment from customer.
    }

    public void requestReports() {
        // TODO: Request reports generated from the Store
    }



}
