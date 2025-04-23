package edu.neu.csye7374.stockflow.designpattern.observer;

import edu.neu.csye7374.stockflow.model.Product;
import edu.neu.csye7374.stockflow.repository.CustomerRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class UpdateCustomers extends ObserverAPI{
	
    private final CustomerRepository customerRepo;
    
    public UpdateCustomers(Notify notify, CustomerRepository customerRepo) {
        this.notify = notify;
        this.notify.attach(this);
        this.customerRepo = customerRepo;
    }
    @Override
    public void update(Product product) {
        AppLogger.info("[Observer Pattern] - UpdateCustomers updating for product: " + product.getProductName());
        NotifyCustomers notify = new NotifyCustomers(product, this.customerRepo);
        notify.notifyAllCustomers();
    }
}
