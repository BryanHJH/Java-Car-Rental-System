package Class;

public class Sedan extends Car{

    private int comfortability;

    public Sedan(String carType, String carName, int carSeat, double carPrice, String carStatus, String carTransmission, int comfortability) {
        
        super(carType, carName, carSeat, carPrice, carStatus, carTransmission);
        
        if (!(comfortability > 0 && comfortability < 11)) {
            throw new IllegalArgumentException("Comfortability score should be between 1 and 10.");
        }
        
        this.comfortability = comfortability;
        
    }
}