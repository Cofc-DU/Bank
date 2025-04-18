import java.util.HashMap;


class Admin extends Person {
    private String password;
    private String userName; 
    private int key;
    

    public Admin(String firstName, String lastName,String userName, String password, int key){
        super(firstName, lastName);
        this.userName = userName;
        this.password = password;
        this.key = key;
    }

    public void addAdmin(String firstName, String lastName,String userName, String password, int key){
        Admin admin = new Admin(firstName, lastName, userName, password, key);
        Bank.adminInfo.put(admin.getKey(), admin);
    }

    public String getUserName(){
        return userName;
    }

    public int getKey(){
        return key;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String name){
        this.userName = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public static void addUser(String firstName, String lastName, String userName, String password, int clientID){
        User user = new User(firstName, lastName, userName, password, clientID);
        Bank.userInfo.put(user.getAccessNum(), user);
    }

    public static String removeUser(int key){
       User user = Bank.userInfo.get(key);
       Bank.userInfo.remove(user.getAccessNum());
       return user.toString() + ": removed";
    }

    public static User searchUser(int key){
        return Bank.userInfo.get(key);
    }

    public static void displayUserInfo(int key){
        User user = Bank.userInfo.get(key); 
        System.out.println(user.toString());
    }

    public static User searchName(String fName, String lName){
        for(User i : Bank.userInfo.values()){
            String first = i.getFirstName();
            String last = i.getLastName();
            if(first.equalsIgnoreCase(fName) && last.equalsIgnoreCase(lName)){
                return i;
            }
        }
        return null;
    }
    public static void addCheckingAccount(String accountNumber, String clientID, double initialBalance, double overdraftLimit){
        CheckingAccount account = new CheckingAccount(accountNumber, clientID, initialBalance, overdraftLimit);
        User user = Bank.userInfo.get(clientID);
        user.addChAccount(account);
    }

    public static void addSavingsAccount(String accountNumber, String clientID, double initialBalance, double interestRate){
        SavingsAccount account = new SavingsAccount(accountNumber, clientID, initialBalance, interestRate);
        User user = Bank.userInfo.get(clientID);
        user.addSaAccount(account);
    }

    public static void addCreditAccount(String accountNumber, String clientID, double initialBalance, double creditLimit, double interestRate){
        CreditAccount account = new CreditAccount(accountNumber, clientID, initialBalance, creditLimit, interestRate);
        User user = Bank.userInfo.get(clientID);
        user.addCrAccount(account);
    }

    public static User searchUserName(String userName){
        for(User i : Bank.userInfo.values()){
            String user = i.getUserName();
            if(user.equalsIgnoreCase(userName)){
                return i;
            }
        }
        return null;
    }

    public static Admin searchUserNameA(String userName){
        for(Admin i : Bank.adminInfo.values()){
            String user = i.getUserName();
            if(user.equalsIgnoreCase(userName)){
                return i;
            }
        }
        return null;
    }

    public static void printUsers(){
        for(User i : Bank.userInfo.values()){
            int key = i.getAccessNum();
            displayUserInfo(key); 
        }
    }
    
}