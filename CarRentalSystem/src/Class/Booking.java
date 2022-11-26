package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Booking {
    
    final AtomicLong identifier = new AtomicLong(0);
    private String bookingType, bookingStatus, email, identification, plateNumber;
    private int totalPrice, bookingPeriod;
    private LocalDate bookingStart, bookingEnd;

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US);

    static File carFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Car.txt");

    public static Car[] readCarFile(File file) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
            .create();
        Reader reader = new FileReader(file);
        Car[] carList = gson.fromJson(reader, Car[].class);
        return carList;
    }

    public Booking(String bookingType, String bookingStatus, String email, String identification, String plateNumber, String bookingStart, String bookingEnd) {
        this.identifier.incrementAndGet();

        if (!(bookingType.toLowerCase().equals("booking") || bookingType.toLowerCase().equals("return") || bookingType.toLowerCase().equals("damaged"))) throw new IllegalArgumentException("Wrong booking type given");

        if (!(bookingStatus.toLowerCase().equals("approved") || bookingStatus.toLowerCase().equals("rejected") || bookingStatus.toLowerCase().equals("pending") || bookingStatus.toLowerCase().equals("paid") || bookingStatus.toLowerCase().equals("returned"))) throw new IllegalArgumentException("Invalid booking status");

        this.bookingType = bookingType;
        this.bookingStatus = bookingStatus;
        this.email = email;
        this.identification = identification;
        this.plateNumber = plateNumber;
        this.bookingStart = LocalDate.parse(bookingStart, dateFormatter);
        this.bookingEnd = LocalDate.parse(bookingEnd, dateFormatter);
        this.bookingPeriod = Period.between(this.bookingStart, this.bookingEnd).getDays();

        try {
            Car[] CarList = readCarFile(carFile);
            for (Car car: CarList) {
                if (car.getPlateNumber().equals(this.plateNumber)) {
                    this.totalPrice = Math.round(car.getCarPrice() * this.bookingPeriod);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Booking(Booking source) {
        this.bookingType = source.getBookingType();
        this.bookingStatus = source.getBookingStatus();
        this.email = source.getEmail();
        this.identification = source.getIdentification();
        this.plateNumber = source.getPlateNumber();
        this.totalPrice = source.getTotalPrice();
        this.bookingStart = source.getBookingStart();
        this.bookingEnd = source.getBookingEnd();
        this.bookingPeriod = source.getBookingPeriod();
    }

    public AtomicLong getIdentifier() {
        return this.identifier;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentification() {
        return this.identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getBookingStart() {
        return this.bookingStart;
    }

    public void setBookingStart(LocalDate bookingStart) {
        this.bookingStart = bookingStart;
    }

    public LocalDate getBookingEnd() {
        return this.bookingEnd;
    }

    public void setBookingEnd(LocalDate bookingEnd) {
        this.bookingEnd = bookingEnd;
    }

    public int getBookingPeriod() {
        return this.bookingPeriod;
    }

    public void setBookingPeriod(int bookingPeriod) {
        this.bookingPeriod = bookingPeriod;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Booking)) {
            return false;
        }
        Booking booking = (Booking) o;
        return
            this.identifier.equals(booking.identifier) &&
            this.email.equals(booking.email) && 
            this.identification.equals(booking.identification) && 
            this.plateNumber.equals(booking.plateNumber) && 
            this.totalPrice == booking.totalPrice && 
            this.bookingPeriod == booking.bookingPeriod && 
            this.bookingStart.isEqual(booking.bookingStart) && 
            this.bookingEnd.isEqual(booking.bookingEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, email, identification, plateNumber, bookingPeriod, bookingStart, bookingEnd);
    }


    @Override
    public String toString() {
        return
            "Booking ID: " + getIdentifier() + "\n" +
            "Booking Type: " + getBookingType() + "\n" +
            "Booking Status: " + getBookingStatus() + "\n" +
            "Email: " + getEmail() + "\n" +
            "Identification: " + getIdentification() + "\n" +
            "Plate Number: " + getPlateNumber() + "\n" +
            "Car Price: " + getTotalPrice() + "\n" +
            "Booking Period: " + getBookingPeriod();
    }

}
