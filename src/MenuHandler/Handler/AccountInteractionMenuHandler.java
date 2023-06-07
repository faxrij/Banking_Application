package MenuHandler.Handler;

import Account.Account;
import Bank.Bank;
import Group.AccountGroup;
import Helper.Printer.AccountGroupsPrinter;
import Interface.AccountComponent;

import java.util.List;
import java.util.Scanner;

public class AccountInteractionMenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public void performExchange(Bank bank, Account currentAccount, List<AccountComponent> accountComponentList) {
        System.out.println("Accounts: ");
        int counter = 0;
        counter = getCounter(currentAccount, accountComponentList, counter);

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

        Account selectedAccount = getAccount(currentAccount, accountComponentList, accountIndex);

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

    private int getCounter(Account currentAccount, List<AccountComponent> accountComponentList, int counter) {
        for (AccountComponent accountComponent : accountComponentList) {
            if (accountComponent instanceof Account account) {
                if (!account.equals(currentAccount)) {
                    System.out.println(counter + ". " + account.getClass().getSimpleName());
                    counter++;
                }
            }
        }
        return counter;
    }

    private Account getAccount(Account currentAccount, List<AccountComponent> accountComponentList, int accountIndex) {
        int counter;
        Account selectedAccount = null;
        counter = 0;
        for (AccountComponent accountComponent : accountComponentList) {
            if (!(accountComponent instanceof Account account)) {
                continue;
            }
            if (account.equals(currentAccount)) {
                continue;
            }

            if (counter == accountIndex) {
                selectedAccount = account;
                break;
            }
            counter++;


        }
        return selectedAccount;
    }

    public void moveAccountToAccountGroup(Account account, List<AccountComponent> accountComponentList) {
        System.out.println("Choose the account group to move the account:");

        AccountGroupsPrinter accountGroupsPrinter = new AccountGroupsPrinter();
        accountGroupsPrinter.printIfAny(accountComponentList);

        int accountGroupIndex;
        while (true) {
            System.out.print("Choose the account group index or enter 'b' to go back: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("b")) {
                return;
            }

            try {
                accountGroupIndex = Integer.parseInt(input);
                if (extracted(accountComponentList, accountGroupIndex)) break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        AccountComponent selectedAccountGroup = accountComponentList.get(accountGroupIndex);
        if (!(selectedAccountGroup instanceof AccountGroup)) {
            System.out.println("Invalid account group selection.");
            return;
        }
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

    }

    private boolean extracted(List<AccountComponent> accountComponentList, int accountGroupIndex) {
        if (accountGroupIndex >= 0 && accountGroupIndex < accountComponentList.size()) {
            return true;
        } else {
            System.out.println("Invalid account group selection.");
        }
        return false;
    }

    private AccountGroup findAccountGroupForAccount(Account account, List<AccountComponent> accountComponentList) {
        for (AccountComponent accountComponent : accountComponentList) {
            if (!(accountComponent instanceof AccountGroup)) {
                continue;
            }
            if (((AccountGroup) accountComponent).containsAccount(account)) {
                return (AccountGroup) accountComponent;
            } else {
                AccountGroup previousAccountGroup = findAccountGroupForAccount(account, ((AccountGroup) accountComponent).getAccountComponents());
                if (previousAccountGroup != null) {
                    return previousAccountGroup;
                }
            }
        }
        return null;
    }

}
