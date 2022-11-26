package Class;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Store
 */
public class Store {

    private ArrayList<Car> cars;
    private ArrayList<User> admins;
    private ArrayList<User> customers;
    private ArrayList<Booking> bookings;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy", Locale.US);

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");
    static File bookingFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Booking.txt");

    public Store(ArrayList<User> admins, ArrayList<User> customers, ArrayList<Car> cars) {
        this.cars = new ArrayList<>(cars);
        this.admins = new ArrayList<>(admins);
        this.customers = new ArrayList<>(customers);
    }

    public ArrayList<Car> getCars() {
        return this.cars;
    }

    public ArrayList<User> getAdmins() {
        return this.admins;
    }

    public ArrayList<User> getCustomers() {
        return this.customers;
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }

    public Car getCar(int index) {
        return this.cars.get(index);
    }

    public void setCar(int index, Car car) {
        this.cars.set(index, car);
    }

    public User getAdmin(int index) {
        return this.admins.get(index);
    }

    public void setAdmin(int index, Admin admin) {
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
            Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
            gson.toJson(arr, writer);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveCars(File file, ArrayList<Car> arr) throws IOException {
        FileWriter fwriter = new FileWriter(file);
        
        try (BufferedWriter writer = new BufferedWriter(fwriter)) {
            Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
            gson.toJson(arr, writer);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveBookings(File file, ArrayList<Booking> arr) throws IOException {
        FileWriter fwriter = new FileWriter(file);
        
        try (BufferedWriter writer = new BufferedWriter(fwriter)) {
            Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
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
    public static User[] readAdminFile(File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
        Reader reader = new FileReader(file);
        User[] customerList = (User[]) gson.fromJson(reader, Admin[].class);
        return customerList;
    }
    
    /**
     * Name: readUserFile
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static User[] readCustomerFile(File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
        Reader reader = new FileReader(file);
        User[] customerList = (User[]) gson.fromJson(reader, Customer[].class);
        return customerList;
    }

    public static Car[] readCarFile(File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
        Reader reader = new FileReader(file);
        Car[] carList = gson.fromJson(reader, Car[].class);
        return carList;
    }

    public static Booking[] readBookingFile(File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
        Reader reader = new FileReader(file);
        Booking[] bookingList = gson.fromJson(reader, Booking[].class);
        return bookingList;
    }

    public void rentCar(Car car, String bookingStart, String bookingEnd) throws ParseException, FileNotFoundException, IOException {
        
        LocalDate bookingStartDate = LocalDate.parse(bookingStart, formatter);
        LocalDate bookingEndDate = LocalDate.parse(bookingEnd, formatter);
        ArrayList<LocalDate> bookedDates = new ArrayList<>();
        List<LocalDate> dates = bookingStartDate.datesUntil(bookingEndDate).collect(Collectors.toList());

        for (LocalDate date: dates) {
            if (car.checkAvailability(date)) {
                bookedDates.add(date);
            } else {
                bookedDates.clear();
            }
        }

        if (bookedDates.size() != 0) {
            for (LocalDate date: bookedDates) {
                car.addDates(date);
            }
            
            Car[] currentCarList = readCarFile(carFile);
            ArrayList<Car> newCarList = new ArrayList<>();
    
            for (Car currentCar: currentCarList) {
                if (currentCar.equals(car)) {
                    newCarList.add(car);
                } else {
                    newCarList.add(currentCar);
                }
            }
    
            saveCars(carFile, newCarList);
        }

    }

    public void returnCar(Car car, String bookingStart, String bookingEnd) throws IOException {
        LocalDate bookingStartDate = LocalDate.parse(bookingStart, formatter);
        LocalDate bookingEndDate = LocalDate.parse(bookingEnd, formatter);
        ArrayList<LocalDate> dates = (ArrayList<LocalDate>) bookingStartDate.datesUntil(bookingEndDate).collect(Collectors.toList());
        ArrayList<LocalDate> bookedDates = car.getBookedDates();
        ArrayList<LocalDate> datesToBeRemoved = new ArrayList<>();

        for (LocalDate date: dates) {
            for (LocalDate carDate: bookedDates) {
                if (date.isEqual(carDate)) {
                    datesToBeRemoved.add(date);
                }
            }
        }

        car.removeDate(datesToBeRemoved);

        Car[] currentCarList = readCarFile(carFile);
        ArrayList<Car> newCarList = new ArrayList<>();

        for (Car currentCar: currentCarList) {
            if (currentCar.equals(car)) {
                newCarList.add(car);
            } else {
                newCarList.add(currentCar);
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

    /**
     * Function name: addCustomer
     * 
     * @param customer
     * @throws IOException
     * 
     * What it does:
     *  1. Get a new Admin object
     *  2. Append it to existing Admin (User) ArrayList
     *  3. Return the ArrayList
     */
    public void addCustomer(Customer customer) throws IOException {
        this.customers.add(customer);
        saveUsers(customerFile, customers);
    }

    public void addCar(Car car) throws IOException {
        this.cars.add(car);
        saveCars(carFile, cars);
    }

    /**
     * Function name: addBooking
     * 
     * @param b
     * @throws IOException
     * 
     * What it does:
     *  1. Create a temporary Booking Array with all current Bookings
     *  2. Create a new Booking ArrayList
     *  3a. Move all current Bookings from the Array to the ArrayList
     *  3b. If no current Bookings, meaning the Array is empty, just add the new Booking to the ArrayList
     *  4. Add the new Booking to the ArrayList
     *  5. Write the ArrayList to the text file
     */
    public void addBooking(Booking b) throws IOException {
        Booking[] oldBookings = readBookingFile(bookingFile);
        ArrayList<Booking> newBookings = new ArrayList<>();

        if (oldBookings != null) {
            for (Booking booking: oldBookings) {
                newBookings.add(booking);
            }
            newBookings.add(b);
        } else {
            newBookings.add(b);
        }

        saveBookings(bookingFile, newBookings);
    }

    /**
     * Function name: removeAdmin
     * 
     * @param username
     * @return
     * @throws IOException
     * 
     * What it does:
     *  1. Get the username of the Admin to be removed.
     *  2. Use the delete function in Admin class to remove the Admin.
     */
    public void removeAdmin(String username) throws IOException {
        Admin[] adminArray = new Admin[this.admins.size()];
        ArrayList<User> newAdminList = Admin.delete(this.admins.toArray(adminArray), username);
        saveUsers(adminFile, newAdminList);
    }

    /**
     * Function name: removeCustomer
     * 
     * @param username
     * @throws IOException
     * 
     * What it does:
     *  1. Get the username of the Admin to be removed.
     *  2. Use the delete function in Admin class to remove the Admin.
     */
    public void removeCustomer(String username) throws IOException {
        Customer[] customerArray = new Customer[this.customers.size()];
        ArrayList<User> newCustomerList = Customer.delete(this.admins.toArray(customerArray), username);
        saveUsers(customerFile, newCustomerList);
    }

    public void removeBooking(Booking b) throws IOException {
        Booking[] currentBookings = readBookingFile(bookingFile);
        ArrayList<Booking> newBookings = new ArrayList<Booking>(Arrays.asList(currentBookings));

        for (int i = 0; i < newBookings.size(); i++) {
            if (newBookings.get(i).equals(b)) {
                newBookings.remove(i);
            }
        }

        saveBookings(bookingFile, newBookings);
    }

    /**
     * Function name: findAdmin
     * 
     * @param username
     * @return
     * 
     * What it does:
     *  1. Get the admin username
     *  2. Read the admin.txt file and find the matching admin record with the provided username.
     *  3. Return the admin object
     * @throws FileNotFoundException
     */
    public Admin findAdmin(String username) throws FileNotFoundException {
        User[] adminList = readAdminFile(adminFile);
        for (Admin admin: (Admin[]) adminList) {
            if (admin.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return admin;
            }
        }
        return null;
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
        User[] customerList = readCustomerFile(customerFile);
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

    public Booking findBooking(String id) throws FileNotFoundException {
        Booking[] bookingList = readBookingFile(bookingFile);
        for (Booking booking: bookingList) {
            if (booking.getIdentifier().toString().equals(id)) {
                return booking;
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