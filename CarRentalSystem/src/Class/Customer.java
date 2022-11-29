package Class;

import java.util.ArrayList;
import java.util.Locale;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Customer extends User{

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy", Locale.US);
    
    public Customer(String fullname, String identification, String email, String contact, String username, String password) {
        super(fullname, identification, email, contact, username, password);
    }

    public Customer(Customer source) {
        super(source);
    }

    // Delete user
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
    public Booking bookCar(Car car, String bookingStart, String bookingEnd) throws IOException{
        return new Booking("Booking", "Pending", this.getEmail(), this.getIdentification(), car.getPlateNumber(), bookingStart, bookingEnd);
    }
    

    /*
    * Function name: returnCar
    * 
    * What it does:
    *  1. Let the dealer approve the return
    *  2. If approved, send a notification, then, add the date to car catalog?
    *  3. If rejected, semd a notification with a fine imposed
    */
    public Booking returnCar(Booking booking) {
        booking.setBookingType("Return");
        booking.setBookingStatus("Pending");
        return booking;
    }
    

}