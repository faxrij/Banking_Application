package Helper.Printer;

import Stock.Stock;

import java.util.List;

public class StockListPrinter {
    public void printer(List<Stock> stocks) {
        int counter = 0;
        for (Stock stock : stocks) {
            System.out.println(counter + " " + stock.getName() + " " + stock.getValue() + " " + stock.getCurrency());
            counter++;
        }
    }
}
