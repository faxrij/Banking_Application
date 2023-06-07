package Account.Foreign;

import Account.Account;
import Account.RegularAccountWithoutInterest;
import Bank.Bank;
import Currency.*;
import Group.AccountGroup;
import Helper.CommonOperation.CommonOperationsForWithoutInterestAccounts;

public class ForeignCurrencyAccountUSDWithoutInterest extends Account {
    public ForeignCurrencyAccountUSDWithoutInterest(String accountNumber, AccountGroup accountGroup) {
        super(Currency.USD, false, accountNumber, accountGroup);
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {

        if (!(targetAccount instanceof ForeignCurrencyAccountUSDWithInterest || targetAccount instanceof RegularAccountWithoutInterest)) {
            System.out.println("You cannot exchange with this account");
            return;
        }
        CommonOperationsForWithoutInterestAccounts commonOperationsForWithoutInterestAccounts = new CommonOperationsForWithoutInterestAccounts();
        commonOperationsForWithoutInterestAccounts.sendMoney(bank, targetAccount, amount, this, Currency.EUR);
    }

//    private void mainOperation(Bank bank, Account targetAccount, double amount) {
//        CurrencyRates exchangeRates = bank.getCurrencyRates().get(Currency.EUR);
//        ExchangeHelper exchangeHelper = new ExchangeHelper();
//        double newBalance = exchangeHelper.exchange(exchangeRates, targetAccount, amount, getBalance(), getCurrency());
//        setBalance(newBalance);
//    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        double futureBalance = getBalance();
        System.out.println("Expected balance after " + daysLater + " days: " + futureBalance + " " + getCurrency());
        return futureBalance;
    }

    @Override
    public void setLastExchangeDay(int exchangeDay) {
        // CAN BE IMPLEMENTED FOR FUTURE DEVELOPMENTS
    }
}

