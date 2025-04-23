package edu.neu.csye7374.stockflow.designpattern.observer;

import edu.neu.csye7374.stockflow.model.Product;
import edu.neu.csye7374.stockflow.utils.AppLogger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Notify {
    private final List<ObserverAPI> subscribers = new ArrayList();

    public Notify() {
        AppLogger.info("[Observer Pattern] - Notify initialized");
    }

    public void setState(Product product) {
        AppLogger.info("[Observer Pattern] - Setting state for product: " + product.getProductName());
        this.notifyAllSubscribers(product);
    }

    public void attach(ObserverAPI sub) {
        AppLogger.info("[Observer Pattern] - Attaching subscriber: " + sub.getClass().getSimpleName());
        this.subscribers.add(sub);
    }

    public void notifyAllSubscribers(Product product) {
        AppLogger.info("[Observer Pattern] - Notifying all subscribers about product: " + product.getProductName());
        Iterator iterator = this.subscribers.iterator();

        while(iterator.hasNext()) {
            ObserverAPI observer = (ObserverAPI)iterator.next();
            observer.update(product);
        }

    }
}
