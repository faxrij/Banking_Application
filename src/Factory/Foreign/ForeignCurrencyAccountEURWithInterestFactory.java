package Factory.Foreign;

import Account.Account;
import Account.Foreign.ForeignCurrencyAccountEURWithInterest;
import Bank.Bank;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class ForeignCurrencyAccountEURWithInterestFactory implements AccountFactory {
    private final double interestRate;

    public ForeignCurrencyAccountEURWithInterestFactory(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup) {
        return new ForeignCurrencyAccountEURWithInterest(accountNumber, interestRate, accountGroup);
    }
}
