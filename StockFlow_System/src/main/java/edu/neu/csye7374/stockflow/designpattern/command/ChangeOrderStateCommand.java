package edu.neu.csye7374.stockflow.designpattern.command;

import edu.neu.csye7374.stockflow.designpattern.state.OrderState;
import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.repository.OrderRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class ChangeOrderStateCommand implements OrderCommand {
    private PurchaseOrder order;
    private OrderState previousState;
    private OrderState newState;
    private OrderRepository orderRepository;

    public ChangeOrderStateCommand(PurchaseOrder order, OrderState newState, OrderRepository orderRepository) {
        this.order = order;
        this.previousState = order.getState();
        this.newState = newState;
        this.orderRepository = orderRepository;
    }

    @Override
    public void execute() {
        order.setState(newState);
        orderRepository.save(order);
        AppLogger.info("[Command Design Pattern] Order #" + order.getProductOrderId() + " state changed from "
                + previousState.getStateName() + " to " + newState.getStateName());
    }

    @Override
    public void undo() {
        order.setState(previousState);
        orderRepository.save(order);
        AppLogger.info("[Command Design Pattern] Order #" + order.getProductOrderId() + " state reverted from "
                + newState.getStateName() + " to " + previousState.getStateName());
    }
}