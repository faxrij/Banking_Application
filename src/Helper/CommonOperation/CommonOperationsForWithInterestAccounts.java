package Helper.CommonOperation;

import Account.Account;
import Bank.Bank;

public class CommonOperationsForWithInterestAccounts {
    public boolean checkIfAnyTransactionsToday(Bank bank, int lastExchangeDay) {
        if (lastExchangeDay >= bank.getCurrentDate()) {
            System.out.println("You cannot exchange TODAY, move TIME forward");
            return true;
        }
        return false;
    }

    public double sendMoney(Bank bank, Account targetAccount, double amount, double balance, Account account) {
        double newBalance = balance - amount;
        targetAccount.deposit(amount);
        System.out.println("Exchanged " + amount + " from" + account.getClass() + " to " + targetAccount.getClass().getSimpleName()
                + " successful");
        account.setBalance(newBalance);
        account.setLastExchangeDay(bank.getCurrentDate());
        return newBalance;
    }
}
