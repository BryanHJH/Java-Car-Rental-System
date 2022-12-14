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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private ArrayList<Log> logs;

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");
    static File bookingFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Booking.txt");
    static File logFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Logs.txt");

    /**
     * Constructor
     * @param admins
     * @param customers
     * @param cars
     * @param bookings
     * @param logs
     */
    public Store(ArrayList<User> admins, ArrayList<User> customers, ArrayList<Car> cars, ArrayList<Booking> bookings, ArrayList<Log> logs) {
        this.cars = new ArrayList<>(cars);
        this.admins = new ArrayList<>(admins);
        this.customers = new ArrayList<>(customers);
        this.bookings = new ArrayList<>(bookings);
        this.logs = new ArrayList<>(logs);
    }

    /**
     * Copy Constructor
     * @param admin
     * @param customer
     * @param car
     * @param booking
     * @param log
     */
    public Store(File admin, File customer, File car, File booking, File log) { 
        try {
            this.cars = new ArrayList<Car>(Arrays.asList(readCarFile(car)));
            this.admins = new ArrayList<User>(Arrays.asList(readAdminFile(admin)));
            this.customers = new ArrayList<User>(Arrays.asList(readCustomerFile(customer)));
            this.bookings = new ArrayList<Booking>(Arrays.asList(readBookingFile(booking)));
            this.logs = new ArrayList<Log>(Arrays.asList(readLogFile(log)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public ArrayList<Car> getCars() {
        return this.cars;
    }

    public ArrayList<User> getAdmins() {
        return this.admins;
    }

    public ArrayList<User> getCustomers() {
        return this.customers;
    }

    public ArrayList<Log> getLogs() {
        return this.logs;
    }

    public ArrayList<Booking> getBookings() {
        try {
            this.bookings = new ArrayList<Booking>(Arrays.asList(readBookingFile(bookingFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

    /**
     * Function Name: saveCars
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

    /**
     * Function Name: saveBookings
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
     * Function Name: saveLogs
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
    public static void saveLogs(File file, ArrayList<Log> arr) throws IOException {
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
     * Name: readAdminFile
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
     * Name: readCustomerFile
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

    /**
     * Name: readCarFile
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static Car[] readCarFile(File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
        Reader reader = new FileReader(file);
        Car[] carList = gson.fromJson(reader, Car[].class);
        return carList;
    }

    /**
     * Name: readBookingFile
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static Booking[] readBookingFile(File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
        Reader reader = new FileReader(file);
        Booking[] bookingList = gson.fromJson(reader, Booking[].class);
        return bookingList;
    }

        /**
     * Name: readLogFile
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static Log[] readLogFile(File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
        Reader reader = new FileReader(file);
        Log[] logs = gson.fromJson(reader, Log[].class);
        return logs;
    }

    /**
     * Function name: rentCar
     * @param car
     * @param bookingStart
     * @param bookingEnd
     * @throws ParseException
     * @throws FileNotFoundException
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Gets the Car object that is to be rented and the beginning and end of rental period <br>
     *  2. Add all the dates between the beginning and end to an arraylist <br>
     *  3. If car is already booked in any of those dates, the booking will not happen, no dates will be added to the car's bookedDates arraylist <br>
     *  4. Save the modified car information to Car.txt <br> 
     */
    public void rentCar(Car car, LocalDate bookingStart, LocalDate bookingEnd) throws ParseException, FileNotFoundException, IOException {
        ArrayList<LocalDate> bookedDates = new ArrayList<>();
        List<LocalDate> dates = bookingStart.datesUntil(bookingEnd).collect(Collectors.toList());

        for (LocalDate date: dates) {
            if (car.checkAvailability(date)) {
                bookedDates.add(date);
            } else {
                bookedDates.clear();
                break;
            }
        }

        if (bookedDates.size() != 0) {
            for (LocalDate date: bookedDates) {
                car.addDates(date);
            }
            
            car.addDates(bookingEnd);
                
            for (Car currentCar: readCarFile(carFile)) {
                if (currentCar.getPlateNumber().toLowerCase().trim().equals(car.getPlateNumber().toLowerCase().trim())) {
                    removeCar(currentCar);
                    addCar(car);
                }
            }
        }
    }

    /**
     * Function name: returnCar
     * @param car
     * @param bookingStart
     * @param bookingEnd
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Get all the dates that are to be removed into an arraylist <br>
     *  2. Remove all of them from the car's bookedDates arraylist <br>
     *  3. Save modified car information to Car.txt <br>
     */
    public void returnCar(Car car, LocalDate bookingStart, LocalDate bookingEnd) throws IOException {
        ArrayList<LocalDate> dates = (ArrayList<LocalDate>) bookingStart.datesUntil(bookingEnd).collect(Collectors.toList());
        ArrayList<LocalDate> bookedDates = car.getBookedDates();
        ArrayList<LocalDate> datesToBeRemoved = new ArrayList<>();

        for (LocalDate date: dates) {
            for (LocalDate carDate: bookedDates) {
                if (date.isEqual(carDate)) {
                    datesToBeRemoved.add(date);
                    continue;
                }
            }
        }

        datesToBeRemoved.add(bookingEnd);
        car.removeDate(datesToBeRemoved);

        for (Car currentCar: readCarFile(carFile)) {
            if (currentCar.getPlateNumber().toLowerCase().trim().equals(car.getPlateNumber().toLowerCase().trim())) {
                removeCar(currentCar);
                addCar(car);
            }
        }
    }

    /**
     * Function name: addAdmin
     * 
     * @param admin
     * @return
     * 
     * What it does: <br>
     *  1. Get a new Admin object <br>
     *  2. Append it to existing Admin (User) ArrayList <br>
     *  3. Save the ArrayList <br>
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
     * What it does: <br>
     *  1. Get a new Customer object <br>
     *  2. Append it to existing Customer (User) ArrayList <br>
     *  3. Save the ArrayList <br>
     */
    public void addCustomer(Customer customer) throws IOException {
        this.customers.add(customer);
        saveUsers(customerFile, customers);
    }

    /**
     * Function name: addCar
     * 
     * @param car
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Get a new Car object <br>
     *  2. Append it to existing Car ArrayList <br>
     *  3. Save the ArrayList to Car.txt <br>
     */
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
     * What it does: <br>
     *  1. Create a temporary Booking Array with all current Bookings <br>
     *  2. Create a new Booking ArrayList <br>
     *  3a. Move all current Bookings from the Array to the ArrayList <br>
     *  3b. If no current Bookings, meaning the Array is empty, just add the new Booking to the ArrayList <br>
     *  4. Add the new Booking to the ArrayList <br>
     *  5. Write the ArrayList to the text file <br>
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
     * Function name: addLog
     * @param l
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Gets a new Log object <br>
     *  2. Save it to Logs.txt <br>
     */
    public void addLog(Log l) throws IOException {
        this.logs.add(l);
        saveLogs(logFile, logs);;
    }

    /**
     * Function name: updateBooking
     * @param b
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Updates existing bookings, mostly just changing the status and type of the booking
     */
    public void updateBooking(Booking b) throws IOException {
        Booking[] oldBookings = readBookingFile(bookingFile);
        ArrayList<Booking> updatedBookings = new ArrayList<>();

        if (oldBookings.length == 0) {
            updatedBookings.add(b);
        } else {
            for (Booking booking: oldBookings) {
                if (booking.getEmail().equals(b.getEmail()) && booking.getPlateNumber().equals(b.getPlateNumber()) && booking.getBookingStart().isEqual(b.getBookingStart())) {
                    updatedBookings.add(b);
                    continue;
                } else {
                    updatedBookings.add(booking);
                }
            }
        }

        saveBookings(bookingFile, updatedBookings);
    }

    /**
     * Function name: removeAdmin
     * 
     * @param username
     * @return
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Get the Admin to be removed. <br>
     *  2. Remove the Admin object from the ArrayList <br>
     *  3. Save the ArrayList to Admin.txt     
     */
    public void removeAdmin(User admin) throws IOException {
        this.admins.add(admin);
        saveUsers(adminFile, this.admins);
    }

    /**
     * Function name: removeCustomer
     * 
     * @param username
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Get the Customer to be removed. <br>
     *  2. Remove the Customer object from the ArrayList <br>
     *  3. Save the ArrayList to Customer.txt
     */
    public void removeCustomer(User customer) throws IOException {
        this.customers.remove(customer);
        saveUsers(customerFile, this.customers);
    }

    /**
     * Function name: removeCar
     * 
     * @param username
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Get the Car to be removed. <br>
     *  2. Remove the Car object from the ArrayList <br>
     *  3. Save the ArrayList to Car.txt <br>    
     */
    public void removeCar(Car car) throws IOException {
        this.cars.remove(car);
        saveCars(carFile, this.cars);
    }

        /**
     * Function name: removeBooking
     * 
     * @param username
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Get the Booking to be removed. <br>
     *  2. Remove the Booking object from the ArrayList <br>
     *  3. Save the ArrayList to Booking.txt <br>    
     */
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
     *  1. Get the admin email
     *  2. Read the admin.txt file and find the matching admin record with the provided username.
     *  3. Return the admin object
     * @throws FileNotFoundException
     */
    public User findAdmin(String email) {
        User[] adminList;
        try {
            adminList = readAdminFile(adminFile);
            
            for (User admin: adminList) {
                if (admin.getEmail().toLowerCase().equals(email.toLowerCase())) {
                    return admin;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
    public Customer findCustomer(String email) throws FileNotFoundException {
        User[] customerList = readCustomerFile(customerFile);
        for (Customer customer: (Customer[]) customerList) {
            if (customer.getEmail().toLowerCase().equals(email.toLowerCase())) {
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
     * Function name: findCar
     * 
     * @param carID
     * @return
     * 
     * What it does:
     *  1. Get the Booking object
     *  2. Read the Booking.txt file and find the matching Booking record with the provided Booking object's email, car plate number and booking start
     *  3. Return the Booking object
     * @throws FileNotFoundException
     */
    public Booking findBooking(Booking b) throws FileNotFoundException {
        Booking[] bookingList = readBookingFile(bookingFile);
        for (Booking booking: bookingList) {
            if (booking.getEmail().equals(b.getEmail()) && 
                booking.getPlateNumber().equals(b.getPlateNumber()) && 
                booking.getBookingStart().isEqual(b.getBookingStart())) {
                return booking;
            }
        }

        return null;
    }
}