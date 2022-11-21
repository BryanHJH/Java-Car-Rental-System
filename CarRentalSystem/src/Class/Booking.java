package Class;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Booking {
    
    private AtomicLong id;
    private String email, identification, plateNumber;
    private int carPrice, bookingPeriod;
    private LocalDate bookingStart, bookingEnd;

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.US);


    public Booking(AtomicLong id, String email, String identification, String plateNumber, int carPrice, String bookingStart, String bookingEnd) {
        this.id = id;
        this.email = email;
        this.identification = identification;
        this.plateNumber = plateNumber;
        this.carPrice = carPrice;
        this.bookingStart = LocalDate.parse(bookingStart, dateFormatter);
        this.bookingEnd = LocalDate.parse(bookingEnd, dateFormatter);
        this.bookingPeriod = Period.between(this.bookingStart, this.bookingEnd).getDays();
    }

    public Booking(Booking source) {
        this.id = source.getId();
        this.email = source.getEmail();
        this.identification = source.getIdentification();
        this.plateNumber = source.getPlateNumber();
        this.carPrice = source.getCarPrice();
        this.bookingStart = source.getBookingStart();
        this.bookingEnd = source.getBookingEnd();
        this.bookingPeriod = source.getBookingPeriod();
    }

    public AtomicLong getId() {
        return this.id;
    }

    public void setId(AtomicLong id) {
        this.id = id;
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

    public int getCarPrice() {
        return this.carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
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
            this.id.equals(booking.id) &&
            this.email.equals(booking.email) && 
            this.identification.equals(booking.identification) && 
            this.plateNumber.equals(booking.plateNumber) && 
            this.carPrice == booking.carPrice && 
            this.bookingPeriod == booking.bookingPeriod && 
            this.bookingStart.isEqual(booking.bookingStart) && 
            this.bookingEnd.isEqual(booking.bookingEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, identification, plateNumber, carPrice, bookingPeriod, bookingStart, bookingEnd);
    }


    @Override
    public String toString() {
        return
            "Booking ID: " + getId() + "\n" +
            "Email: " + getEmail() + "\n" +
            "Identification: " + getIdentification() + "\n" +
            "Plate Number: " + getPlateNumber() + "\n" +
            "Car Price: " + getCarPrice() + "\n" +
            "Booking Period: " + getBookingPeriod();
    }

}
