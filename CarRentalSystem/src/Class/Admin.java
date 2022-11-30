package Class;

import java.util.ArrayList;

public class Admin extends User {
    
    public Admin(String fullname, String identification, String email, String contact, String username, String password) {
        super(fullname, identification, email, contact, username, password);
    }

    public Admin(Admin source) {
        super(source);
    }

    // Use for reference for LoginController
    /** @Override
    public boolean login(User[] arr, String email, String password) {

        boolean result = false;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        for (User user: arr) {
            if (user.getEmail().equals(email)) {
                if (passwordEncryptor.checkPassword(password, user.getPassword())) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        return result;
    } **/
    
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
     * Function name: approveBooking
     * @return
     * 
     * What it does:
     *  1. Admin will decide whether to approve the booking or not
     * @throws IllegalAccessException
     */
    public Booking approve(Booking booking, boolean approval) throws IllegalAccessException {
        if (booking.getBookingType().toLowerCase().equals("booking") || booking.getBookingType().toLowerCase().equals("return")) {
            if (approval) { // IF booking is APPROVED
                booking.setBookingStatus("Approved");
            } else { // IF booking is REJECTED
                booking.setBookingStatus("Rejected");
            }
        } else if (booking.getBookingType().toLowerCase().equals("damaged")) {
            if (approval) { // IF fines are PAID
                booking.setBookingStatus("Paid");
            }  else { // IF fines are not PAID
                booking.setBookingStatus("Pending");
            }
        } else {
            throw new IllegalAccessException("Unknown booking type");
        }

        return booking;
    }

    /**
     * Function name: imposeFines
     * @return
     * 
     * What it does:
     *  1. Returns a double that will be added to the price for Damaged Booking record
     */
    public int imposeFines(Booking booking) {
        int fines = (int) Math.round(booking.getBookingPeriod() * booking.getTotalPrice() * 0.35);
        booking.setTotalFines(fines);
        return fines;
    }

    /**
     * Function name: approveReturn
     * @return
     * 
     * What it does:
     *  1. If admin approves nothing happens
     *  2. If admin rejects, imposeFines() is run
     */
    public Booking approveReturn(Booking booking, boolean approval) {
        if (!approval) { // Car is damaged
            booking.setBookingStatus("Pending");
            booking.setBookingType("Damaged");
            booking.setTotalPrice(booking.getTotalPrice() + imposeFines(booking));
        } else { // Car is not damaged
            booking.setBookingStatus("Returned");
        }

        return booking;
    }

}