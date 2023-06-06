package Bank;

import Account.Account;
import Client.Client;
import Currency.Currency;
import Group.AccountGroup;
import Interface.AccountComponent;

import java.util.List;

public class UpdateForeignAccountInterestRates {
    public void update(Currency currency, double newInterestRate, List<Client> clients) {
        for (Client client : clients) {
            List<AccountComponent> accountComponents = client.getRootAccountGroup().getAccountComponents();
            updateForeignCurrencyInterestRatesRecursive(accountComponents, currency, newInterestRate);
        }
    }

    private void updateForeignCurrencyInterestRatesRecursive(List<AccountComponent> accountComponents, Currency currency, double newInterestRate) {
        for (AccountComponent accountComponent : accountComponents) {
            checking(currency, newInterestRate, accountComponent);
        }
    }

    private void checking(Currency currency, double newInterestRate, AccountComponent accountComponent) {
        if (accountComponent instanceof Account account) {
            if (account.getCurrency() == currency && account.isHasInterest()) {
                account.setInterestRate(newInterestRate);
            }
        } else if (accountComponent instanceof AccountGroup accountGroup) {
            List<AccountComponent> groupAccountComponents = accountGroup.getAccountComponents();
            updateForeignCurrencyInterestRatesRecursive(groupAccountComponents, currency, newInterestRate);
        }
    }

}
