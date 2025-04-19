package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class PennyStock extends Stock {

    private double maxBid = super.getPrice();
    private double performanceMetric;

    public PennyStock(String name, double price, String description) {
        super(name, price, description);
    }

    private List<Double> bidHistory = new ArrayList<>();

    @Override
    public void setBid(String bid) {
        double bidValue = Double.parseDouble(bid);
        if (bidValue > 5.00) {
            System.out.println("Error: Penny stock bid cannot exceed $5.00");
            return;
        }
        bidHistory.add(bidValue); // Add bid to history
        if (bidValue > maxBid) {
            maxBid = bidValue;
        }
        System.out.println("Penny Stock Bid: $" + bidValue);
    }

    public List<Double> getBidHistory() {
        return bidHistory;
    }

    @Override
    public String getMetric() {
        // Calculate the percentage change from initial price to max bid
        performanceMetric = ((maxBid - super.getPrice()) / super.getPrice()) * 100;
        return new DecimalFormat("#.##").format(performanceMetric); // Format to 2 decimal places
    }

    @Override
    public String toString() {
    return "------------ Stock Name: " + super.getName() + " ------------" + "\n" +
            "Price: $" + super.getPrice() + "\n" +
            "Description: " + super.getDescription() + "\n" +
            "Final Bid: $" + maxBid + "\n" +
            "Performance Metric: " + getMetric() + "% increase in stock price" + "\n" +
            "Bid History: " + bidHistory;
    }        
}