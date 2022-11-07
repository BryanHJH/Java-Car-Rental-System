package Class;

public class Sedan extends Car{
    private String carSeat;
    private double carPrice;
    private String carStatus;

    public Sedan(String carType, String carName, String carSeat, double carPrice, String carStatus) {
        super(carType, carName);
        this.carSeat = carSeat;
        this.carPrice = carPrice;
        this.carStatus = carStatus;
    }
}