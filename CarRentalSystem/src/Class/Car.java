package Class;

import java.util.ArrayList;

public class Car{
    private String carType;
    private String carName;


    private ArrayList<Car> bookingHistory;

    public Car(String carType, String carName) {
        this.carType = carType;
        this.carName = carName;
    }
    
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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