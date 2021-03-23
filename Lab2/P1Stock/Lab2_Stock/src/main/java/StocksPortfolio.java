import java.lang.reflect.Array;
import java.util.ArrayList;

public class StocksPortfolio {
    private String name;
    private IStockMarket stockMarket;
    private ArrayList<Stock> stocks;

    public StocksPortfolio(IStockMarket market) {
        this.stockMarket = market;
        this.stocks = new ArrayList<>();
    }

    public IStockMarket getStockMarket(){
        return this.stockMarket;
    }

    public void setStockMarket(IStockMarket newStockMarket){
        this.stockMarket = newStockMarket;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public double getTotalValue(){
        double total = 0;

        for(Stock stock : this.stocks){
            total += stockMarket.getPrice(stock.getLabel()) * stock.getQuantity();
        }

        return total;
    }

    public void addStock(Stock newStock){
        this.stocks.add(newStock);
    }
}
