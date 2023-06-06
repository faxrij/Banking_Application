package Helper;

import Account.Account;

import java.util.Scanner;

public class DepositOperation {
    private static final Scanner scanner = new Scanner(System.in);
    public void performDeposit(Account account) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        account.deposit(amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }

}
