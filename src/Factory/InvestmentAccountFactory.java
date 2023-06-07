package Factory;

import Account.Account;
import Account.InvestmentAccount;
import Bank.Bank;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class InvestmentAccountFactory implements AccountFactory {
    @Override
    public Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup) {
        return new InvestmentAccount(accountNumber, accountGroup);
    }
}
