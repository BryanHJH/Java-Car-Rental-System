package Class;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

/**
 * Store
 */
public class Store {

    private Car[] cars;
    private ArrayList<User> admins;


    public Store(Car[] cars) {
        this.cars = Arrays.copyOf(cars, cars.length);
        this.admins = new ArrayList<Admin>();
    }
    

    public Car[] getCars(int index) {
        return this.cars[index];
    }

    public void setCars(int index) {
        this.cars[index] = new Car(car);
    }

    public User getAdmins(int index) {
        return this.admins.get(index);
    }

    public void setAdmins(int index, Admin admin) {
        this.admins.set(index, admin);
    }

    /**
     * Function Name: saveUser
     * 
     * @param file
     * @param arr
     * @throws IOException
     * 
     * What it does:
     *  1. Locate file
     *  2. Get the arraylist with the data to be saved
     *  3. Write to the file in JSON format
     */
    public static void saveUsers(File file, ArrayList<User> arr) throws IOException {
        
        FileWriter fwriter = new FileWriter(file);
        
        try (BufferedWriter writer = new BufferedWriter(fwriter)) {
            Gson gson = new Gson();
            gson.toJson(arr, writer);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void rentCar(Car car) {

    } 

    /**
     * Function name: addAdmin
     * 
     * @param admin
     * @return
     * 
     * What it does:
     *  1. Get a new Admin object
     *  2. Append it to existing Admin (User) ArrayList
     *  3. Return the ArrayList
     */
    public ArrayList<User> addAdmin(Admin admin) {
        this.admins.add(admin);
        return this.admins;
    }

    /**
     * Function name: removeAdmin
     * 
     * @param username
     * @return
     * 
     * What it does:
     *  1. Get the username of the Admin to be removed.
     *  2. Use the delete function in Admin class to remove the Admin.
     */
    public ArrayList<User> removeAdmin(String username) {
        Admin[] adminArray = new Admin[this.admins.size()];
        ArrayList<User> newAdminList = Admin.delete(this.admins.toArray(adminArray), username);
        return newAdminList;
    }

    /**
     * Function name: generateReport
     * 
     * @param reportType
     * @return
     * 
     * What it does:
     *  1. From the Admin class, get the String of the report type
     *  2. Depending on th report type, read the necessary data from the text files
     *  3. Format the data acquired from the text files in a readable format
     */
    public String generateReport(String reportType) {
        switch (reportType) {
            case "Type1": ;
            case "Type2": ;
            case "Type3": ;
        }
    }
    
}