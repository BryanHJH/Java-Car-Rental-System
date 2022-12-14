package Class;

import java.io.IOException;
import java.time.LocalDate;

public class Customer extends User{
    
    /**
     * Constructor
     * @param fullname
     * @param identification
     * @param email
     * @param contact
     * @param username
     * @param password
     */
    public Customer(String fullname, String identification, String email, String contact, String username, String password) {
        super(fullname, identification, email, contact, username, password);
    }

    /**
     * Copy Constructor
     * @param source
     */
    public Customer(Customer source) {
        super(source);
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