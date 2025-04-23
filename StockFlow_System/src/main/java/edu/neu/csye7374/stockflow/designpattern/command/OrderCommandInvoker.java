package edu.neu.csye7374.stockflow.designpattern.command;

import java.util.Stack;

// Command invoker that keeps track of command history
public class OrderCommandInvoker {
    private Stack<OrderCommand> commandHistory = new Stack<>();

    public void executeCommand(OrderCommand command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            OrderCommand lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("No commands to undo");
        }
    }

    public void clearHistory() {
        commandHistory.clear();
    }
}
