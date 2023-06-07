package Factory;

import Account.Account;
import Account.GoldAccountWithInterest;
import Bank.Bank;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class GoldAccountWithInterestFactory implements AccountFactory {
    private final double interestRate;

    public GoldAccountWithInterestFactory(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup) {
        return new GoldAccountWithInterest(accountNumber, interestRate, accountGroup);
    }
}
