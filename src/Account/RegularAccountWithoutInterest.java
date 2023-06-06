package Account;

import Bank.Bank;
import Currency.*;
import Group.AccountGroup;
import Helper.ExchangeHelper;
import Interface.IDepositable;

public class RegularAccountWithoutInterest extends Account implements IDepositable {

    public RegularAccountWithoutInterest(String accountNumber, AccountGroup accountGroup) {
        super(Currency.TRY, false, accountNumber, accountGroup);
    }

    @Override
    public void depositMoney(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount. Amount must be greater than zero.");
            return;
        }

        // Update the balance
        deposit(getBalance() + amount);
        System.out.println("Deposit successful. New balance: " + getBalance());
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        double futureBalance = getBalance();
        System.out.println("Expected balance after " + daysLater + " days: " + futureBalance);
        return futureBalance;
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {
        if(targetAccount instanceof RegularAccountWithInterest){
            mainOperation(bank, targetAccount, amount);
            return;
        }
        else {
            if( !(targetAccount.getClass().getSimpleName().endsWith("WithoutInterest"))){
                System.out.println("You cannot exchange with this Account");
                return;
            }
        }
        mainOperation(bank, targetAccount, amount);
    }

    private void mainOperation(Bank bank, Account targetAccount, double amount) {
        CurrencyRates exchangeRates = bank.getCurrencyRates().get(Currency.TRY);
        ExchangeHelper exchangeHelper = new ExchangeHelper();
        double newBalance = exchangeHelper.exchange(exchangeRates, targetAccount, amount, getBalance(), getCurrency());
        setBalance(newBalance);
    }
}
