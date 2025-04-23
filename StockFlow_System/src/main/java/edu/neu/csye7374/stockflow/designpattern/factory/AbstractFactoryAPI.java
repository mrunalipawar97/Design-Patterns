package edu.neu.csye7374.stockflow.designpattern.factory;

import edu.neu.csye7374.stockflow.designpattern.command.CommunicationHandler;

public abstract class AbstractFactoryAPI {
    public abstract CommunicationHandler getObject();
}
