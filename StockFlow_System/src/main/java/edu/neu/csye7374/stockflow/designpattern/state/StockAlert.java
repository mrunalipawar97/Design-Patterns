package edu.neu.csye7374.stockflow.designpattern.state;

import edu.neu.csye7374.stockflow.designpattern.facade.SendMessage;
import edu.neu.csye7374.stockflow.model.Product;
import edu.neu.csye7374.stockflow.repository.ProductRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class StockAlert implements StateAPI {
    Product product;
    ProductRepository productRepo;

    public StockAlert(Product product, ProductRepository productRepo) {
        this.product = product;
        this.productRepo = productRepo;
        AppLogger.info("State Design Pattern - StockAlert state initialized for product: " + product.getProductName());
    }

    @Override
    public void alertStock(int stock) {
        AppLogger.info("State Design Pattern - Alerting low stock for product: " + product.getProductName());
        SendMessage.message("\n******\nLOW STOCK for " + this.product.getProductName() + "\n*****\n");
    }
}

