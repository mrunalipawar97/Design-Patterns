package edu.neu.csye7374.stockflow.designpattern.strategy;

import edu.neu.csye7374.stockflow.model.ProductPO;
import edu.neu.csye7374.stockflow.repository.ProductPORepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

import java.util.Optional;

public class ProductPOStrategy implements StrategyAPI{

    private ProductPORepository productPORepo;
    private int id;
    private ProductPO productPO;

    public ProductPOStrategy(ProductPORepository productPORepo, int id) {
        this.productPORepo = productPORepo;
        this.id = id;
    }

    public ProductPOStrategy(ProductPORepository productPORepo, ProductPO productPO) {
        this.productPORepo = productPORepo;
        this.productPO = productPO;
    }

    @Override
    public void add() {
        this.productPORepo.save(this.productPO);
        AppLogger.info("[Strategy Pattern] ProductPOStrategy Strategy - " + this.productPO + " added");
    }

    @Override
    public void update(int id) {
        Optional<ProductPO> productPO = this.productPORepo.findById(id);
        if (productPO.isEmpty()) {
            throw new RuntimeException("ProductPO does not exist");
        }
        productPO.get().setProduct(this.productPO.getProduct());
        productPO.get().setProductPOQuantity(this.productPO.getProductPOQuantity());
        productPO.get().setPurchaseOrder(this.productPO.getPurchaseOrder());

        this.productPORepo.save(productPO.get());
        AppLogger.info("[Strategy Pattern] ProductPOStrategy Strategy - " + productPO.get() + " updated");
    }

    @Override
    public void delete() {
        Optional<ProductPO> productPO = this.productPORepo.findByproductPOId(this.id);
        this.productPORepo.delete(productPO.get());
        AppLogger.info("[Strategy Pattern] ProductPOStrategy Strategy - " + productPO.get() + " deleted");
    }

}
