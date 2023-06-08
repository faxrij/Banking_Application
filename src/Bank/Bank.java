package Bank;

import Account.*;
import Client.ClientOperations;
import Client.Client;
import Currency.Currency;
import Currency.CurrencyRates;
import Stock.Stock;

import java.util.*;

public class Bank {
    private final List<Client> clients;
    private Client currentClient;
    private final ClientOperations clientOperations;
    private final AccountOperations accountOperations;
    private int currentDate; //Like day 10 or 15 or 35, or 50 etc.

    private final Map<Currency, CurrencyRates> currencyRates;

    private final Map<Currency, Double> interestRates;
    private final List<Stock> stocks;

    public Bank() {
        clients = new ArrayList<>();
        currentClient = null;
        clientOperations = new ClientOperations();
        accountOperations = new AccountOperations(this);
        currencyRates = new HashMap<>();
        interestRates = new HashMap<>();
        stocks = new ArrayList<>();
        currentDate = 1;

        BankRatesOperation bankRatesOperation = new BankRatesOperation();
        bankRatesOperation.setInitialInterestRates(interestRates);

        bankRatesOperation.setInitialCurrencyRates(currencyRates);
    }

    public List<Client> getClients() {
        return clients;
    }

    public Double getInterestRate(Currency currency) {
        return interestRates.get(currency);
    }

    public void setInterestRate(Currency currency, Double interestRate) {
        clientOperations.setInterestRate(currency, interestRate, interestRates);

        updateForeignCurrencyInterestRates(currency, interestRate);
    }

    public void updateForeignCurrencyInterestRates(Currency currency, double newInterestRate) {
        UpdateForeignAccountInterestRates updateForeignAccountInterestRates = new UpdateForeignAccountInterestRates();
        updateForeignAccountInterestRates.update(currency, newInterestRate, clients);
    }

    public void switchClient(int clientIndex) {
        if (clientOperations.isValidClientIndex(clientIndex, clients.size())) {
            currentClient = clients.get(clientIndex);
            System.out.println("Switched to client: " + currentClient.getName());

        } else {
            System.out.println("Invalid client index.");
        }
    }

    public int getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(int currentDate) {
        this.currentDate = currentDate;
    }

    public Map<Currency, CurrencyRates> getCurrencyRates() {
        return currencyRates;
    }

    public void createClient(String clientName) {
        currentClient = clientOperations.createClient(clientName, clients, this);
        if (currentClient!=null) {
            createAccount("RegularAccountWithoutInterest");
        }
        else {
            System.out.println("Cannot Create Regular Account without Interest");
        }
    }

    public void createAccount(String accountType) {
        if (currentClient == null) {
            System.out.println("No client selected. Please switch to a client first.");
            return;
        }
        // Check if the client already has the maximum number of accounts of the same type
        clientOperations.checkIfHasMoreThan3AccountsOfSameType(currentClient, accountType);

        Account account = accountOperations.createAccount(accountType);
        if (account != null) {
            currentClient.addAccountComponent(account);
            System.out.println("Account created successfully: " + accountType);
        }
    }

    public void displayClients() {
        clientOperations.displayClients(clients);
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}
