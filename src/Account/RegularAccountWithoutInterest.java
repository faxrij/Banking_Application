package Account;

import Bank.Bank;
import Currency.*;
import Group.AccountGroup;
import Helper.CommonOperation.CommonOperationsForWithoutInterestAccounts;

public class RegularAccountWithoutInterest extends Account {

    public RegularAccountWithoutInterest(String accountNumber, AccountGroup accountGroup) {
        super(Currency.TRY, false, accountNumber, accountGroup);
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        double futureBalance = getBalance();
        System.out.println("Expected balance after " + daysLater + " days: " + futureBalance);
        return futureBalance;
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {
        CommonOperationsForWithoutInterestAccounts commonOperationsForWithoutInterestAccounts = new CommonOperationsForWithoutInterestAccounts();
        if (targetAccount instanceof RegularAccountWithInterest) {
            commonOperationsForWithoutInterestAccounts.sendMoney(bank, targetAccount, amount, this, Currency.TRY);
            return;
        }

        if (!(targetAccount.getClass().getSimpleName().endsWith("WithoutInterest"))) {
            System.out.println("You cannot exchange with this Account");
            return;

        }
        commonOperationsForWithoutInterestAccounts.sendMoney(bank, targetAccount, amount, this, Currency.TRY);
    }

    @Override
    public void setLastExchangeDay(int exchangeDay) {
        // CAN BE IMPLEMENTED FOR FUTURE DEVELOPMENTS
    }
}
