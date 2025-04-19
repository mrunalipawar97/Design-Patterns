package edu.neu.csye7374;

import java.util.*;

class InnovativeTechStock extends Stock {
    private ArrayList<Double> bidValues;

    private double price = super.getPrice();
    public InnovativeTechStock() {
        super("TechCorp", 200.00, "TechCorp High Tech Stock");
        this.bidValues = new ArrayList<>();
    }

    @Override
    public void setBid(String bid) {
        double bidValue = Double.parseDouble(bid);
        this.bidValues.add(bidValue);
        this.price += bidValue; // Simplistically increasing the price by the bid value
        System.out.println("Received bid: " + bid + " for stock: " + this.getName() + ", new price: " + this.price);
    }

    @Override
    public String getMetric() {
        if (bidValues.isEmpty()) {
            return "No bids placed";
        }
        double min = bidValues.stream().min(Double::compareTo).get();
        double max = bidValues.get(0);
        for (Double bid : bidValues) {
            if (bid < min) {
                min = bid;
            }
            if (bid > max) {
                max = bid;
            }
        }
        double range = max - min;
        return "Bid range: " + range + ". High volatility if range > 50, low otherwise.";
    }

    @Override
    public String toString() {
        return "InnovativeTechStock { " + this.getName() + " - " + this.getDescription()
                + " - Current Price: " + this.getPrice() +" }";
    }
}
