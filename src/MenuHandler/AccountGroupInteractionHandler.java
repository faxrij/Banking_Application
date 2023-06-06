package MenuHandler;

import Bank.Bank;
import Bank.GetAccountGroups;
import Group.AccountGroup;
import Helper.AccountGroupsPrinter;
import Interface.AccountComponent;

import java.util.List;
import java.util.Scanner;

public class AccountGroupInteractionHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public void displayMenu(Bank bank, List<AccountComponent> accountGroups, AccountGroup accountGroup) {
        System.out.println("Account Group Interaction Menu");
        while (true) {
            AccountGroupInteractionMenu accountGroupInteractionMenu = new AccountGroupInteractionMenu();
            accountGroupInteractionMenu.mainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addAccountGroupIntoOther(bank, accountGroups, accountGroup);
                case 2 -> seeBalance(accountGroup);
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void seeBalance(AccountGroup accountGroup) {
        System.out.println(accountGroup.getBalance());
    }

    private void addAccountGroupIntoOther(Bank bank, List<AccountComponent> accountGroups, AccountGroup accountGroup) {

        AccountGroupsPrinter accountGroupsPrinter = new AccountGroupsPrinter();
        if(! accountGroupsPrinter.printWithExcludingIfAnyLeft(accountGroups, accountGroup)){
            System.out.println("You should create account groups!");
            return;
        };

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice>=accountGroups.size()) {
            System.out.println("Bad input");
            return;
        }
        if( !(accountGroups.get(choice) instanceof AccountGroup accountGroup1)) {
            System.out.println("Bad input");
            return;
        }

        accountGroup1.addAccountComponent(accountGroup);
        bank.getCurrentClient().getRootAccountGroup().removeAccountComponent(accountGroup);
    }
}
