package Class;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Car{

    private String plateNumber;
    private String carBrand;
    private String carType;
    private int carSeat;
    private int carPrice;
    private ArrayList<LocalDate> bookedDates;
    private String carTransmission;

    /**
     * Constructor
     * @param plateNumber
     * @param carBrand
     * @param carSeat
     * @param carType
     * @param carPrice
     * @param carTransmission
     */
    public Car(String plateNumber, String carBrand, int carSeat, String carType, int carPrice, String carTransmission) {
        if (!(carTransmission.toUpperCase().equals("AUTO") || carTransmission.toUpperCase().equals("MANUAL"))) {
            throw new IllegalArgumentException("Transmission can only be AUTO or MANUAL");
        }

        if (carPrice < 0) {
            throw new IllegalArgumentException("Car rental price cannot be less than 0, we're not earning any profit at all!");
        }

        this.plateNumber = plateNumber;
        this.carBrand = carBrand;
        this.carSeat = carSeat;
        this.carType = carType;
        this.carPrice = carPrice;
        this.bookedDates = new ArrayList<>();
        this.carTransmission = carTransmission; 

    }

    /**
     * Copy Constructor
     * @param source
     */
    public Car(Car source) {
        this.plateNumber = source.getPlateNumber();
        this.carBrand = source.getCarBrand();
        this.carSeat = source.getCarSeat();
        this.carType = source.getCarType();
        this.carPrice = source.getCarPrice();
        this.carTransmission = source.getCarTransmission();
        this.bookedDates = source.getBookedDates();
    }

    // Getter and Setters
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

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getCarSeat() {
        return this.carSeat;
    }

    public void setCarSeat(int carSeat) {
        this.carSeat = carSeat;
    }

    public int getCarPrice() {
        return this.carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
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

    /**
     * Function name: addDates
     * @param date
     * @throws ParseException
     * 
     * What it does:
     *  1. Add dates after the return car is approved
     */
    public void addDates(LocalDate date) throws ParseException {
        if (this.bookedDates.size() == 0) {
            this.bookedDates.add(date);
        } else {
            int lastIndex = this.bookedDates.size() - 1;
            LocalDate lastDate = this.bookedDates.get(lastIndex);
            int comparisonResults = date.compareTo(lastDate);
            if (comparisonResults > 0) { // Meaning date is after the last item in this.availableDate
                this.bookedDates.add(date);
            } else if (comparisonResults == 0) { // Meaning date is the same as the current date and replaces the one in the ArrayList
                this.bookedDates.remove(lastIndex);
                this.bookedDates.add(date);
            } else { // Meaning date is before the last index and is now being placed as the second last item in the ArrayList
                this.bookedDates.add(lastIndex - 1, date);
            }
            
            this.bookedDates.sort(Comparator.naturalOrder());
        }
    }

    /**
     * Function name: removeDates
     * @param datesToBeRemoved
     * 
     * What it does: <br>
     *  1. Remove dates when payment for booking car is done
     */
    public void removeDate(ArrayList<LocalDate> datesToBeRemoved) {
        if (this.bookedDates.size() == 0) {
            throw new IllegalStateException("You cannot remove anything anymore!");
        }

        for (LocalDate date: datesToBeRemoved) {
            this.bookedDates.remove(date);
        }
    }

    /**
     * Name: checkAvailability
     * @param date
     * @return
     * 
     * What it does: <br>
     *  1. Loop through the ArrayList of bookedDates <br>
     *  2. return false if the car is already booked else true <br>
     */
    public boolean checkAvailability(LocalDate date) {
        for (LocalDate bookedDates: this.bookedDates) {
            if (bookedDates.isEqual(date)) {
                return false; // Means the car is booked at that date
            }
        }

        return true; // Means the car is not booked at that date
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(plateNumber, car.plateNumber) && Objects.equals(carBrand, car.carBrand) && Objects.equals(carType, car.carType) && carSeat == car.carSeat && carPrice == car.carPrice && Objects.equals(bookedDates, car.bookedDates) && Objects.equals(carTransmission, car.carTransmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber, carBrand, carType, carSeat, carPrice, bookedDates, carTransmission);
    }

    @Override
    public String toString() {
        return
            "Plate Number:" + getPlateNumber() + "\n" +
            "Car Brand:" + getCarBrand() + "\n" +
            "Car Type:" + getCarType() + "\n" +
            "Car Seat:" + getCarSeat() + "\n" +
            "Car Price:" + getCarPrice() + "\n" +
            "Booked Dates:" + getBookedDates() + "\n" +
            "Car Transmission:" + getCarTransmission() + "\n" ;
    }

}