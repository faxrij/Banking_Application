package MenuHandler.Handler;

import Bank.Bank;
import Bank.GetAccountGroups;
import Group.AccountGroup;
import Helper.Printer.AccountGroupsPrinter;
import Helper.GetAccountType;
import Interface.AccountComponent;
import MenuHandler.Menu.AccountInteractionMenu;
import MenuHandler.Menu.AccountTypeMenu;
import MenuHandler.Menu.ClientUserMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientUserMenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public void displayMenu(Bank bank) {
        System.out.println("Client User Menu");
        while (true) {
            ClientUserMenu clientUserMenu = new ClientUserMenu();
            clientUserMenu.mainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createClientOption(bank);
                case 2 -> switchClientOption(bank);
                case 3 -> createAccountGroup(bank);
                case 4 -> seeAccountGroups(bank);
                case 5 -> createAccountOption(bank);
                case 6 -> displayClientsOption(bank);
                case 7 -> accountInteractionOption(bank);
                case 8 -> accountGroupInteractionOption(bank);
                case 9 -> {
                    return;
                }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void accountGroupInteractionOption(Bank bank) {
        GetAccountGroups getAccountGroups = new GetAccountGroups();
        if (accountGroupEmptyChecker(getAccountGroups.getAccountGroups(bank))) return;
        List<AccountComponent> accountGroups = new ArrayList<>(getAccountGroups.getAccountGroups(bank));

        AccountGroupsPrinter accountGroupsPrinter = new AccountGroupsPrinter();
        if (!accountGroupsPrinter.printIfAny(accountGroups)) {
            return;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        AccountGroupInteractionHandler accountGroupInteractionHandler = new AccountGroupInteractionHandler();
        accountGroupInteractionHandler.displayMenu(bank, accountGroups, (AccountGroup) accountGroups.get(choice));

    }

    private void seeAccountGroups(Bank bank) {
        if (checkingIfCurrentClientExists(bank)) return;
        GetAccountGroups getAccountGroups = new GetAccountGroups();
        List<AccountComponent> accountGroups = getAccountGroups.getAccountGroups(bank);
        if (accountGroupEmptyChecker(accountGroups)) return;

        AccountGroupsPrinter accountGroupsPrinter = new AccountGroupsPrinter();
        accountGroupsPrinter.printIfAny(accountGroups);
    }

    private boolean accountGroupEmptyChecker(List<AccountComponent> accountGroups) {
        if (accountGroups == null) {
            System.out.println("No account groups found.");
            return true;
        }
        return false;
    }

    private void createAccountGroup(Bank bank) {
        if (checkingIfCurrentClientExists(bank)) return;
        System.out.print("Enter account group name: ");
        String groupName = scanner.nextLine();

        AccountComponent accountComponent = new AccountGroup(groupName, bank);
        bank.getCurrentClient().addAccountComponent(accountComponent);
    }

    private boolean checkingIfCurrentClientExists(Bank bank) {
        if (bank.getCurrentClient() == null) {
            System.out.println("You have not created any client");
            return true;
        }
        return false;
    }

    private void accountInteractionOption(Bank bank) {
        AccountInteractionMenu accountInteractionMenu = new AccountInteractionMenu();
        if (bank.getCurrentClient() == null) {
            System.out.println("Client is not created yet");
            return;
        }
        accountInteractionMenu.displayAccountMenu(bank, bank.getCurrentClient().getRootAccountGroup().getAccountComponents());
    }

    private void createClientOption(Bank bank) {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        bank.createClient(clientName);
    }

    private void switchClientOption(Bank bank) {
        System.out.println("Select client index:");
        bank.displayClients();
        int clientIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        bank.switchClient(clientIndex);
    }

    private void createAccountOption(Bank bank) {
        if (bank.getCurrentClient() == null) {
            System.out.println("No client selected. Please switch to a client first.");
            return;
        }

        System.out.println("Select account type:");

        AccountTypeMenu accountTypeMenu = new AccountTypeMenu();
        accountTypeMenu.printAccountTypeOptions();
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Get inputted element

        GetAccountType getAccountType = new GetAccountType();
        String accountType = getAccountType.getAccountType(choice);

        if (accountType == null) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        bank.createAccount(accountType);
    }

    private void displayClientsOption(Bank bank) {
        bank.displayClients();
    }
}
