package Classes;

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

    public ArrayList<User> addAdmin(Admin admin) {
        this.admins.add(admin);
        return this.admins;
    }

    public ArrayList<User> removeAdmin(String username) {
        Admin[] adminArray = new Admin[this.admins.size()];
        ArrayList<User> newAdminList = Admin.delete(this.admins.toArray(adminArray), username);
        return newAdminList;
    }
    
}