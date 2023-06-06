package Client;

import Currency.Currency;

import java.util.List;
import java.util.Map;

public class ClientOperations {
    public boolean isClientNameValid(String clientName) {
        return !clientName.isEmpty();
    }

    public boolean isValidClientIndex(int clientIndex, int clientCount) {
        return clientIndex >= 0 && clientIndex < clientCount;
    }

    public void displayClients(List<Client> clients) {
        System.out.println("Clients:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(i + ". " + clients.get(i).getName());
        }
    }

    public Client createClient(String clientName, List<Client> clients) {
        if (isClientNameValid(clientName)) {
            Client newClient = new Client(clientName);
            clients.add(newClient);

            System.out.println("Client created successfully: " + clientName);
            return newClient;
        } else {
            System.out.println("Invalid client name.");
            return null;
        }
    }

    public void checkIfHasMoreThan3AccountsOfSameType(Client currentClient, String accountType) {
        int maxAccountTypeCount = 3;
        long accountTypeCount = currentClient.getRootAccountGroup().getAccountComponents().stream()
                .filter(acc -> acc.getClass().getSimpleName().equals(accountType))
                .count();
        if (accountTypeCount >= maxAccountTypeCount) {
            System.out.println("Cannot create more than " + maxAccountTypeCount + " accounts of type " + accountType);
        }
    }

    public void setInterestRate(Currency currency, Double interestRate, Map<Currency, Double> interestRates) {
        if (interestRates.containsKey(currency)) {
            interestRates.replace(currency, interestRate);
        } else {
            interestRates.put(currency, interestRate);
        }
    }

}
