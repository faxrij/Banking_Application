package Bank;

import Account.Account;
import Currency.Currency;
import Factory.CreateAccountFactory;
import Factory.Foreign.ForeignCurrencyAccountEURWithInterestFactory;
import Factory.Foreign.ForeignCurrencyAccountEURWithoutInterestFactory;
import Factory.Foreign.ForeignCurrencyAccountUSDWithInterestFactory;
import Factory.Foreign.ForeignCurrencyAccountUSDWithoutInterestFactory;
import Factory.GoldAccountWithInterestFactory;
import Factory.GoldAccountWithoutInterestFactory;
import Factory.InvestmentAccountFactory;
import Factory.Regular.RegularAccountWithInterestFactory;
import Factory.Regular.RegularAccountWithoutInterestFactory;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class AccountOperations {
    private final CreateAccountFactory createAccountFactory;
    private final AccountFactory RegularAccountWithoutInterestFactory;
    private final AccountFactory RegularAccountWithInterestFactory;
    private final AccountFactory ForeignCurrencyAccountEURWithoutInterestFactory;
    private final AccountFactory ForeignCurrencyAccountEURWithInterestFactory;
    private final AccountFactory ForeignCurrencyAccountUSDWithoutInterestFactory;
    private final AccountFactory ForeignCurrencyAccountUSDWithInterestFactory;
    private final AccountFactory GoldAccountWithInterestFactory;
    private final AccountFactory GoldAccountWithoutInterestFactory;
    private final AccountFactory InvestmentAccountFactory;


    private final Bank bank;

    public AccountOperations(Bank bank) {
        createAccountFactory = new CreateAccountFactory();
        this.bank = bank;
        RegularAccountWithoutInterestFactory = new RegularAccountWithoutInterestFactory();
        RegularAccountWithInterestFactory = new RegularAccountWithInterestFactory(getInterestRate(Currency.TRY));
        ForeignCurrencyAccountEURWithoutInterestFactory = new ForeignCurrencyAccountEURWithoutInterestFactory();
        ForeignCurrencyAccountEURWithInterestFactory = new ForeignCurrencyAccountEURWithInterestFactory(getInterestRate(Currency.EUR));
        ForeignCurrencyAccountUSDWithoutInterestFactory = new ForeignCurrencyAccountUSDWithoutInterestFactory();
        ForeignCurrencyAccountUSDWithInterestFactory = new ForeignCurrencyAccountUSDWithInterestFactory(getInterestRate(Currency.USD));
        GoldAccountWithInterestFactory = new GoldAccountWithInterestFactory(getInterestRate(Currency.XAU));
        GoldAccountWithoutInterestFactory = new GoldAccountWithoutInterestFactory();
        InvestmentAccountFactory = new InvestmentAccountFactory();
    }

    public Account createAccount(String accountType) {
        Account account = null;
        AccountGroup rootGroup = bank.getCurrentClient().getRootAccountGroup();
        switch (accountType) {
            case "RegularAccountWithoutInterest" ->
                    account = createAccountFactory.createAccount(bank, RegularAccountWithoutInterestFactory, rootGroup);
            case "RegularAccountWithInterest" ->
                    account = createAccountFactory.createAccount(bank, RegularAccountWithInterestFactory, rootGroup);
            case "ForeignCurrencyAccountEURWithoutInterest" ->
                    account = createAccountFactory.createAccount(bank, ForeignCurrencyAccountEURWithoutInterestFactory, rootGroup);
            case "ForeignCurrencyAccountEURWithInterest" ->
                    account = createAccountFactory.createAccount(bank, ForeignCurrencyAccountEURWithInterestFactory, rootGroup);
            case "ForeignCurrencyAccountUSDWithoutInterest" ->
                    account = createAccountFactory.createAccount(bank, ForeignCurrencyAccountUSDWithoutInterestFactory, rootGroup);
            case "ForeignCurrencyAccountUSDWithInterest" ->
                    account = createAccountFactory.createAccount(bank, ForeignCurrencyAccountUSDWithInterestFactory, rootGroup);
            case "GoldAccountWithoutInterest" ->
                    account = createAccountFactory.createAccount(bank, GoldAccountWithoutInterestFactory, rootGroup);
            case "GoldAccountWithInterest" ->
                    account = createAccountFactory.createAccount(bank, GoldAccountWithInterestFactory, rootGroup);
            case "InvestmentAccount" ->
                    account = createAccountFactory.createAccount(bank, InvestmentAccountFactory, rootGroup);

            default -> System.out.println("Invalid account type.");
        }
        return account;
    }

    private double getInterestRate(Currency currency) {
        return bank.getInterestRate(currency);
    }
}
