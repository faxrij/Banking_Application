package Helper.Printer;

import Account.Account;

public class AccountDetailsPrinter {
    public void displayAccountDetails(Account account) {
        System.out.println("Account Details");
        System.out.println("Account Type: " + account.getClass().getSimpleName());
        System.out.println("Balance: " + account.getBalance());
        if (account.isHasInterest()) {
            System.out.println("Interest Rate: " + account.getInterestRate());
        }
    }
}
