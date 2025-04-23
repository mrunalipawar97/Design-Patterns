package edu.neu.csye7374.stockflow.designpattern.state;

import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class ProcessingState implements OrderState {

    @Override
    public void updateState(PurchaseOrder order) {
        AppLogger.info("[State Design Pattern] Order #" + order.getProductOrderId() + " processed, moving to shipped state...");
        order.setState(new ShippedState());
    }

    @Override
    public void processOrder(PurchaseOrder order) {
        AppLogger.info("[State Design Pattern] Processing order items...");
        // Logic for processing items, reserving inventory, etc.
    }

    @Override
    public String getStateName() {
        return "PROCESSING";
    }
}
