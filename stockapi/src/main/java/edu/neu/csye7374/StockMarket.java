package edu.neu.csye7374;

import java.util.*;

public class StockMarket {
    private static StockMarket instance;
    private Map<String, Stock> stocks;

    private StockMarket() {
        stocks = new HashMap<String, Stock>();
    }

    public static synchronized StockMarket getInstance() {
        if (instance == null) {
            instance = new StockMarket();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        stocks.put(stock.getName(), stock);
    }

    public void removeStock(String name) {
        stocks.remove(name);
    }

    public void tradeStock(String name, String bid) {
        Stock stock = stocks.get(name);
        if (stock != null) {
            stock.setBid(bid);
        } else {
            System.out.println("Stock not found.");
        }
    }

    public void showAllStocks() {
        stocks.values().forEach(System.out::println);
    }

    public static void demo(){
        System.out.println("<------------------------Bidding Start------------------------>");
        StockMarket stockMarket= StockMarket.getInstance();
        Stock techStock = new TechStock("HCL", 100, "The HCL stock for in the " +
                "world of Technology");
        stockMarket.addStock(techStock);
        stockMarket.tradeStock("HCL", "120");
        stockMarket.tradeStock("HCL", "125");
        stockMarket.tradeStock("HCL", "135");
        stockMarket.tradeStock("HCL", "115");
        stockMarket.tradeStock("HCL", "90");
        stockMarket.tradeStock("HCL", "84");

        System.out.println(techStock.getMetric());
        System.out.println();

        Stock payPalStock = new PayPal("PayPal", 200, "The PayPal stock in the world of Technology");
        stockMarket.addStock(payPalStock);

        stockMarket.tradeStock("PayPal", "140");
        stockMarket.tradeStock("PayPal", "145");
        stockMarket.tradeStock("PayPal", "155");
        stockMarket.tradeStock("PayPal", "100");
        stockMarket.tradeStock("PayPal", "70");
        stockMarket.tradeStock("PayPal", "60");

        System.out.println(payPalStock.getMetric());
        System.out.println();

        Stock uber = new Uber("Uber", 131.15, "Uber Stock");
        stockMarket.addStock(uber);
        stockMarket.tradeStock("Uber", "130");
        stockMarket.tradeStock("Uber", "140");
        stockMarket.tradeStock("Uber", "150");
        stockMarket.tradeStock("Uber", "160");
        stockMarket.tradeStock("Uber", "170");
        stockMarket.tradeStock("Uber", "180");

        System.out.println(uber.getMetric() + "% increase in Stock Price");
        System.out.println();

        Stock innovTechStock = new InnovativeTechStock();
        stockMarket.addStock(innovTechStock);
        stockMarket.tradeStock("TechCorp", "220");
        stockMarket.tradeStock("TechCorp", "240");
        stockMarket.tradeStock("TechCorp", "260");
        stockMarket.tradeStock("TechCorp", "280");
        stockMarket.tradeStock("TechCorp", "300");
        stockMarket.tradeStock("TechCorp", "320");

        System.out.println(innovTechStock.getMetric());
        System.out.println();

        PennyStock pennyStock = new PennyStock("PennyStock01", 0.50, "A highly volatile stock");

        // Display initial stock details
        System.out.println(pennyStock);
        stockMarket.addStock(pennyStock);
        stockMarket.tradeStock("PennyStock01","0.55");
        stockMarket.tradeStock("PennyStock01","0.68");
        stockMarket.tradeStock("PennyStock01","0.45");
        stockMarket.tradeStock("PennyStock01","0.89");
        stockMarket.tradeStock("PennyStock01","0.58");

        System.out.println("<------------------------Bidding End------------------------>");

        stockMarket.showAllStocks();

    }
}