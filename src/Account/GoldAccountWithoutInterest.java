package Account;

import Bank.Bank;
import Currency.*;
import Group.AccountGroup;
import Helper.CommonOperation.CommonOperationsForWithoutInterestAccounts;
import Helper.ExchangeHelper;

public class GoldAccountWithoutInterest extends Account {

    public GoldAccountWithoutInterest(String accountNumber, AccountGroup accountGroup) {
        super(Currency.XAU, false, accountNumber, accountGroup);
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        // No interest calculation for gold accounts
        return 0;
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {
        if (!(targetAccount instanceof RegularAccountWithoutInterest || targetAccount instanceof GoldAccountWithInterest)) {
            System.out.println("You cannot exchange with this account");
            return;
        }
        CommonOperationsForWithoutInterestAccounts commonOperationsForWithoutInterestAccounts = new CommonOperationsForWithoutInterestAccounts();
        commonOperationsForWithoutInterestAccounts.sendMoney(bank, targetAccount, amount, this, Currency.XAU);
    }

    private void mainOperation(Bank bank, Account targetAccount, double amount) {
        CurrencyRates exchangeRates = bank.getCurrencyRates().get(Currency.TRY);
        ExchangeHelper exchangeHelper = new ExchangeHelper();
        double newBalance = exchangeHelper.exchange(exchangeRates, targetAccount, amount, getBalance(), getCurrency());
        setBalance(newBalance);
    }
    @Override
    public void setLastExchangeDay(int exchangeDay) {
        // CAN BE IMPLEMENTED FOR FUTURE DEVELOPMENTS
    }
}
