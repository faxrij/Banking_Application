package Factory.Regular;

import Account.Account;
import Account.RegularAccountWithoutInterest;
import Bank.Bank;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class RegularAccountWithoutInterestFactory implements AccountFactory {
    @Override
    public Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup) {
        return new RegularAccountWithoutInterest(accountNumber, accountGroup);
    }
}
