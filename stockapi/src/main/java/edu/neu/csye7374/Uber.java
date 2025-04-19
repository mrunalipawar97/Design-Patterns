package edu.neu.csye7374;

import java.text.DecimalFormat;

public class Uber extends Stock {

    private double maxBid = super.getPrice();

    private double performanceMetric;

    public Uber(String name, double price, String description) {
        super(name, price, description);
    }

    @Override
    public void setBid(String bid) {
        double bidValue = Double.parseDouble(bid);
        if (bidValue > maxBid) {
            maxBid = bidValue;
        }
        System.out.println("Uber Bid: " + bidValue);
    }

    @Override
    public String getMetric() {
        performanceMetric = ((maxBid - super.getPrice())/super.getPrice()) * 100;
        return Double.toString(performanceMetric);
    }

    @Override
    public String toString() {
        return "------------Stock Name: " + super.getName() + "------------" + "\n" +
                "Price: " + super.getPrice() + "\n" +
                "Description: " + super.getDescription() + "\n" +
                "Final Bid: " + maxBid + "\n" +
                "Performance Metric: " + getMetric() + "% increase in stock price";
    }
}