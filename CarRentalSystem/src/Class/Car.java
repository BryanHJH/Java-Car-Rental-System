package Class;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

public class Car{

    private String plateNumber;
    private String carBrand;
    private String carType;
    private int carSeat;
    private double carPrice;
    private String carStatus;
    private ArrayList<LocalDate> bookedDates;
    private String carTransmission;

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US);

    public Car(String plateNumber, String carBrand, int carSeat, String carType, double carPrice, String carStatus, String carTransmission) {
        if (carTransmission.toUpperCase().equals("AUTO") || carTransmission.toUpperCase().equals("MANUAL") ) {
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
        
        this.carStatus = carStatus;
        this.bookedDates = new ArrayList<>();

        this.carTransmission = carTransmission; 

    }

    public Car(Car source) {
        this.plateNumber = source.getPlateNumber();
        this.carBrand = source.getCarBrand();
        this.carSeat = source.getCarSeat();
        this.carType = source.getCarType();
        this.carPrice = source.getCarPrice();
        this.carStatus = source.getCarStatus();
        this.carTransmission = source.getCarTransmission();
        this.bookedDates = source.getBookedDates();
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
    public void addDates(LocalDate date) throws ParseException {
        //LocalDate newDate = LocalDate.parse(date, dateFormatter);

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
                this.bookedDates.add(lastIndex - 2, date);
            }

            this.bookedDates.sort(Comparator.naturalOrder());
        }

        // Not sure this part is needed or not, above part is taken from my old Java Assignment, require testing
        /** else if (this.bookedDates.size() == 1) {
            if (newDate.isAfter(this.bookedDates.get(0))) {
                this.bookedDates.add(newDate);
            }
        }**/
    }


    /*
    * Function name: removeDates
    * 
    * What it does:
    *  1. Remove dates when payment for booking car is done
    */
    public void removeDate(String date) {
        if (this.bookedDates.size() == 0) {
            throw new IllegalStateException("You cannot remove anything anymore!");
        }

        for (int i = 0; i < this.bookedDates.size(); i++) {
            if (LocalDate.parse(date, dateFormatter).isEqual(this.bookedDates.get(i))) {
                this.bookedDates.remove(i);
            }
        }
    }

    /**
     * Name: checkAvailability
     * @param date
     * @return
     * 
     * What it does:
     *  1. Loop through the ArrayList of bookedDates
     *  2. return false if the car is already booked else true
     */
    public boolean checkAvailability(LocalDate date) {
        // LocalDate bookingDate = LocalDate.parse(date, dateFormatter);

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
        return Objects.equals(plateNumber, car.plateNumber) && Objects.equals(carBrand, car.carBrand) && Objects.equals(carType, car.carType) && carSeat == car.carSeat && carPrice == car.carPrice && Objects.equals(carStatus, car.carStatus) && Objects.equals(bookedDates, car.bookedDates) && Objects.equals(carTransmission, car.carTransmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber, carBrand, carType, carSeat, carPrice, carStatus, bookedDates, carTransmission);
    }


    /*
    * Function name: carDetails / carCatalog /override the toString method
    * 
    * What it does:
    *  1. Show the information of car
    *  2. Information grouped by the types of car
    *  3. After choosing types of car, customer should see the picture of the car? (discuss with bryan),
    *  car model, seats, price
    * 
    */
    @Override
    public String toString() {
        return
            "Plate Number:" + getPlateNumber() + "\n" +
            "Car Brand:" + getCarBrand() + "\n" +
            "Car Type:" + getCarType() + "\n" +
            "Car Seat:" + getCarSeat() + "\n" +
            "Car Price:" + getCarPrice() + "\n" +
            "Car Status:" + getCarStatus() + "\n" +
            "Booked Dates:" + getBookedDates() + "\n" +
            "Car Transmission:" + getCarTransmission() + "\n" ;
    }


}