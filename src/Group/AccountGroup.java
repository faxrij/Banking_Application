package Group;

import Account.Account;
import Bank.Bank;
import Currency.Currency;
import Currency.CurrencyRates;
import Interface.AccountComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountGroup implements AccountComponent {
    private final Bank bank;
    private final String groupName;
    private final List<AccountComponent> accountComponents;

    public AccountGroup(String groupName, Bank bank) {
        this.groupName = groupName;
        this.accountComponents = new ArrayList<>();
        this.bank = bank;
    }

    public boolean containsAccount(Account account) {
        for (AccountComponent accountComponent : accountComponents) {
            if (accountComponent instanceof AccountGroup) {
                if (((AccountGroup) accountComponent).containsAccount(account)) {
                    return true;
                }
            } else if (accountComponent instanceof Account) {
                if (accountComponent == account) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsAccountGroup(AccountGroup entryAccountGroup) {
        if (this == entryAccountGroup) {
            return true;
        }

        for (AccountComponent accountComponent : accountComponents) {
            if (accountComponent instanceof AccountGroup accountGroup) {
                if (accountGroup.containsAccountGroup(entryAccountGroup)) {
                    return true;
                }
            }
        }

        return false;
    }


    public String getGroupName() {
        return groupName;
    }

    public List<AccountComponent> getAccountComponents() {
        return accountComponents;
    }

    public void addAccountComponent(AccountComponent accountComponent) {
        accountComponents.add(accountComponent);
    }

    public void removeAccountComponent(AccountComponent accountComponent) {
        accountComponents.remove(accountComponent);

        // Check if the accountComponent is an AccountGroup
        if (accountComponent instanceof AccountGroup accountGroup) {

            // Recursively remove the accountComponent from all child account groups
            for (AccountComponent childComponent : accountGroup.getAccountComponents()) {
                removeAccountComponent(childComponent);
            }
        }
    }

    @Override
    public double getBalance() {
        Map<Currency, CurrencyRates> currencyRates = bank.getCurrencyRates();
        double totalBalance = 0.0;
        for (AccountComponent accountComponent : accountComponents) {
            if (accountComponent instanceof Account) {
                Currency currency = ((Account) accountComponent).getCurrency();
                CurrencyRates currencyRates1 = currencyRates.get(currency);
                double amount = currencyRates1.getExchangeRate(Currency.TRY);
                totalBalance += amount * accountComponent.getBalance();
            }
        }
        return totalBalance;
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        Map<Currency, CurrencyRates> currencyRates = bank.getCurrencyRates();
        double totalBalance = 0.0;
        for (AccountComponent accountComponent : accountComponents) {
            if (accountComponent instanceof Account) {
                Currency currency = ((Account) accountComponent).getCurrency();
                CurrencyRates currencyRates1 = currencyRates.get(currency);
                double amount = currencyRates1.getExchangeRate(Currency.TRY);
                totalBalance += amount * accountComponent.calculateFutureBalance(daysLater);
            }
        }
        return totalBalance;
    }
}