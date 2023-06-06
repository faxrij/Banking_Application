package Bank;

import Currency.*;

import java.util.Map;

public class BankRatesOperation {
    public void setInitialInterestRates(Map<Currency, Double> interestRates) {
        interestRates.put(Currency.USD, 2.0);
        interestRates.put(Currency.TRY, 1.0);
        interestRates.put(Currency.EUR, 3.0);
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

        // AUX to other currencies
        CurrencyRates auxRates = new CurrencyRates();
        auxRates.setExchangeRate(Currency.XAU, 1);
        auxRates.setExchangeRate(Currency.TRY, 1352.36);
        currencyRates.put(Currency.XAU, auxRates);

        // TL to other currencies
        CurrencyRates tlRates = new CurrencyRates();
        tlRates.setExchangeRate(Currency.TRY, 1);
        tlRates.setExchangeRate(Currency.EUR, 0.044);
        tlRates.setExchangeRate(Currency.USD, 0.047);
        tlRates.setExchangeRate(Currency.XAU, 0.0001);

        currencyRates.put(Currency.TRY, tlRates);
    }
}
