package Helper;

import Account.Account;
import Currency.Currency;
import Currency.CurrencyRates;

public class ExchangeHelper {
    public double exchange(CurrencyRates exchangeRates, Account targetAccount, double amount, double balance, Currency currency) {
        double exchangeRate = exchangeRates.getExchangeRate(targetAccount.getCurrency());
        double exchangedAmount = amount * exchangeRate;

        // Deduct the exchanged amount from the account's balance in the current currency
        double newBalance = balance - amount;
        targetAccount.deposit(exchangedAmount);
        System.out.println("Exchanged " + amount + " " + currency + " to " + exchangedAmount + " " + targetAccount.getCurrency());
        return newBalance;
    }
}
