package Stock;

import Currency.Currency;

public class Stock {
    private final String name;
    private final Currency currency;
    private final double value;

    public Stock(String name, double value) {
        this.name = name;
        this.currency = Currency.USD;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }
}
