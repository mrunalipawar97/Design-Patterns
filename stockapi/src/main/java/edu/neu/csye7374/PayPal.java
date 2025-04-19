package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class PayPal extends Stock {

    // List to store all bid values
    List<Integer> bidList = new ArrayList<>();

    public PayPal(String name, double price, String description) {
        super(name, price, description);
    }

    @Override
    public void setBid(String bid) {
        // Convert bid to integer
        int bidValue = Integer.parseInt(bid);
        bidList.add(bidValue);

        // Update the price based on the new bid (we'll calculate the price change in getMetric)
        System.out.println("Bid placed for " + this.getName() + " with value: " + bidValue);
    }

    @Override
    public String getMetric() {
        
        if (bidList.isEmpty()) {
            return "No bids placed yet for " + this.getName();
        }

       
        double avgBid = bidList.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

      
        double updatedPrice = (getPrice() + avgBid) / 2;
        setPrice(updatedPrice);  // Set the updated price

        double performanceMetric = (updatedPrice - 120) / 10;

        return String.format("The Metric for %s: Average Bid: %.2f | Performance Metric: %.2f",
                this.getName(), avgBid, performanceMetric);
    }

    @Override
    public String toString() {
        return String.format("PayPal { %s - %s - Current Price: %.2f }",
                this.getName(), this.getDescription(), this.getPrice());
    }
}
