public class CreditAccount extends BankAccount {

    private double creditLimit;
    private double interestRate;

    public CreditAccount(String accountNumber, String clientID, double initialBalance, double creditLimit, double interestRate) {
        super(accountNumber, clientID, initialBalance);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

   
    public void purchase(double amount){
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid purchase amount.");
        } else if (getBalance() + amount > creditLimit) {
            throw new IllegalArgumentException("Transaction cancelled. Exceeds credit limit.");
        } else {
            super.withdraw(amount);
        }
    }

    public void makePayment(double amount, BankAccount paymentAccount) {
            if (paymentAccount == null) {
                throw new IllegalArgumentException("Invalid account.");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid payment amount.");
            }
            if (amount > paymentAccount.getBalance()) {
                throw new IllegalArgumentException("Insufficient funds in payment account.");
            }

            // Reduce credit balance (you owe less)
            balance -= amount;

            // Remove funds from the payment account
            paymentAccount.withdraw(amount);

            updateTransaction("payment", amount);
        }

    @Override
    public void applyInterest() {
        deposit(getBalance() * interestRate);
    }

    public boolean canOpenAccount(int creditScore) {
        return creditScore >= 600;
    }

    public static double calculateCreditLimit(int creditScore) {
        if (creditScore >= 800) {
            return 5000;
        } else if (creditScore >= 700) {
            return 3000;
        } else if (creditScore >= 600) {
            return 1000;
        } else {
            return 0;
        }
    }   

    public static double calculateInterestRate(int creditScore) {
        if (creditScore >= 800) {
            return 0.15;
        } else if (creditScore >= 700) {
            return 0.20;
        } else if (creditScore >= 600) {
            return 0.25;
        } else {
            return 0.30;
        }
    }


    public String toString() {
        return "CreditAccount [Account#: " + getAccountNumber() + ", ClientID: " + getClientID() + ", Balance Owed: $" + getBalance() + ", Credit Limit: $" + creditLimit + ", Interest Rate: " + (interestRate * 100) + "%]";
    }
}