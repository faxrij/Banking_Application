package Bank;

import Currency.*;

import java.util.Map;

public class BankRatesOperation {
    public void setInitialInterestRates(Map<Currency, Double> interestRates) {
        interestRates.put(Currency.USD, 0.04);
        interestRates.put(Currency.TRY, 0.01);
        interestRates.put(Currency.EUR, 0.05);
        interestRates.put(Currency.XAU, 0.1);
    }

    public void setInitialCurrencyRates(Map<Currency, CurrencyRates> currencyRates) {

        // USD to other currencies
        CurrencyRates usdRates = new CurrencyRates();
        usdRates.setExchangeRate(Currency.USD, 1);
        usdRates.setExchangeRate(Currency.TRY, 23.0);
        usdRates.setExchangeRate(Currency.EUR, 0.94);
        currencyRates.put(Currency.USD, usdRates);

        // EUR to other currencies
        CurrencyRates eurRates = new CurrencyRates();
        eurRates.setExchangeRate(Currency.EUR, 1);
        eurRates.setExchangeRate(Currency.TRY, 8.5);
        currencyRates.put(Currency.EUR, eurRates);

        // XAU to other currencies
        CurrencyRates xauRates = new CurrencyRates();
        xauRates.setExchangeRate(Currency.XAU, 1);
        xauRates.setExchangeRate(Currency.TRY, 1352.36);
        currencyRates.put(Currency.XAU, xauRates);

        // TL to other currencies
        CurrencyRates tlRates = new CurrencyRates();
        tlRates.setExchangeRate(Currency.TRY, 1);
        tlRates.setExchangeRate(Currency.EUR, 0.044);
        tlRates.setExchangeRate(Currency.USD, 0.047);
        tlRates.setExchangeRate(Currency.XAU, 0.0001);

        currencyRates.put(Currency.TRY, tlRates);
    }
}
