package Helper;

import Account.Account;
import Group.AccountGroup;
import Interface.AccountComponent;

import java.util.List;

public class AccountGroupsPrinter {
    public void print(List<AccountComponent> accountGroups) {
        System.out.println("Account Groups:");
        for (int i = 0; i < accountGroups.size(); i++) {
            if (accountGroups.get(i) instanceof AccountGroup accountGroup) {
                System.out.println((i) + ". " + accountGroup.getGroupName());
                printingContents(accountGroup, 1);
            }
        }
    }

    public boolean printWithExcludingIfAnyLeft(List<AccountComponent> accountGroups, AccountGroup chosenEntryAccountGroup) {
        System.out.println("Account Groups:");
        for (int i = 0; i < accountGroups.size(); i++) {
            if (accountGroups.get(i) instanceof AccountGroup accountGroup && !(accountGroups.get(i).equals(chosenEntryAccountGroup))) {
                if (checking(chosenEntryAccountGroup, i, accountGroup)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checking(AccountGroup chosenEntryAccountGroup, int i, AccountGroup accountGroup) {
        if (accountGroup.containsAccountGroup(chosenEntryAccountGroup)) {
            System.out.println("Already added");
            return true;
        }
        System.out.println(i + ". " + accountGroup.getGroupName());
        printingContents(accountGroup, 1);
        return false;
    }

    private void printingContents(AccountGroup accountGroup, int depth) {
        String indentation = "  ".repeat(depth);
        System.out.println(indentation + "- " + accountGroup.getGroupName());

        List<AccountComponent> accountComponents = accountGroup.getAccountComponents();
        if (accountComponents != null) {
            for (AccountComponent accountComponent : accountComponents) {
                if (accountComponent instanceof AccountGroup innerGroup) {
                    printingContents(innerGroup, depth + 1);
                } else if (accountComponent instanceof Account) {
                    System.out.println(indentation + "  - Account: " + accountComponent.getClass().getSimpleName());
                }
            }
        }
    }
}
