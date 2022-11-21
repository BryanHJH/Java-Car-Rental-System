package Class;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

/**
 * Store
 */
public class Store {

    private Car[] cars;
    private ArrayList<User> admins;
    private ArrayList<User> customers;

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");

    public Store(Car[] cars) {
        this.cars = Arrays.copyOf(cars, cars.length);
        this.admins = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public Car getCars(int index) {
        return this.cars[index];
    }

    public void setCars(int index, Car car) {
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

    public static void saveCars(File file, ArrayList<Car> arr) throws IOException {
        FileWriter fwriter = new FileWriter(file);
        
        try (BufferedWriter writer = new BufferedWriter(fwriter)) {
            Gson gson = new Gson();
            gson.toJson(arr, writer);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Name: readUserFile
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static User[] readUserFile(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(file);
        User[] customerList = (User[]) gson.fromJson(reader, Customer[].class);
        return customerList;
    }

    public static Car[] readCarFile(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(file);
        Car[] carList = gson.fromJson(reader, Car[].class);
        return carList;
    }

    public void rentCar(Car car, String date) throws ParseException, FileNotFoundException, IOException {
        if (car.checkAvailability(date)) {
            car.addDates(date);
        }

        Car[] currentCarList = readCarFile(carFile);
        ArrayList<Car> newCarList = new ArrayList<>();

        for (Car currentCar: currentCarList) {
            if (currentCar.equals(car)) {
                newCarList.add(currentCar);
            } else {
                newCarList.add(car);
            }
        }

        saveCars(carFile, newCarList);
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
     * @throws IOException
     */
    public void addAdmin(Admin admin) throws IOException {
        this.admins.add(admin);
        saveUsers(adminFile, admins);
    }

    public void addCustomer(Customer customer) throws IOException {
        this.customers.add(customer);
        saveUsers(customerFile, customers);
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
     * @throws IOException
     */
    public void removeAdmin(String username) throws IOException {
        Admin[] adminArray = new Admin[this.admins.size()];
        ArrayList<User> newAdminList = Admin.delete(this.admins.toArray(adminArray), username);
        saveUsers(adminFile, newAdminList);
    }

    public void removeCustomer(String username) throws IOException {
        Customer[] customerArray = new Customer[this.customers.size()];
        ArrayList<User> newCustomerList = Customer.delete(this.admins.toArray(customerArray), username);
        saveUsers(customerFile, newCustomerList);
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
     * @throws FileNotFoundException
     */
    public Customer findCustomer(String username) throws FileNotFoundException {
        User[] customerList = readUserFile(customerFile);
        for (Customer customer: (Customer[]) customerList) {
            if (customer.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return customer;
            }
        }
        return null;
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
     * @throws FileNotFoundException
     */
    public Car findCar(String platNumber) throws FileNotFoundException {
        Car[] carList = readCarFile(carFile);
        for (Car car: carList) {
            if (car.getPlateNumber().equals(platNumber)) {
                return car;
            }
        }

        return null;
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
            case "Type1": return "Type1";
            case "Type2": return "Type2";
            case "Type3": return "Type3";
            default: return "Nothing found";
        }
    }
    
}