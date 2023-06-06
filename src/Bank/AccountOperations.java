package Bank;

import Account.Account;
import Currency.Currency;
import Factory.CreateAccountFactory;
import Factory.Foreign.ForeignCurrencyAccountEURWithInterestFactory;
import Factory.Foreign.ForeignCurrencyAccountEURWithoutInterestFactory;
import Factory.Foreign.ForeignCurrencyAccountUSDWithInterestFactory;
import Factory.Foreign.ForeignCurrencyAccountUSDWithoutInterestFactory;
import Factory.Regular.RegularAccountWithInterestFactory;
import Factory.Regular.RegularAccountWithoutInterestFactory;
import Group.AccountGroup;

public class AccountOperations {
    private final CreateAccountFactory createAccountFactory;
    private final Bank bank;

    public AccountOperations(Bank bank) {
        createAccountFactory = new CreateAccountFactory();
        this.bank = bank;
    }

    public Account createAccount(String accountType) {
        Account account = null;
        AccountGroup rootGroup = bank.getCurrentClient().getRootAccountGroup();
        switch (accountType) {
            case "RegularAccountWithoutInterest" ->
                    account = createAccountFactory.createAccount(bank, new RegularAccountWithoutInterestFactory(), rootGroup);
            case "RegularAccountWithInterest" ->
                    account = createAccountFactory.createAccount(bank, new RegularAccountWithInterestFactory(getInterestRate(Currency.TRY)), rootGroup);
            case "ForeignCurrencyAccountEURWithoutInterest" ->
                    account = createAccountFactory.createAccount(bank, new ForeignCurrencyAccountEURWithoutInterestFactory(), rootGroup);
            case "ForeignCurrencyAccountEURWithInterest" ->
                    account = createAccountFactory.createAccount(bank, new ForeignCurrencyAccountEURWithInterestFactory(getInterestRate(Currency.EUR)), rootGroup);
            case "ForeignCurrencyAccountUSDWithoutInterest" ->
                    account = createAccountFactory.createAccount(bank, new ForeignCurrencyAccountUSDWithoutInterestFactory(), rootGroup);
            case "ForeignCurrencyAccountUSDWithInterest" ->
                    account = createAccountFactory.createAccount(bank, new ForeignCurrencyAccountUSDWithInterestFactory(getInterestRate(Currency.USD)), rootGroup);
            default -> System.out.println("Invalid account type.");
        }
        return account;
    }

    public boolean isValidAccountIndex(int accountIndex, int totalAccounts) {
        return accountIndex >= 0 && accountIndex < totalAccounts;
    }

    private double getInterestRate(Currency currency) {
        return bank.getInterestRate(currency);
    }
}
