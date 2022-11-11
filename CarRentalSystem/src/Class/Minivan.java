package Class;

public class Minivan extends Car{
    private String carSeat;
    private double carPrice;
    private String carStatus;
    private String carPlate;

    public Minivan(String carType, String carName, String carPlate, String carSeat, double carPrice, String carStatus) {
        super(carType, carName);
        this.carPlate = carPlate;
        this.carSeat = carSeat;
        this.carPrice = carPrice;
        this.carStatus = carStatus;
    }
}