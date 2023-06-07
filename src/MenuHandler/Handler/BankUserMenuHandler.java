package MenuHandler.Handler;

import Account.Account;
import Bank.Bank;
import Client.Client;
import Currency.*;
import Interface.AccountComponent;
import MenuHandler.Menu.BankUserMenu;
import Stock.Stock;

import java.util.List;
import java.util.Scanner;

public class BankUserMenuHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public void displayBankUserMenu(Bank bank) {
        System.out.println("Bank User Menu");
        while (true) {
            BankUserMenu bankUserMenu = new BankUserMenu();
            bankUserMenu.printBankUserOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> setDayOption(bank);
                case 2 -> setInterestRatesOption(bank);
                case 3 -> setCurrencyRatesOption(bank);
                case 4 -> createStocks(bank);
                case 5 -> {
                    return; // Exit the menu and return to the main menu
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createStocks(Bank bank) {
        System.out.println("Stock Creation");
        System.out.println("Enter name of the stock that you want to create :");

        String stockName = scanner.nextLine();

        System.out.println("Enter price of stock:");
        double stockPrice = scanner.nextDouble();

        Stock stock = new Stock(stockName, stockPrice);
        bank.addStock(stock);
    }


    private void setDayOption(Bank bank) {
        System.out.print("Enter the new day: ");
        int newDay = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        int currentDate = bank.getCurrentDate();

        if (newDay <= currentDate) {
            System.out.println("You must enter a Date that is in the future");
            return;
        }

        List<Client> clients = bank.getClients();
        for (Client client : clients) {
            List<AccountComponent> accountComponents = client.getRootAccountGroup().getAccountComponents();
            for (AccountComponent accountComponent : accountComponents) {
                if (accountComponent instanceof Account account) {
                    double futureBalance = account.calculateFutureBalance(newDay - bank.getCurrentDate());
                    account.setBalance(futureBalance);
                }
            }
        }

        bank.setCurrentDate(newDay);
        System.out.println("Day set successfully to: " + newDay);
    }

    private void setInterestRatesOption(Bank bank) {
        Currency currency = getCurrency();
        if (currency == null) return;

        System.out.print("Enter the new interest rate for " + currency + ": ");
        double newInterestRate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        bank.setInterestRate(currency, newInterestRate);
        System.out.println("Interest rate set successfully for " + currency + ": " + newInterestRate);
    }

    private void setCurrencyRatesOption(Bank bank) {
        Currency currency = getCurrency();
        if (currency == null) return;

        selectingCurrency();

        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Currency currency2;
        switch (choice2) {
            case 1 -> currency2 = Currency.EUR;
            case 2 -> currency2 = Currency.USD;
            case 3 -> currency2 = Currency.TRY;
            default -> {
                System.out.println("Invalid choice. Please try again.");
                return;
            }
        }

        if (currency.equals(currency2)) {
            System.out.println("You cannot choose same Currencies");
            return;
        }

        System.out.println("Enter New Rate For " +currency.getDisplayName() + " - " + currency2.getDisplayName());

        double newCurrencyRate = scanner.nextDouble();
        scanner.nextLine();

        CurrencyRates selectedCurrencyRates = bank.getCurrencyRates().get(currency);
        CurrencyRates currencyRates = bank.getCurrencyRates().get(currency2);

        currencyRates.setExchangeRate(currency, 1/newCurrencyRate);
        selectedCurrencyRates.setExchangeRate(currency2, newCurrencyRate);

        System.out.println("Currency rate set successfully to: " + newCurrencyRate);
    }

    private Currency getCurrency() {
        selectingCurrency();

        int choice = scanner.nextInt();
        scanner.nextLine();

        Currency currency;
        switch (choice) {
            case 1 -> currency = Currency.EUR;
            case 2 -> currency = Currency.USD;
            case 3 -> currency = Currency.TRY;
            default -> {
                System.out.println("Invalid choice. Please try again.");
                return null;
            }
        }
        return currency;
    }

    private void selectingCurrency() {
        System.out.println("Select a currency:");
        System.out.println("1. EUR");
        System.out.println("2. USD");
        System.out.println("3. TRY");
        System.out.print("Enter your choice: ");
    }
}
