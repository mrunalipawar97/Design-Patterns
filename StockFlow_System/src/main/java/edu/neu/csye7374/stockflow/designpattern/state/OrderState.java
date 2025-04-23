package edu.neu.csye7374.stockflow.designpattern.state;

import edu.neu.csye7374.stockflow.model.PurchaseOrder;

public interface OrderState {
    void updateState(PurchaseOrder order);
    void processOrder(PurchaseOrder order);
    String getStateName();
}
