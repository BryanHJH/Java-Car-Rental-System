package Class;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class User {

    private String fullname;
    private String identification;
    private String email;
    private String contact;
    private String username;
    private String password;

    static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    
    /**
     * Constructor
     * @param fullname
     * @param identification
     * @param email
     * @param contact
     * @param username
     * @param password
     */
    public User(String fullname, String identification, String email, String contact, String username, String password) {
        this.fullname = fullname;
        this.identification = identification;
        this.email = email;
        this.contact = contact;
        this.username = username;
        this.password = passwordEncryptor.encryptPassword(password);
    }

    /**
     * Copy Constructor
     * @param source
     */
    public User(User source) {
        this.fullname = source.getUsername();
        this.identification = source.getIdentification();
        this.email = source.getEmail();
        this.contact = source.getContact();
        this.username = source.getUsername();
        this.password = source.getPassword();
    }

    // Getter and Setter
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
    
    public boolean login(User user, String password) {
        boolean result = false;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
            result = true;
        } else {
            result = false;
        }

        return result;
    };

    @Override
    public String toString() {
        return  "Name: " + this.getFullname() + "\n" +
                "ID: " + this.getIdentification() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Contact Number: " + this.getContact() + "\n" +
                "Username: " + this.getUsername();
    }
}
