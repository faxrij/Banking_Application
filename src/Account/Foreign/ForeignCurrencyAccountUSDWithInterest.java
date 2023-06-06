package Account.Foreign;

import Account.Account;
import Bank.Bank;
import Currency.*;
import Group.AccountGroup;

public class ForeignCurrencyAccountUSDWithInterest extends Account {
    private int lastExchangeDay;
    public ForeignCurrencyAccountUSDWithInterest(Bank bank, String accountNumber, double interestRate, AccountGroup accountGroup) {
        super(Currency.USD, true, interestRate, accountNumber, accountGroup);
        lastExchangeDay = 0;
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        double futureBalance = getBalance() * Math.pow(1 + getInterestRate(), daysLater);
        System.out.println("Expected balance after " + daysLater + " days: " + futureBalance + " " + getCurrency());
        return futureBalance;
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {
        if(lastExchangeDay>=bank.getCurrentDate()) {
            System.out.println("You cannot exchange TODAY, move TIME forward");
            return;
        }
        if (!(targetAccount.getClass().getSimpleName().equals("ForeignCurrencyAccountUSDWithoutInterest"))) {
            System.out.println("You can only exchange between Account -> USD Without Interest");
            return;
        }
        double balance = getBalance() - amount;
        setBalance(balance);
        targetAccount.deposit(amount);
        System.out.println("Exchanged " + amount + " from" + getClass().getSimpleName() + " to " + targetAccount.getClass().getSimpleName()
                + " successful");
        lastExchangeDay = bank.getCurrentDate();
    }
}
