package MenuHandler;

import Account.Account;
import Account.RegularAccountWithoutInterest;
import Bank.Bank;
import Group.AccountGroup;
import Helper.AccountDetailsPrinter;
import Helper.AccountGroupsPrinter;
import Helper.DepositOperation;
import Interface.AccountComponent;

import java.util.List;
import java.util.Scanner;

public class AccountInteractionMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public void displayAccountMenu(Bank bank, List<AccountComponent> accountComponentList) {
        System.out.println("Account Interaction Menu");
        System.out.println("Choose Account You Want to interact with");
        int counter = 0;
        for (AccountComponent accountComponent : accountComponentList) {
            if (!(accountComponent instanceof Account)) {
                counter++;
                continue;
            }
            System.out.println(counter + " " + accountComponent.getClass().getSimpleName());
            counter++;
        }
        int accountIndex = scanner.nextInt();
        scanner.nextLine();

        if (accountIndex < 0 || accountIndex >= accountComponentList.size()) {
            System.out.println("Please Enter Correct Value");
            return;
        }

        if (!(accountComponentList.get(accountIndex) instanceof Account account)) {
            System.out.println("Wrong Input");
            return;
        }

        System.out.println("1. Display Account Details");

        if (account instanceof RegularAccountWithoutInterest) {
            System.out.println("2. Deposit");
        }

        System.out.println("3. Calculate Balance after N Days");
        System.out.println("4. Move Account to Account Group");
        System.out.println("5. Exchange");
        System.out.println("0. Go Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> displayAccountDetails(account);
            case 2 -> {
                if (account instanceof RegularAccountWithoutInterest) {
                    performDeposit(account);
                } else {
                    System.out.println("Deposits are only allowed for Regular Accounts without Interest.");
                }
            }
            case 3 -> calculateBalanceAfterNDays(account);
            case 4 -> moveAccountToAccountGroup(account, accountComponentList);
            case 5 -> performExchange(bank, account, accountComponentList);
            case 0 -> System.out.println("Returning to previous menu...");
            default -> System.out.println("Invalid choice. Please try again.");
        }

    }

    private void performExchange(Bank bank, Account currentAccount, List<AccountComponent> accountComponentList) {
        System.out.println("Accounts: ");
        int counter = 0;
        for (AccountComponent accountComponent : accountComponentList) {
            if (accountComponent instanceof Account account) {
                if (!account.equals(currentAccount)) {
                    System.out.println(counter + ". " + account.getClass().getSimpleName());
                    counter++;
                }
            }
        }

        if (counter == 0) {
            System.out.println("No accounts found that match the criteria.");
            return;
        }

        System.out.print("Choose the account index to perform the exchange: ");
        int accountIndex = scanner.nextInt();
        scanner.nextLine();

        if (accountIndex < 0 || accountIndex >= counter) {
            System.out.println("Invalid account index. Please try again.");
            return;
        }

        Account selectedAccount = null;
        counter = 0;
        for (AccountComponent accountComponent : accountComponentList) {
            if (accountComponent instanceof Account account) {
                if (!account.equals(currentAccount)) {
                    if (counter == accountIndex) {
                        selectedAccount = account;
                        break;
                    }
                    counter++;
                }
            }
        }

        if (selectedAccount == null) {
            System.out.println("Failed to find the selected account. Please try again.");
            return;
        }

        System.out.print("Enter the amount to exchange: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (currentAccount.getBalance() < amount) {
            System.out.println("You do not have that much money");
            return;
        }

        currentAccount.exchangeToCurrency(bank, selectedAccount, amount);
    }

    private void displayAccountDetails(Account account) {
        AccountDetailsPrinter accountDetailsPrinter = new AccountDetailsPrinter();
        accountDetailsPrinter.displayAccountDetails(account);
    }

    private void performDeposit(Account account) {
        DepositOperation depositOperation = new DepositOperation();
        depositOperation.performDeposit(account);
    }

    private void calculateBalanceAfterNDays(Account account) {
        System.out.print("Enter the number of days: ");
        int nDays = scanner.nextInt();
        scanner.nextLine();

        account.calculateFutureBalance(nDays);
    }


    private void moveAccountToAccountGroup(Account account, List<AccountComponent> accountComponentList) {
        System.out.println("Choose the account group to move the account:");

        AccountGroupsPrinter accountGroupsPrinter = new AccountGroupsPrinter();
        accountGroupsPrinter.print(accountComponentList);

        int accountGroupIndex;
        while (true) {
            System.out.print("Choose the account group index or enter 'b' to go back: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("b")) {
                return;
            }

            try {
                accountGroupIndex = Integer.parseInt(input);
                if (accountGroupIndex >= 0 && accountGroupIndex < accountComponentList.size()) {
                    break;
                } else {
                    System.out.println("Invalid account group selection.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        AccountComponent selectedAccountGroup = accountComponentList.get(accountGroupIndex);
        if (selectedAccountGroup instanceof AccountGroup) {
            AccountGroup previousAccountGroup = findAccountGroupForAccount(account, accountComponentList);
            if (previousAccountGroup != null) {
                previousAccountGroup.removeAccountComponent(account);
            }

            if (((AccountGroup) selectedAccountGroup).containsAccount(account)) {
                System.out.println("The account is already present in the selected account group.");
            } else {
                ((AccountGroup) selectedAccountGroup).addAccountComponent(account);
                System.out.println("Account moved successfully to the selected account group.");
            }
        } else {
            System.out.println("Invalid account group selection.");
        }
    }

    private AccountGroup findAccountGroupForAccount(Account account, List<AccountComponent> accountComponentList) {
        for (AccountComponent accountComponent : accountComponentList) {
            if (accountComponent instanceof AccountGroup) {
                if (((AccountGroup) accountComponent).containsAccount(account)) {
                    return (AccountGroup) accountComponent;
                } else {
                    AccountGroup previousAccountGroup = findAccountGroupForAccount(account, ((AccountGroup) accountComponent).getAccountComponents());
                    if (previousAccountGroup != null) {
                        return previousAccountGroup;
                    }
                }
            }
        }
        return null;
    }
}
