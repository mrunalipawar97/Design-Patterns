package edu.neu.csye7374.stockflow.designpattern.state;

import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class CreatedState implements OrderState {

    @Override
    public void updateState(PurchaseOrder order) {
        AppLogger.info("[State Design Pattern] Order #" + order.getProductOrderId() + " processed, moving to processing state...");
        order.setState(new ProcessingState());
    }

    @Override
    public void processOrder(PurchaseOrder order) {
        AppLogger.info("[State Design Pattern] Processing new order...");
        // Logic for initial processing
    }

    @Override
    public String getStateName() {
        return "CREATED";
    }
}
