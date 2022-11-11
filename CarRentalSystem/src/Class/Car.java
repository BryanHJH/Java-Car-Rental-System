package Class;

import java.time.LocalDate;
import java.util.ArrayList;

public class Car{

    private String plateNumber;
    private String carBrand;
    private int carSeat;
    private double carPrice;
    private String carStatus;
    private ArrayList<LocalDate> bookedDates;
    private String carTransmission;


    private ArrayList<Car> bookingHistory;

    public Car(String plateNumber, String carBrand, int carSeat, double carPrice, String carStatus, String carTransmission) {
        if (carTransmission.toUpperCase().equals("AUTO") || carTransmission.toUpperCase().equals("MANUAL") ) {
            throw new IllegalArgumentException("Transmission can only be AUTO or MANUAL");
        }

        if (carPrice < 0) {
            throw new IllegalArgumentException("Car rental price cannot be less than 0, we're not earning any profit at all!");
        }

        this.plateNumber = plateNumber;
        this.carBrand = carBrand;
        this.carSeat = carSeat;
        
        this.carPrice = carPrice;
        
        this.carStatus = carStatus;
        this.bookedDates = new ArrayList<>();

        this.carTransmission = carTransmission; 

    }

    // Prepare a Copy Constructor

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCarBrand() {
        return this.carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getCarSeat() {
        return this.carSeat;
    }

    public void setCarSeat(int carSeat) {
        this.carSeat = carSeat;
    }

    public double getCarPrice() {
        return this.carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarStatus() {
        return this.carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public ArrayList<Car> getBookingHistory() {
        return this.bookingHistory;
    }

    public void setBookingHistory(ArrayList<Car> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    public ArrayList<LocalDate> getBookedDates() {
        return this.bookedDates;
    }

    public void setBookedDates(ArrayList<LocalDate> bookedDates) {
        this.bookedDates = bookedDates;
    }

    public String getCarTransmission() {
        return this.carTransmission;
    }

    public void setCarTransmission(String carTransmission) {
        this.carTransmission = carTransmission;
    }

/*
 * Function name: addDates
 * 
 * What it does:
 *  1. Add dates after the return car is approved
 */

/*
 * Function name: removeDates
 * 
 * What it does:
 *  1. Remove dates when payment for booking car is done
 */

/*
 * Function name: carDetails / carCatalog
 * 
 * What it does:
 *  1. Show the information of car
 *  2. Information grouped by the types of car
 *  3. After choosing types of car, customer should see the picture of the car? (discuss with bryan),
 *  car model, seats, price
 * 
 */

/*
 * Discuss with Bryan
 * Regarding creating classes for each car types
 * 
 * Format of car information: (discuss with Bryan)
 *  1. Car types (Sedan, hatchback, Minivans, SUVs)
 *  2. Car Name / Model
 *  3. Seats (how many seater)
 *  4. Price (subject to how many days)
 *  5. Status (available or not available)
 */
}