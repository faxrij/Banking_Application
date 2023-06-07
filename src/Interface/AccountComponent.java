package Interface;

import Bank.Bank;

public interface AccountComponent {
    double getBalance();
    double calculateFutureBalance(int daysLater);
}
