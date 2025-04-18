public class SavingsAccount extends BankAccount{
    private double interestRate;
    
    public SavingsAccount(String accountNumber, String clientID, double initialBalance, double interestRate){
        super(accountNumber, clientID, initialBalance);
        this.interestRate = interestRate;
    }
    
    public double getInterestRate(){
        return interestRate;
    }

    @Override
    public void applyInterest(){
        deposit(getBalance() * interestRate);
    }

    public static double calcInterestRate(int creditScore) {
        if (creditScore >= 800) {
            return 0.03;
        } else if (creditScore >= 700) {
            return 0.02;
        } else if (creditScore >= 600) {
            return 0.01;
        } else {
            return 0.005;
        }
    }
    public static boolean canOpenAccount(double initialBalance) {
        return initialBalance >= 100;
    }
}