package Class;

public class Hatchback extends Car {
    private String carSeat;
    private double carPrice;
    private String carStatus;
    private String carPlate;

    public Hatchback(String carType, String carName, String carPlate, String carSeat, double carPrice, String carStatus) {
        super(carType, carName);
        this.carPlate = carPlate;
        this.carSeat = carSeat;
        this.carPrice = carPrice;
        this.carStatus = carStatus;
    }
}