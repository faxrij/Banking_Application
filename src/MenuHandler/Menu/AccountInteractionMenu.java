package MenuHandler.Menu;

import Account.Account;
import Account.InvestmentAccount;
import Account.RegularAccountWithoutInterest;
import Bank.Bank;
import Helper.Printer.AccountDetailsPrinter;
import Helper.CommonOperation.DepositOperation;
import Interface.AccountComponent;
import MenuHandler.Handler.AccountInteractionMenuHandler;
import Stock.Stock;

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
        System.out.println("2. Calculate Balance after N Days");
        System.out.println("3. Move Account to Account Group");
        System.out.println("4. Exchange");

        if (account instanceof RegularAccountWithoutInterest) {
            System.out.println("5. Deposit");
        }

        if (account instanceof InvestmentAccount) {
            System.out.println("6. Buy Stock");
        }
        System.out.println("0. Go Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> displayAccountDetails(account);
            case 2 -> calculateBalanceAfterNDays(account);
            case 3 -> moveAccountToAccountGroup(account, accountComponentList);
            case 4 -> performExchange(bank, account, accountComponentList);
            case 5 -> {
                if (account instanceof RegularAccountWithoutInterest) {
                    performDeposit(account);
                } else {
                    System.out.println("Deposits are only allowed for Regular Accounts without Interest.");
                }
            }
            case 6 -> buyStock(bank, account);
            case 0 -> System.out.println("Returning to previous menu...");
            default -> System.out.println("Invalid choice. Please try again.");
        }

    }

    private void buyStock(Bank bank, Account account) {
        if (!(account instanceof InvestmentAccount)) {
            System.out.println("Invalid input");
            return;
        }
        List<Stock> stocks = bank.getStocks();
        if (stocks.size()==0) {
            System.out.println("No Stock Available Right Now.");
            return;
        }
        int counter = 0;
        for (Stock stock:stocks) {
            System.out.println(counter + " " + stock.getName() + " " + stock.getValue() + " " + stock.getCurrency());
            counter++;
        }
        System.out.println("Choose the Stock you want to buy");
        int choice = scanner.nextInt();

        if (stocks.size() <= choice) {
            System.out.println("Invalid input");
            return;
        }
        Stock selectedStock = stocks.get(choice);
        ((InvestmentAccount) account).buyStock(selectedStock);
    }

    private void performExchange(Bank bank, Account currentAccount, List<AccountComponent> accountComponentList) {
        AccountInteractionMenuHandler accountInteractionMenuHandler = new AccountInteractionMenuHandler();
        accountInteractionMenuHandler.performExchange(bank, currentAccount, accountComponentList);
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
        AccountInteractionMenuHandler accountInteractionMenuHandler = new AccountInteractionMenuHandler();
        accountInteractionMenuHandler.moveAccountToAccountGroup(account, accountComponentList);
    }

}
