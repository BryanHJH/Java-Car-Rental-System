package Class;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Log {
    
    private Timestamp timestamp;
    private String userEmail;
    private String activity;

    public static Date convertToDateUsingInstant(LocalDateTime date) {
        return (Date) java.util.Date.from(date
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public Log(LocalDateTime timestamp, String userEmail, String activity) {
        this.timestamp = new Timestamp(convertToDateUsingInstant(timestamp).getTime());
        this.userEmail = userEmail;
        this.activity = activity;
    }


    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    
}
