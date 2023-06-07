package Client;

import Bank.Bank;
import Group.AccountGroup;
import Interface.AccountComponent;

public class Client {
    private final String name;
    private AccountGroup rootAccountGroup;

    public Client(Bank bank, String name) {
        this.name = name;
        this.rootAccountGroup = new AccountGroup(name + "'s Root Account Group", bank);

    }

    public AccountGroup getRootAccountGroup() {
        return rootAccountGroup;
    }

    public String getName() {
        return name;
    }

    public void addAccountComponent(AccountComponent accountComponent) {
        rootAccountGroup.addAccountComponent(accountComponent);
    }
}
