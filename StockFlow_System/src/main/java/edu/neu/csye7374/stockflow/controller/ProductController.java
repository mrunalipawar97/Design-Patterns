package edu.neu.csye7374.stockflow.controller;

import edu.neu.csye7374.stockflow.designpattern.strategy.InventoryStrategy;
import edu.neu.csye7374.stockflow.designpattern.strategy.ProductStrategy;
import edu.neu.csye7374.stockflow.model.Product;
import edu.neu.csye7374.stockflow.repository.CustomerRepository;
import edu.neu.csye7374.stockflow.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable int id) {
        return productRepo.findByProductId(id).get();
    }

    @PostMapping("/save")
    public void save(@RequestBody Product product) {
        InventoryStrategy strategy = new InventoryStrategy(new ProductStrategy(productRepo, product, customerRepo));
        strategy.executeAdd();
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Product product, @PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(new ProductStrategy(productRepo, product));
        strategy.executeUpdate(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletebyID(@PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(new ProductStrategy(productRepo, id));
        strategy.executeDelete();
    }
}
