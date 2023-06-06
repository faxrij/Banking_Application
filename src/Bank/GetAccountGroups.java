package Bank;

import Group.AccountGroup;
import Interface.AccountComponent;

import java.util.List;

public class GetAccountGroups {
    public List<AccountComponent> getAccountGroups(Bank bank) {
        if(bank.getCurrentClient() == null) {
            System.out.println("Client is not created");
            return null;
        }
        return bank.getCurrentClient().getRootAccountGroup().getAccountComponents().stream() // HEre I DO not print The ROOT Account Group name
                .filter(account -> account instanceof AccountGroup)
                .toList();

    }
}
