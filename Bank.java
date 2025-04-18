import java.util.HashMap;
import java.util.Map;

public class Bank{
    public static Map<Integer, User> userInfo = new HashMap<>();
    public static Map<Integer, Admin> adminInfo = new HashMap<>();


    public static void addAdmin(String firstName, String lastName,String userName, String password, int key){
        Admin admin = new Admin(firstName, lastName, userName, password, key);
        adminInfo.put(key, admin);
    }

    public static void addUser(String firstName, String lastName, String userName, String password, int clientID){
        User user = new User(firstName, lastName, userName, password, clientID);
        userInfo.put(user.getAccessNum(), user);
    }

}