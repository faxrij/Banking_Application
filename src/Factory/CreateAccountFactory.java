package Factory;

import Account.Account;
import Bank.Bank;
import Group.AccountGroup;
import Helper.RandomStringGenerator;
import Interface.Factory.AccountFactory;

public class CreateAccountFactory {

    public Account createAccount(Bank bank, AccountFactory createAccount, AccountGroup rootGroup) {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        return createAccount.createAccount(bank, randomStringGenerator.generateRandomString(10), rootGroup);
    }
}
