package edu.neu.csye7374.stockflow.designpattern.observer;

import edu.neu.csye7374.stockflow.model.Product;
import edu.neu.csye7374.stockflow.repository.ProductRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UpdateDB extends ObserverAPI{
    private ProductRepository productRepo;

    public UpdateDB(Notify notify, ProductRepository productRepo) {
        this.notify = notify;
        this.productRepo = productRepo;
        this.notify.attach(this);
    }
    @Override
    public void update(Product product) {
        if (this.productRepo.findByProductName(product.getProductName()).isPresent()) {
            AppLogger.info("[Observer Pattern] - Product already exists in UpdateDB: " + product.getProductName());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product exists");
        } else {
            AppLogger.info("[Observer Pattern] - Saving product to database in UpdateDB: " + product.getProductName());
            this.productRepo.save(product);
        }
    }
}
