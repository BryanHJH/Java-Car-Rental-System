import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Class.Admin;
import Class.Booking;
import Class.Car;
import Class.Customer;
import Class.GsonLocalDateAdapter;
import Class.Log;
import Class.Store;
import Class.User;

public class SetUp {

    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");
    static File customerFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Customer.txt");
    static File bookingFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Booking.txt");
    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");
    static File logFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Logs.txt");
    
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US);

    public static void saveUserData(File file, ArrayList<User> arr) throws IOException {
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
    
    public static void saveCarData(ArrayList<Car> car) throws IOException {
        FileWriter fwriter = new FileWriter(carFile);
        
        try (BufferedWriter writer = new BufferedWriter(fwriter)) {
            Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
                .create();
            gson.toJson(car, writer);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

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

    public static void main(String[] args) throws IOException {
    

        // User[] admins = {
        //     new Admin("Brian", "1111120293", "brian@car.com", "0192938202", "brian", "brian"),
        //     new Admin("Ryan", "23948723987", "ryan@car.com", "2309423", "ryan", "ryan"),
        //     new Admin("Taran", "32453242134", "taran@car.com", "0192938202", "taran", "taran"),
        //     new Admin("Rana", "r43534234", "rana@car.com", "0192938202", "rana", "rana")
        // };
        
        // User[] customers = {
        //     new Customer("Bryan", "1111120293", "bryan@gmail.com", "0192938202", "bryan", "bryan"),
        //     new Customer("Damian", "23948723987", "damian@yahoo.com", "2309423", "damian", "damian"),
        //     new Customer("Derek", "32453242134", "derek@hotmail.com", "0192938202", "derek", "derek"),
        //     new Customer("Lemon", "r43534234", "lemon@gmail.com", "0192938202", "lemon", "lemon")
        // };
        
        // Car[] cars = {
        //     new Car("BNY1122", "Toyota", 5, "Sedan", 25, "AUTO"),
        //     new Car("WYY2349", "Proton", 7, "SUV", 45, "AUTO"),
        //     new Car("W2349", "Porshe", 2, "Sports Car", 99, "MANUAL"),
        //     new Car("A1", "Ferari", 2, "Sports Car", 150, "MANUAL"),
        //     new Car("RRE1233", "Nissan", 15, "Minivan", 60, "MANUAL"),
        //     new Car("YYU3291", "Mercedes Benz", 5, "Sedan", 50, "AUTO")
        // };

        ArrayList<User> adminArr = new ArrayList<>(Arrays.asList(readAdminFile(adminFile)));
        ArrayList<User> custArr = new ArrayList<>(Arrays.asList(readCustomerFile(customerFile)));
        ArrayList<Car> carArr = new ArrayList<>(Arrays.asList(readCarFile(carFile)));
        // saveUserData(adminFile, adminArr);
        // saveUserData(customerFile, custArr);
        // saveCarData(carArr);


        Store testStore = new Store(adminArr, custArr, carArr, new ArrayList<Booking>(), new ArrayList<Log>());

        // testStore.addAdmin(new Admin("Christina", "12938749293", "christina@car.com", "0128382239", "christina", "christina"));
        // testStore.addAdmin(new Admin("Jimmy", "4323445324", "jimmy@car.com", "0128382240", "jimmy", "jimmy"));
        
        Admin tmpAdmin = (Admin) testStore.findAdmin("brian@car.com");
        Car tmpCar = testStore.findCar("BNY1122");
        Car tmpCar2 = testStore.findCar("A1");
        Customer tmpCustomer = testStore.findCustomer("bryan@gmail.com");
        Customer tmpCustomer2 = testStore.findCustomer("derek@hotmail.com");

        // System.out.println(tmpAdmin);
        // System.out.println();
        // System.out.println(tmpCar);
        // System.out.println();
        // System.out.println(tmpCustomer);
        // System.out.println();

        LocalDate bookingStart = LocalDate.parse("12-12-2022", formatter);
        LocalDate bookingEnd = LocalDate.parse("16-12-2022", formatter);
        LocalDate bookingStart2 = LocalDate.parse("13-12-2022", formatter);
        LocalDate bookingEnd2 = LocalDate.parse("20-12-2022", formatter);


        try {
            Log log = new Log(LocalDateTime.of(2022, 11, 30, 10, 30), tmpCustomer.getEmail(), "Login successful");
            Log log2 = new Log(LocalDateTime.of(2022, 11, 30, 10, 43), tmpCustomer.getEmail(), "Booking Created");
            Log log3 = new Log(LocalDateTime.of(2022, 11, 30, 10, 50), tmpCustomer.getEmail(), "Logout successful");
            Log log4 = new Log(LocalDateTime.of(2022, 12, 1, 8, 0), tmpAdmin.getEmail(), "Login successful");
            Log log5 = new Log(LocalDateTime.of(2022, 12, 1, 8, 5), tmpAdmin.getEmail(), "Approval successful");
            Log log6 = new Log(LocalDateTime.of(2022, 12, 1, 9, 0), tmpAdmin.getEmail(), "Logout successful");

            testStore.addLog(log);
            testStore.addLog(log2);
            testStore.addLog(log3);
            testStore.addLog(log4);
            testStore.addLog(log5);
            testStore.addLog(log6);
            Booking tmpBooking = tmpCustomer.bookCar(tmpCar, bookingStart, bookingEnd);
            System.out.println("Booking Created: \n");
            System.out.println(tmpBooking);
            tmpBooking = tmpAdmin.approve(tmpBooking, true);
            testStore.rentCar(tmpCar, bookingStart, bookingEnd);
            testStore.addBooking(tmpBooking);
            System.out.println("Booking Approved: \n");
            System.out.println(tmpBooking);
            System.out.println();
            System.out.println(tmpCar);
            System.out.println();
            System.out.println("Before Returning: \n");
            System.out.println(tmpBooking);
            tmpBooking = tmpCustomer.returnCar(tmpBooking);
            System.out.println("After Returning: \n");
            System.out.println(tmpBooking);
            testStore.updateBooking(tmpBooking);
            // System.out.println();
            // tmpBooking = tmpAdmin.approveReturn(tmpBooking, true);
            // System.out.println("After approving Return: \n");
            // System.out.println(tmpBooking);
            // testStore.updateBooking(tmpBooking);
            // System.out.println();
            // testStore.returnCar(tmpCar, bookingStart, bookingEnd);
            // System.out.println("After removing dates: \n");
            // System.out.println(tmpCar);
            // testStore.updateBooking(tmpBooking);

            Log log7 = new Log(LocalDateTime.of(2022, 11, 30, 10, 44), tmpCustomer2.getEmail(), "Login successful");
            Log log8 = new Log(LocalDateTime.of(2022, 11, 30, 10, 50), tmpCustomer2.getEmail(), "Booking Created");
            Log log9 = new Log(LocalDateTime.of(2022, 11, 30, 10, 54), tmpCustomer2.getEmail(), "Logout successful");
            testStore.addLog(log7);
            testStore.addLog(log8);
            testStore.addLog(log9);
            Booking tmpBooking2 = tmpCustomer2.bookCar(tmpCar2, bookingStart2, bookingEnd2);
            System.out.println("Booking 2 Created: \n");
            System.out.println(tmpBooking2);
            testStore.addBooking(tmpBooking2);
            // tmpBooking2 = tmpAdmin.approve(tmpBooking2, true);
            // testStore.rentCar(tmpCar2, bookingStart2, bookingEnd2);
            // testStore.addBooking(tmpBooking2);
            // System.out.println("Booking 2 Approved: \n");
            // System.out.println(tmpBooking2);
            // System.out.println();
            // System.out.println(tmpCar2);
            // System.out.println();

        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //User testUser = testList[1];
        //System.out.println(testUser);
        //System.out.println(testUser.getClass());
        //System.out.println(testUser.login(testList, "brian@staff.com", "dfal;sdkfj"));
    }

    
}