package edu.neu.csye7374.stockflow.designpattern.decorator;

import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class TaxDecorator extends CostDecorator {
    private final double taxRate = 0.07;

    public TaxDecorator(Purchase purchase) {
        super(purchase);
    }

    @Override
    public double getTotalAmount() {
        double totalCost = purchase.getTotalAmount();
        double taxAmount = totalCost * taxRate;
        double totalWithTax = Math.round((totalCost + taxAmount) * 100) / 100.00;
        AppLogger.info("[Decorator Pattern] TaxDecorator - Total amount with tax: $" + totalWithTax);
        return totalWithTax;
    }

}
