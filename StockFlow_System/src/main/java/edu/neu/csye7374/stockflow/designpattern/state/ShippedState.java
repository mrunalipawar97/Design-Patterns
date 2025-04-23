package edu.neu.csye7374.stockflow.designpattern.state;

import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class ShippedState implements OrderState {
    @Override
    public void updateState(PurchaseOrder order) {
        AppLogger.info("[State Design Pattern] Order #" + order.getProductOrderId() + " delivered, moving to completed state...");
        order.setState(new CompletedState());
    }

    @Override
    public void processOrder(PurchaseOrder order) {
        AppLogger.info("[State Design Pattern] Order has been shipped, updating tracking info...");
        // Logic for updating shipping info
    }

    @Override
    public String getStateName() {
        return "SHIPPED";
    }
}
