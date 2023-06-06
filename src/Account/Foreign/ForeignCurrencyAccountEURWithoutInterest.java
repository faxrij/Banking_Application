package Account.Foreign;

import Account.Account;
import Bank.Bank;
import Currency.*;
import Group.AccountGroup;
import Helper.ExchangeHelper;

public class ForeignCurrencyAccountEURWithoutInterest extends Account {
    public ForeignCurrencyAccountEURWithoutInterest(String accountNumber, AccountGroup accountGroup) {
        super(Currency.EUR, false, accountNumber, accountGroup);
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        double futureBalance = getBalance();
        System.out.println("Expected balance after " + daysLater + " days: " + futureBalance + " " + getCurrency());
        return futureBalance;
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {
        CurrencyRates exchangeRates = bank.getCurrencyRates().get(Currency.EUR);
        ExchangeHelper exchangeHelper = new ExchangeHelper();
        double newBalance = exchangeHelper.exchange(exchangeRates, targetAccount, amount, getBalance(), getCurrency());
        setBalance(newBalance);
    }
}
