package Helper.Printer;

import Account.Account;
import Interface.AccountComponent;

import java.util.List;

public class AccountListPrinter {
    public void printer(List<AccountComponent> accountComponentList) {
        int counter = 0;
        for (AccountComponent accountComponent : accountComponentList) {
            if (!(accountComponent instanceof Account)) {
                counter++;
                continue;
            }
            System.out.println(counter + " " + accountComponent.getClass().getSimpleName());
            counter++;
        }
    }
}
