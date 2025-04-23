package edu.neu.csye7374.stockflow.designpattern.state;

import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class CompletedState implements OrderState {
    @Override
    public void updateState(PurchaseOrder order) {
        AppLogger.info("[State Design Pattern] Order #" + order.getProductOrderId() + " already completed and paid.");
    }

    @Override
    public void processOrder(PurchaseOrder order) {
        AppLogger.info("[State Design Pattern] Order is completed, checking payment status...");
        // Logic for checking payment
    }

    @Override
    public String getStateName() {
        return "COMPLETED";
    }
}
