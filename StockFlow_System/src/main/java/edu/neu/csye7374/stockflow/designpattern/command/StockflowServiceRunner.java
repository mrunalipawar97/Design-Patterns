package edu.neu.csye7374.stockflow.designpattern.command;

import java.io.IOException;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class StockflowServiceRunner implements Runnable, CommandAPI {


    @Override
    public void execute() {
        AppLogger.info("[Command Pattern] " + "StockflowServiceRunner.execute() : starting thread");
        Thread t = new Thread(new StockflowServiceRunner());
        t.start();
    }

    @Override
    public void run() {
        AppLogger.info("[Command Pattern] " + "StockflowServiceRunner.run() : launching ServiceRunner");
        try {
            new StockflowClientHandler().ServiceRunner();
        } catch (IOException e) {
            AppLogger.error( "[Command Pattern] " + "StockflowServiceRunner.run() error: "
                            + e.getMessage());
            e.printStackTrace();
        }
    }
}
