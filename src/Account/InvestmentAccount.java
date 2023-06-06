package Account;

import Bank.Bank;
import Group.AccountGroup;

import java.util.ArrayList;
import java.util.List;

public class InvestmentAccount extends Account {
    private List<String> stocks;
    private List<String> funds;

    public InvestmentAccount(String accountNumber, AccountGroup accountGroup) {
        super(null, false, accountNumber, accountGroup);
        stocks = new ArrayList<>();
        funds = new ArrayList<>();
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        // Calculate future balance based on stocks and funds
        return 0;
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {
        // Can be improved for future development
    }

    public void buyStock(String stockSymbol) {
        // Buy a stock and update balance
    }

    public void buyFund(String fundSymbol) {
        // Buy a fund and update balance
    }
}
