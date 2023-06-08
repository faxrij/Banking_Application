package MenuHandler.Menu;

import Account.Account;
import Account.InvestmentAccount;
import Account.RegularAccountWithoutInterest;
import Bank.Bank;
import Helper.Printer.AccountDetailsPrinter;
import Helper.CommonOperation.DepositOperation;
import Helper.Printer.AccountListPrinter;
import Helper.StockBuyer;
import Interface.AccountComponent;
import MenuHandler.Handler.AccountInteractionMenuHandler;

import java.util.List;
import java.util.Scanner;

public class AccountInteractionMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public void displayAccountMenu(Bank bank, List<AccountComponent> accountComponentList) {
        System.out.println("Account Interaction Menu");
        System.out.println("Choose Account You Want to interact with");

        AccountListPrinter accountListPrinter = new AccountListPrinter();
        accountListPrinter.printer(accountComponentList);

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

        CommonAccountInteractionMenu commonAccountInteractionMenu = new CommonAccountInteractionMenu();
        commonAccountInteractionMenu.print();

        if (account instanceof RegularAccountWithoutInterest) {
            System.out.println("5. Deposit");
            System.out.println("6. Buy Stock");
        }

        System.out.println("0. Go Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        choiceSelector(bank, accountComponentList, account, choice);

    }

    private void choiceSelector(Bank bank, List<AccountComponent> accountComponentList, Account account, int choice) {
        switch (choice) {
            case 1 -> displayAccountDetails(account);
            case 2 -> calculateBalanceAfterNDays(account);
            case 3 -> moveAccountToAccountGroup(account, accountComponentList);
            case 4 -> performExchange(bank, account, accountComponentList);
            case 5 -> {
                if (!(account instanceof RegularAccountWithoutInterest)) {
                    System.out.println("Deposits are only allowed for Regular Accounts without Interest.");
                    return;
                }
                performDeposit(account);
            }
            case 6 -> {
                if (!(account instanceof RegularAccountWithoutInterest)) {
                    System.out.println("You can do this operation with Regular Account Without Interest");
                    return;
                }
                buyStock(bank, account);
            }
            case 0 -> System.out.println("Returning to previous menu...");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void buyStock(Bank bank, Account account) {
        List<AccountComponent> accountComponentList = bank.getCurrentClient().getRootAccountGroup().getAccountComponents();
        InvestmentAccount investmentAccount;
        investmentAccount = getInvestmentAccount(accountComponentList);

        if (investmentAccount == null) {
            System.out.println("You do not have Investment Account");
            return;
        }

        StockBuyer stockBuyer = new StockBuyer();
        stockBuyer.buy(bank, account, investmentAccount);
    }

    private InvestmentAccount getInvestmentAccount(List<AccountComponent> accountComponentList) {
        for (AccountComponent accountComp : accountComponentList) {
            if (!(accountComp instanceof InvestmentAccount)) {
                continue;
            }
            return  (InvestmentAccount) accountComp;
        }
        return null;
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
