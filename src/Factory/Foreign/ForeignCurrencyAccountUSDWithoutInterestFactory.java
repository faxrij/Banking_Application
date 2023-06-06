package Factory.Foreign;

import Account.Account;
import Account.Foreign.ForeignCurrencyAccountUSDWithoutInterest;
import Bank.Bank;
import Group.AccountGroup;
import Interface.Factory.AccountFactory;

public class ForeignCurrencyAccountUSDWithoutInterestFactory implements AccountFactory {
    @Override
    public Account createAccount(Bank bank, String accountNumber, AccountGroup accountGroup) {
        return new ForeignCurrencyAccountUSDWithoutInterest(accountNumber, accountGroup);
    }
}

