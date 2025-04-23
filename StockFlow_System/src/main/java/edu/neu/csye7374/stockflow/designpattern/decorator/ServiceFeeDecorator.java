package edu.neu.csye7374.stockflow.designpattern.decorator;

import edu.neu.csye7374.stockflow.utils.AppLogger;

public class ServiceFeeDecorator extends CostDecorator {

    private final double serviceFee = 25.00; // Fixed service fee
    public ServiceFeeDecorator(Purchase purchase) {
        super(purchase);
    }

    @Override
    public double getTotalAmount() {
        double totalCost = purchase.getTotalAmount() + serviceFee;
        AppLogger.info("[Decorator Pattern] ServiceFeeDecorator - Total amount with service fee: $" + totalCost);
        return totalCost;
    }
}
