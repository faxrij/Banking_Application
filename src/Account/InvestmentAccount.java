package Account;

import Bank.Bank;
import Group.AccountGroup;
import Stock.Stock;

import java.util.ArrayList;
import java.util.List;

public class InvestmentAccount extends Account {
    private List<Stock> stocks;

    public InvestmentAccount(String accountNumber, AccountGroup accountGroup) {
        super(null, false, accountNumber, accountGroup);
        stocks = new ArrayList<>();
//        funds = new ArrayList<>();
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        // Calculate future balance based on stocks and funds
        double totalValue = 0;
        for (Stock stock: stocks) {
            totalValue += stock.getValue();
        }
        return totalValue;
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {
        System.out.println("Exchange cannot be done at the moment, sorry!");
        // Can be improved for future development
    }

    @Override
    public void setLastExchangeDay(int exchangeDay) {

    }

    public void buyStock(Stock stock) {
        stocks.add(stock);
        System.out.println(stock.getName() + " is bought");
    }

    public void buyFund(String fundSymbol) {
        // Buy a fund and update balance
    }
}
