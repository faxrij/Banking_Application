package MenuHandler.Handler;

import Bank.Bank;
import Currency.*;
import Helper.UpdateBalanceWhenDayUpdated;
import MenuHandler.Menu.BankUserMenu;
import MenuHandler.Menu.CurrencySelectMenu;
import Stock.Stock;

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

        UpdateBalanceWhenDayUpdated updateBalanceWhenDayUpdated = new UpdateBalanceWhenDayUpdated();
        updateBalanceWhenDayUpdated.operation(bank, newDay);
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

        CurrencySelectMenu currencySelectMenu = new CurrencySelectMenu();
        currencySelectMenu.selectingCurrency();

        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Currency currency2 = getCurrency(choice2);

        if (currency2 == null) return;

        if (currency.equals(currency2)) {
            System.out.println("You cannot choose same Currencies");
            return;
        }

        System.out.println("Enter New Rate For " + currency.getDisplayName() + " - " + currency2.getDisplayName());

        double newCurrencyRate = scanner.nextDouble();
        scanner.nextLine();

        CurrencyRates selectedCurrencyRates = bank.getCurrencyRates().get(currency);
        CurrencyRates currencyRates = bank.getCurrencyRates().get(currency2);

        currencyRates.setExchangeRate(currency, 1 / newCurrencyRate);
        selectedCurrencyRates.setExchangeRate(currency2, newCurrencyRate);

        System.out.println("Currency rate set successfully to: " + newCurrencyRate);
    }

    private Currency getCurrency() {
        CurrencySelectMenu currencySelectMenu = new CurrencySelectMenu();
        currencySelectMenu.selectingCurrency();

        int choice = scanner.nextInt();
        scanner.nextLine();

        Currency currency;
        currency = getCurrency(choice);
        return currency;
    }

    private Currency getCurrency(int choice) {
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
}
