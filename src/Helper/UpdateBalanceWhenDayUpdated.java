package Helper;

import Account.Account;
import Bank.Bank;
import Client.Client;
import Interface.AccountComponent;

import java.util.List;

public class UpdateBalanceWhenDayUpdated {
    public void operation(Bank bank, int newDay) {
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
}
