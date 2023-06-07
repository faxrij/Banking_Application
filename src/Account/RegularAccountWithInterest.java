package Account;

import Bank.Bank;
import Currency.Currency;
import Group.AccountGroup;
import Helper.CommonOperation.CommonOperationsForWithInterestAccounts;

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
        CommonOperationsForWithInterestAccounts commonOperationsForWithInterestAccounts = new CommonOperationsForWithInterestAccounts();

        if (commonOperationsForWithInterestAccounts.checkIfAnyTransactionsToday(bank, lastExchangeDay)) {
            return;
        }

        if (!(targetAccount.getClass().getSimpleName().equals("RegularAccountWithoutInterest"))) {
            System.out.println("You can only exchange between Regular Account");
            return;
        }
        commonOperationsForWithInterestAccounts.sendMoney(bank, targetAccount, amount, getBalance(), this);
    }
    public void setLastExchangeDay(int lastExchangeDay) {
        this.lastExchangeDay = lastExchangeDay;
    }
}

