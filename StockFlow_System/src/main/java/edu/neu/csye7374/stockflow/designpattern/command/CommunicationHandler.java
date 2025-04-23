package edu.neu.csye7374.stockflow.designpattern.command;

import java.util.ArrayList;
import java.util.List;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class CommunicationHandler {
    
    private List<CommandAPI> commandQueue = new ArrayList<>();

    public void queueCommand(CommandAPI command) {
        AppLogger.info("[Command Pattern] " + "queueCommand() : queued "
                       + command.getClass().getSimpleName());
        commandQueue.add(command);
    }

    public void runAllCommands() {
        AppLogger.info("[Command Pattern] " + "runAllCommands() : executing "
                       + commandQueue.size() + " commands");
        for (CommandAPI command : commandQueue) {
            AppLogger.info("[Command Pattern] " + "runAllCommands() : executing "
                           + command.getClass().getSimpleName());
            command.execute();
        }
        commandQueue.clear();
        AppLogger.info("[Command Pattern] " + "runAllCommands() : queue cleared");
    }

    public void initiateInteraction(String message) {
        AppLogger.info("[Command Pattern] " + "initiateInteraction() : message='" + message + "'");
        StockflowServiceRunner server = new StockflowServiceRunner();
        ClientCommand client = new ClientCommand(message);

        CommunicationHandler handler = new CommunicationHandler();
        handler.queueCommand(server);
        handler.queueCommand(client);
        handler.runAllCommands();
    }
}
