public class User extends Person {
    private int pin;
    private int clientID;
    private String userName;
    private String password;
    private BankAccount[] accounts;

    public User(String firstName, String lastName, String userName, String password, int clientID) {
        super(firstName, lastName);
        this.userName = userName;
        this.password = password;
        this.clientID = clientID;
        this.pin = 0000;
        accounts = new BankAccount[3];
        
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public int getAccessNum(){
        return clientID;
    }

    public int getPin(){
        return pin;
    }

    public void addChAccount(CheckingAccount account){
        accounts[0] = account;
    }

    public void addSaAccount(SavingsAccount account){
        accounts[1] = account;
    }
    
    public void addCrAccount(CreditAccount account){
        accounts[2] = account;
    }

    public void setUsername(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setPin(int pin){
        this.pin = pin;
    }

    public void setAccessNum(int clientID){
        this.clientID = clientID;
    }
    
    @Override
    public String toString(){
        return "User{" + getFirstName() + ", " + getLastName() + ", " + String.valueOf(clientID) +", " + userName + ", " + password + ", " + String.valueOf(pin);
    }
}

