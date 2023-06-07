package Helper.CommonOperation;

import Account.Account;
import Bank.Bank;
import Currency.Currency;
import Currency.CurrencyRates;
import Helper.ExchangeHelper;

public class CommonOperationsForWithoutInterestAccounts {
    public void sendMoney(Bank bank, Account targetAccount, double amount, Account account, Currency wantedCurrency) {
        CurrencyRates exchangeRates = bank.getCurrencyRates().get(wantedCurrency);
        ExchangeHelper exchangeHelper = new ExchangeHelper();
        double newBalance = exchangeHelper.exchange(exchangeRates, targetAccount, amount, account.getBalance(), account.getCurrency());
        account.setBalance(newBalance);
    }
}
