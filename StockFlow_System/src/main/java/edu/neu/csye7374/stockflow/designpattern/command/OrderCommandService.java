package edu.neu.csye7374.stockflow.designpattern.command;

import edu.neu.csye7374.stockflow.designpattern.state.*;
import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

// Create a service to handle order commands
@Service
public class OrderCommandService {
    @Autowired
    private OrderRepository orderRepository;

    private OrderCommandInvoker invoker = new OrderCommandInvoker();

    public void changeOrderState(int orderId, String newStateName) {
        Optional<PurchaseOrder> optOrder = orderRepository.findById(orderId);
        if (optOrder.isPresent()) {
            PurchaseOrder order = optOrder.get();
            OrderState newState = createStateFromName(newStateName);

            OrderCommand command = new ChangeOrderStateCommand(order, newState, orderRepository);
            invoker.executeCommand(command);
        } else {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }

    public void undoLastOperation() {
        invoker.undoLastCommand();
    }

    private OrderState createStateFromName(String stateName) {
        switch(stateName.toUpperCase()) {
            case "CREATED":
                return new CreatedState();
            case "PROCESSING":
                return new ProcessingState();
            case "SHIPPED":
                return new ShippedState();
            case "COMPLETED":
                return new CompletedState();
            default:
                throw new IllegalArgumentException("Unknown state: " + stateName);
        }
    }
}
