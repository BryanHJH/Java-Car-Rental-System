package Class;

import java.util.ArrayList;
import java.util.Locale;
import java.io.IOException;
import java.time.LocalDate;
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


    /**
     * Function name: bookCar
     * @param car
     * @param bookingStart
     * @param bookingEnd
     * @return
     * @throws IOException
     * 
     * What it does: <br>
     *  1. Creates a new booking using a Car object and 2 strings denoting the beginning and the end of the rental period <br>
     */
    public Booking bookCar(Car car, LocalDate bookingStart, LocalDate bookingEnd) throws IOException{
        return new Booking("Booking", "Pending", this.getEmail(), this.getIdentification(), car.getPlateNumber(), bookingStart, bookingEnd);
    }
    

    /**
     * Function name: returnCar
     * @param booking
     * @return
     * 
     * What it does: <br>
     *  1. Sets the Booking Status and Type to Pending and Return respectively
     */
    public Booking returnCar(Booking booking) {
        booking.setBookingType("Return");
        booking.setBookingStatus("Pending");
        return booking;
    }
    

}