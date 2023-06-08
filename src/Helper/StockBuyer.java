package Helper;

import Account.Account;
import Account.InvestmentAccount;
import Bank.Bank;
import Helper.Printer.StockListPrinter;
import Stock.Stock;

import java.util.List;
import java.util.Scanner;

public class StockBuyer {
    private static final Scanner scanner = new Scanner(System.in);

    public void buy(Bank bank, Account account, InvestmentAccount investmentAccount ) {
        List<Stock> stocks = bank.getStocks();
        if (stocks.size() == 0) {
            System.out.println("No Stock Available Right Now.");
            return;
        }
        StockListPrinter stockListPrinter = new StockListPrinter();
        stockListPrinter.printer(stocks);

        System.out.println("Choose the Stock you want to buy");
        int choice = scanner.nextInt();

        if (stocks.size() <= choice) {
            System.out.println("Invalid input");
            return;
        }
        Stock selectedStock = stocks.get(choice);

        if (!(account.getBalance() >= selectedStock.getValue())) { // ASSUMING HERE ALL STOCKS ARE BOUGHT AND SOLD IN TRY
            System.out.println("You do not have enough money");
            return;
        }

        investmentAccount.buyStock(selectedStock);
        account.setBalance(account.getBalance() - selectedStock.getValue());
    }
}
