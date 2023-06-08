package Currency;

import java.util.HashMap;
import java.util.Map;

public class CurrencyRates {
    private final Map<Currency, Double> exchangeRates;

    public CurrencyRates() {
        exchangeRates = new HashMap<>();
    }

    public void setExchangeRate(Currency currency, double rate) {
        if (exchangeRates.containsKey(currency)) {
            exchangeRates.replace(currency, rate);
        } else {
            exchangeRates.put(currency, rate);
        }
    }

    public double getExchangeRate(Currency currency) {
        return exchangeRates.get(currency);
    }
}
