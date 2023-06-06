package MenuHandler;

import Bank.Bank;

import java.util.Scanner;

public class MainMenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public void displayMenu(Bank bank) {
        System.out.println("Banking Application Menu");
        while (true) {
            System.out.println("1. Bank User");
            System.out.println("2. Client User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> handleBankUserMenu(bank);
                case 2 -> handleClientUserMenu(bank);
                case 3 -> {
                    System.out.println("Exiting the application...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleClientUserMenu(Bank bank) {
        ClientUserMenuHandler clientUserMenuHandler = new ClientUserMenuHandler();
        clientUserMenuHandler.displayMenu(bank);
    }

    private void handleBankUserMenu(Bank bank) {
        BankUserMenuHandler bankUserMenuHandler = new BankUserMenuHandler();
        bankUserMenuHandler.displayBankUserMenu(bank);
    }
}
