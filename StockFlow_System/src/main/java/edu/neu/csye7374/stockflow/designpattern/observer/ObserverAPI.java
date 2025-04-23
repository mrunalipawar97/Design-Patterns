package edu.neu.csye7374.stockflow.designpattern.observer;

import edu.neu.csye7374.stockflow.model.Product;

public abstract class ObserverAPI {
	
    protected Notify notify;
    
    public ObserverAPI() {
    	
    }
    public abstract void update(Product product);

}
