package Class;

public class Sedan extends Car{
    private String carSeat;
    private double carPrice;
    private String carStatus;
    private String carPlate;

    public Sedan(String carType, String carName, String carSeat, String carPlate, double carPrice, String carStatus) {
        super(carType, carName);
        this.carPlate = carPlate;
        this.carSeat = carSeat;
        this.carPrice = carPrice;
        this.carStatus = carStatus;
    }
}