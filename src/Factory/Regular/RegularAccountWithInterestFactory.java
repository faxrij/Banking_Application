package Factory.Regular;

import Account.Account;
import Account.RegularAccountWithInterest;
import Bank.Bank;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class RegularAccountWithInterestFactory implements AccountFactory {
    private final double interestRate;

    public RegularAccountWithInterestFactory(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup) {
        return new RegularAccountWithInterest(accountNumber, interestRate, accountGroup);
    }
}
