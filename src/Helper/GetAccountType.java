package Helper;

public class GetAccountType {
    public String getAccountType(int choice) {
        return switch (choice) {
            case 1 -> "RegularAccountWithoutInterest";
            case 2 -> "RegularAccountWithInterest";
            case 3 -> "ForeignCurrencyAccountEURWithoutInterest";
            case 4 -> "ForeignCurrencyAccountEURWithInterest";
            case 5 -> "ForeignCurrencyAccountUSDWithInterest";
            case 6 -> "ForeignCurrencyAccountUSDWithoutInterest";
            case 7 -> "GoldAccount";
            case 8 -> "InvestmentAccount";
            default -> null;
        };
    }
}
