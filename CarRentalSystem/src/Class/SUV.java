package Class;

public class SUV extends Car {
    double carLoad;

    public SUV(String carType, String carName, int carSeat, double carPrice, String carStatus, double carLoad) {
        super(carType, carName, carSeat, carPrice, carStatus);
        this.carLoad = carLoad;
    }

}
