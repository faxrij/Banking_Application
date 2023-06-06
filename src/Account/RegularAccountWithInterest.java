package Account;

import Bank.Bank;
import Currency.Currency;
import Group.AccountGroup;

public class RegularAccountWithInterest extends Account {
    private int lastExchangeDay;

    public RegularAccountWithInterest(String accountNumber, double interestRate, AccountGroup accountGroup) {
        super(Currency.TRY, true, interestRate, accountNumber, accountGroup);
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        double futureBalance = getBalance() * (Math.pow(((1 + getInterestRate())), daysLater));
        System.out.println("Future balance after " + daysLater + " days: " + futureBalance);
        return futureBalance;
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {
        if(lastExchangeDay>=bank.getCurrentDate()) {
            System.out.println("You cannot exchange TODAY, move TIME forward");
            return;
        }
        if (!(targetAccount.getClass().getSimpleName().equals("RegularAccountWithoutInterest"))) {
            System.out.println("You can only exchange between Regular Account");
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

