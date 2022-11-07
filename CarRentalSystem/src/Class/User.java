package Class;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.jasypt.util.password.BasicPasswordEncryptor;

public abstract class User {

    private String fullname;
    private String identification;
    private String email;
    private String contact;
    private String username;
    private String password;

    static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");

    public User(String fullname, String identification, String email, String contact, String username, String password) {
        this.fullname = fullname;
        this.identification = identification;
        this.email = email;
        this.contact = contact;
        this.username = username;
        this.password = passwordEncryptor.encryptPassword(password);
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdentification() {
        return this.identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = passwordEncryptor.encryptPassword(password);
    }
    
    public abstract boolean login(User[] arr, String username, String password);
        
    public abstract void viewCatalog();
}
