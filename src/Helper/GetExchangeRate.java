package Helper;

import Currency.Currency;

public class GetExchangeRate {
    public static double getExchangeRate(Currency sourceCurrency, Currency targetCurrency) {
        if (sourceCurrency == Currency.EUR && targetCurrency == Currency.TRY) {
            return 22.50; // Euro to Turkish Lira exchange rate
        } else if (sourceCurrency == Currency.USD && targetCurrency == Currency.TRY) {
            return 20.88; // Dollar to Turkish Lira exchange rate
        } else {
            // Unsupported currency exchange rate
            throw new IllegalArgumentException("Unsupported currency exchange rate");
        }
    }
}
