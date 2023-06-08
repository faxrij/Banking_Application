package MenuHandler.Handler;

import Bank.Bank;
import Group.AccountGroup;
import Helper.Printer.AccountGroupsPrinter;
import Interface.AccountComponent;
import MenuHandler.Menu.AccountGroupInteractionMenu;

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
                case 3 -> seeFutureBalance(accountGroup);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void seeFutureBalance(AccountGroup accountGroup) {
        System.out.println("Enter the date in numbers :");
        int date = scanner.nextInt();
        scanner.nextLine();
        double returnVal = accountGroup.calculateFutureBalance(date);
        System.out.println("On the day " + date + " " + accountGroup.getGroupName() + " will be worth " + returnVal + " of TRY");
    }

    private void seeBalance(AccountGroup accountGroup) {
        System.out.println(accountGroup.getBalance());
    }

    private void addAccountGroupIntoOther(Bank bank, List<AccountComponent> accountGroups, AccountGroup accountGroup) {

        AccountGroupsPrinter accountGroupsPrinter = new AccountGroupsPrinter();
        if (!accountGroupsPrinter.printWithExcludingIfAnyLeft(accountGroups, accountGroup)) {
            System.out.println("You should create account groups!");
            return;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= accountGroups.size()) {
            System.out.println("Bad input");
            return;
        }
        if (!(accountGroups.get(choice) instanceof AccountGroup accountGroup1)) {
            System.out.println("Bad input");
            return;
        }

        accountGroup1.addAccountComponent(accountGroup);
        bank.getCurrentClient().getRootAccountGroup().removeAccountComponent(accountGroup);
    }
}
