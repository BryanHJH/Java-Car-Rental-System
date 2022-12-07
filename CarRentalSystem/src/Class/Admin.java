package Class;

import java.time.LocalDate;

public class Admin extends User {
    
    /**
     * Constructor
     * @param fullname
     * @param identification
     * @param email
     * @param contact
     * @param username
     * @param password
     */
    public Admin(String fullname, String identification, String email, String contact, String username, String password) {
        super(fullname, identification, email, contact, username, password);
    }

    /**
     * Copy Constructor
     * @param source
     */
    public Admin(Admin source) {
        super(source);
    }

    /**
     * Function name: approveBooking
     * @return
     * 
     * What it does: <br>
     *  1. Admin will decide whether to approve the booking or not <br>
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
     * What it does: <br>
     *  1. Returns a double that will be added to the price for Damaged Booking record <br>
     */
    public int imposeFines(Booking booking) {
        int fines = (int) Math.round(booking.getBookingPeriod() * booking.getTotalPrice() * 0.35);
        booking.setTotalFines(fines);
        return fines;
    }

    public int imposeOvertimeCharges(Booking booking) {
        int charges = (int) Math.round(booking.getBookingPeriod() * 50);
        booking.setTotalOvertimeCharges(charges);
        return charges;
    }

    /**
     * Function name: approveReturn
     * @return
     * 
     * What it does: <br>
     *  1. If admin approves nothing happens <br>
     *  2. If admin rejects, imposeFines() is run
     */
    public Booking approveReturn(Booking booking, boolean approval) {
        if (approval) { // Car is not damaged
            booking.setBookingStatus("Returned");

            if (booking.getBookingEnd().isBefore(LocalDate.now())) { // Charging overtime return fees if customer return the car late
                booking.setTotalPrice(booking.getTotalPrice() + imposeOvertimeCharges(booking));
            }

        } else { // Car is damaged
            booking.setBookingStatus("Pending");
            booking.setBookingType("Damaged");
            booking.setTotalPrice(booking.getTotalPrice() + imposeFines(booking));
        }

        return booking;
    }

}