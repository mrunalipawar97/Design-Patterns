package edu.neu.csye7374.stockflow.designpattern.factory;

import edu.neu.csye7374.stockflow.designpattern.command.CommunicationHandler;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class CommunicationHandlerFactory extends AbstractFactoryAPI {

    private static CommunicationHandler comm;

    @Override
    public CommunicationHandler getObject() {
        if (comm == null) {
            AppLogger.info("[Factory Pattern] CommunicationHandlerFactory +  - Creating new CommunicationHandler instance");
            comm = new CommunicationHandler();
        }
        return comm;
    }

}
