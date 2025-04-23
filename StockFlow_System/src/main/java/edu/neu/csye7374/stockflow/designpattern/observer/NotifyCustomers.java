package edu.neu.csye7374.stockflow.designpattern.observer;

import edu.neu.csye7374.stockflow.designpattern.facade.FacadeClient;
import edu.neu.csye7374.stockflow.designpattern.facade.SendMessage;
import edu.neu.csye7374.stockflow.model.Customer;
import edu.neu.csye7374.stockflow.model.Product;
import edu.neu.csye7374.stockflow.repository.CustomerRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

import java.util.Iterator;
import java.util.List;

public class NotifyCustomers extends Customer {
    private List<Customer> customers;
    private Product product;
    private CustomerRepository customerRepo;

    public NotifyCustomers(Product product, CustomerRepository customerRepo) {
        this.product = product;
        this.customerRepo = customerRepo;
    }

    public void notifyAllCustomers() {
        AppLogger.info("[Observer Pattern]  - notifyAllCustomers about product: " + product.getProductName());
        StringBuilder sb = new StringBuilder();
        this.customers = this.customerRepo.findAll();
        Iterator iterator = this.customers.iterator();

        while(iterator.hasNext()) {
            Customer customer = (Customer) iterator.next();
            System.out.println("Notification sent to "+customer.getOwnerName() + " regarding the "+this.product.getProductName() + " addition");
            sb.append("Hello "+ customer.getOwnerName()).append(", ");
            sb.append(this.product.getProductName() + " is available for purchase now\n");
            FacadeClient facadeClient = new FacadeClient(new SendMessage());
            facadeClient.sendMessage(sb.toString());
            AppLogger.info("[Observer Pattern] facadeClient - " + sb.toString() + " sent");
        }

    }
}
