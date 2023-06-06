package Interface.Factory;

import Account.Account;
import Bank.Bank;
import Group.AccountGroup;

public interface AccountFactory {
    Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup);
}

