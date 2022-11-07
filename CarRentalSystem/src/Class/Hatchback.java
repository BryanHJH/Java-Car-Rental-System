package Class;

public class Hatchback extends Car {
    private String carSeat;
    private double carPrice;
    private String carStatus;

    public Hatchback(String carType, String carName, String carSeat, double carPrice, String carStatus) {
        super(carType, carName);
        this.carSeat = carSeat;
        this.carPrice = carPrice;
        this.carStatus = carStatus;
    }
}