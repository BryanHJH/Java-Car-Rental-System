package Class;

public class SUV extends Car {
    private String carSeat;
    private double carPrice;
    private String carStatus;
    private String carPlate;

    public SUV(String carType, String carName, String carPlate, String carSeat, double carPrice, String carStatus) {
        super(carType, carName);
        this.carPlate = carPlate;
        this.carSeat = carSeat;
        this.carPrice = carPrice;
        this.carStatus = carStatus;
    }
}
