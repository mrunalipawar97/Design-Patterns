package edu.neu.csye7374.stockflow.designpattern.command;

import java.io.IOException;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class ClientCommand implements Runnable, CommandAPI {
    
    private static String payload;

    public ClientCommand() { }

    public ClientCommand(String message) {
        ClientCommand.payload = message;
        AppLogger.info("[Command Pattern] " + "ClientCommand ctor : payload='" + payload + "'");
    }

    @Override
    public void run() {
        AppLogger.info("[Command Pattern] " + "ClientCommand.run() : handling payload='" + payload + "'");
        try {
            new StockflowClientHandler().ClientHandler(payload);
        } catch (IOException ex) {
            AppLogger.error("[Command Pattern] " + "ClientCommand.run() error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void execute() { 
        AppLogger.info("[Command Pattern] " +  "ClientCommand.execute() : spawning thread");
        Thread thread = new Thread(new ClientCommand(payload));
        thread.start();
    }
}
