package Account;

import Bank.Bank;
import Currency.Currency;
import Group.AccountGroup;

public class GoldAccount extends Account {

    public GoldAccount(String accountNumber, AccountGroup accountGroup) {
        super(Currency.XAU, false, accountNumber, accountGroup);
    }

    @Override
    public double calculateFutureBalance(int daysLater) {
        // No interest calculation for gold accounts
        return 0;
    }

    @Override
    public void exchangeToCurrency(Bank bank, Account targetAccount, double amount) {
        if (!(targetAccount.getClass().getSimpleName().equals("RegularAccountWithoutInterest"))) {
            System.out.println("You can only exchange between Account -> TRY Without Interest");
            return;
        }
        double balance = getBalance() - amount;
        setBalance(balance);
        targetAccount.deposit(amount);
        System.out.println("Exchanged " + amount + " from" + getClass().getSimpleName() + " to " + targetAccount.getClass().getSimpleName()
                + " successful");
    }
}


