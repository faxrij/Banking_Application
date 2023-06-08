package Account;

import Bank.Bank;
import Currency.Currency;
import Group.AccountGroup;
import Interface.AccountComponent;

public abstract class Account implements AccountComponent {
    private final Currency currency;

    private final boolean hasInterest;

    private Double interestRate;

    private final String accountNumber;
    private double balance;
    private final AccountGroup rootAccountGroup;


    public Account(Currency currency, boolean hasInterest, String accountNumber, AccountGroup rootAccountGroup) {
        this.currency = currency;
        this.hasInterest = hasInterest;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.rootAccountGroup = rootAccountGroup;
    }

    public boolean isHasInterest() {
        return hasInterest;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Account(Currency currency, boolean hasInterest, Double interestRate, String accountNumber, AccountGroup accountGroup) {
        this.currency = currency;
        this.hasInterest = hasInterest;
        this.interestRate = interestRate;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.rootAccountGroup = accountGroup;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit of " + amount + " successful. New balance: " + balance);
    }

    public abstract double calculateFutureBalance(int daysLater);

    public abstract void exchangeToCurrency(Bank bank, Account targetAccount, double amount);
    public abstract void setLastExchangeDay(int exchangeDay);
}
