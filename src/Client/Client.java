package Client;

import Group.AccountGroup;
import Interface.AccountComponent;

public class Client {
    private final String name;
    private AccountGroup rootAccountGroup;

    public Client(String name) {
        this.name = name;
        this.rootAccountGroup = new AccountGroup(name + "'s Root Account Group");

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
