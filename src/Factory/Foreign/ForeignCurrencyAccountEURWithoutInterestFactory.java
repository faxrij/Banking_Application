package Factory.Foreign;

import Account.Account;
import Account.Foreign.ForeignCurrencyAccountEURWithoutInterest;
import Bank.Bank;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class ForeignCurrencyAccountEURWithoutInterestFactory implements AccountFactory {
    @Override
    public Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup) {
        return new ForeignCurrencyAccountEURWithoutInterest(accountNumber, accountGroup);
    }
}

