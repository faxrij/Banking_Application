package Factory.Foreign;

import Account.Account;
import Account.Foreign.ForeignCurrencyAccountUSDWithInterest;
import Bank.Bank;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class ForeignCurrencyAccountUSDWithInterestFactory implements AccountFactory {
    private final double interestRate;

    public ForeignCurrencyAccountUSDWithInterestFactory(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup) {
        return new ForeignCurrencyAccountUSDWithInterest(bank, accountNumber, interestRate, accountGroup);
    }
}

