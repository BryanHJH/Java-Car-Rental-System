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
    
    @Override
    public void viewCatalog() {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Function name: findCustomer
     * 
     * @param username
     * @return
     * 
     * What it does:
     *  1. Get the customer username
     *  2. Read the customer.txt file and find the matching customer record with the provided username.
     *  3. Return the customer object
     */
    public Customer findCustomer(String username) {
        // TODO: Finish the Customer class first
    }

    /**
     * Function name: findCar
     * 
     * @param carID
     * @return
     * 
     * What it does:
     *  1. Get the Car ID
     *  2. Read the Car.txt file and find the matching Car record with the provided Car ID
     *  3. Return the Car object
     */
    public Car findCar(String carID) {
        // TODO: Finish Car class first
    }

    public void editCarInfo(Car car) {
        // TODO: Access the Car profile and change the Car details, require the fields of the Car class
    }

    public void checkRentalHistory(Customer customer) {
        // TODO: Read Customer.txt and get the history section of the field
    }

    /**
     * Name: approve
     * 
     * @param carCondition
     * 
     * What it does:
     *  1. Check whether is the car in good condition (true for good;false for bad)
     *  2. To do 1., use findCar function to get the specific Car object
     *  2. If true, returns true; If false, return false
     */
    public boolean approve(boolean carCondition) {

        if (carCondition == true) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Function name: imposeFines
     * 
     * @param username
     * @param returnApproval
     * @param damagedCar
     * 
     * What it does:
     *  1. Use findCustomer function and username param to find the bad customer.
     *  2. Impose 10% of the car's total rental as the fine
     *  3. Return the total fines
     */
    public int imposeFines(Customer customer, Car damagedCar, boolean returnApproval) {
        // TODO: Get the result from approve, and if return car failed, impose fines to the customer, send notification and deduct from customer's balance

        int fines;

        if (returnApproval == true) {
            fines = 0;
        } else {
            fines = 0;
        }

        return fines;

    }

    /**
     * Function name: requestReports
     * 
     * @param reportType
     * @return
     * 
     * What it does:
     *  1. From the GUI, a button will be selected
     *  2. The text of the selected button will be extracted and passed through this function
     *  3. This function will return the string and passed to the Store class as a String
     */
    public String requestReports(String reportType) {
        return reportType;
    }


    @Override
    public String toString() {
        return  "Name: " + this.getFullname() + "\n" +
                "ID: " + this.getIdentification() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Contact Number: " + this.getContact() + "\n" +
                "Username: " + this.getUsername();
    }

}