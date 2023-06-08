package Account.Foreign;

import Account.Account;
import Bank.Bank;
import Currency.*;
import Group.AccountGroup;
import Helper.CommonOperation.CommonOperationsForWithInterestAccounts;

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
        CommonOperationsForWithInterestAccounts commonOperationsForWithInterestAccounts = new CommonOperationsForWithInterestAccounts();

        if (commonOperationsForWithInterestAccounts.checkIfAnyTransactionsToday(bank, lastExchangeDay)) {
            return;
        }
        if (!(targetAccount instanceof ForeignCurrencyAccountUSDWithoutInterest || targetAccount instanceof ForeignCurrencyAccountUSDWithInterest)) {
            System.out.println("You can only exchange between Regular Account");
            return;
        }
        commonOperationsForWithInterestAccounts.sendMoney(bank, targetAccount, amount, getBalance(), this);
    }

    public void setLastExchangeDay(int lastExchangeDay) {
        this.lastExchangeDay = lastExchangeDay;
    }

}
