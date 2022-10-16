import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import Classes.Admin;
import Classes.User;

public class SetUp {
    
    static File adminFile = new File("C:\\Users\\2702b\\OneDrive - Asia Pacific University\\Degree (CYB)\\Year 2\\Object Oriented Development with Java\\Java Car Rental System\\Java-Car-Rental-System\\CarRentalSystem\\src\\Database\\Admin.txt");

    public static User[] readFile(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(file);
        User[] adminList = (User[]) gson.fromJson(reader, Admin[].class);
        return adminList;
    }

    public static void saveData(File file, ArrayList<User> arr) throws IOException {
        FileWriter fwriter = new FileWriter(file);
        
        try (BufferedWriter writer = new BufferedWriter(fwriter)) {
            Gson gson = new Gson();
            gson.toJson(arr, writer);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {

        User[] admins = {
            new Admin("Brian", "1111120293", "brian@car.com", "0192938202", "brian", "brian"),
            new Admin("Ryan", "23948723987", "ryan@car.com", "2309423", "ryan", "ryan"),
            new Admin("Taran", "32453242134", "taran@car.com", "0192938202", "taran", "taran"),
            new Admin("Rana", "r43534234", "rana@car.com", "0192938202", "rana", "rana")
        };

        ArrayList<User> adminArr = new ArrayList<>(Arrays.asList(admins));

        saveData(adminFile, adminArr);
        User[] testList = readFile(adminFile);
        User testUser = testList[0];
        System.out.println(testUser);
        System.out.println(testUser.login(testList, "brian", "dfal;sdkfj"));
    }

}
