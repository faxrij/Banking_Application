package Factory;

import Account.Account;
import Account.GoldAccountWithoutInterest;
import Bank.Bank;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class GoldAccountWithoutInterestFactory implements AccountFactory {
    @Override
    public Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup) {
        return new GoldAccountWithoutInterest(accountNumber, accountGroup);
    }
}
