package edu.neu.csye7374.stockflow.designpattern.strategy;

import edu.neu.csye7374.stockflow.designpattern.observer.Notify;
import edu.neu.csye7374.stockflow.designpattern.observer.UpdateCustomers;
import edu.neu.csye7374.stockflow.designpattern.observer.UpdateDB;
import edu.neu.csye7374.stockflow.model.Product;
import edu.neu.csye7374.stockflow.repository.CustomerRepository;
import edu.neu.csye7374.stockflow.repository.ProductRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

import java.util.Optional;

public class ProductStrategy implements StrategyAPI {

    private ProductRepository productRepo;
    private int id;
    private Product product;
    private CustomerRepository customerRepo;

    public ProductStrategy(ProductRepository productRepo, int id) {
        this.productRepo = productRepo;
        this.id = id;
    }

    public ProductStrategy(ProductRepository productRepo, Product product) {
        this.productRepo = productRepo;
        this.product = product;
    }

    public ProductStrategy(ProductRepository productRepo, Product product, CustomerRepository customerRepo) {
        this.productRepo = productRepo;
        this.product = product;
        this.customerRepo = customerRepo;
    }


    @Override
    public void add() {
        Notify a = new Notify();
        new UpdateCustomers(a, this.customerRepo);
        new UpdateDB(a, this.productRepo);
        a.setState(this.product);
        AppLogger.info("[Strategy Pattern] Product Strategy  - " + this.product + " added");
    }

    @Override
    public void update(int id) {
        Optional<Product> product = this.productRepo.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Product does not exist");
        }
        product.get().setProductName(this.product.getProductName());
        product.get().setPrice(this.product.getPrice());
        product.get().setQuantity(this.product.getQuantity());
        product.get().setPrice(this.product.getPrice());
        product.get().setPurchaseOrders(this.product.getPurchaseOrders());
        this.productRepo.save(product.get());
        AppLogger.info("[Strategy Pattern] Product Strategy  - " + product.get() + " updated");
    }

    @Override
    public void delete() {
        Optional<Product> product = this.productRepo.findById(this.id);
        this.productRepo.delete(product.get());
        AppLogger.info("[Strategy Pattern] Product Strategy  - " + product.get() + " updated");
    }

}
