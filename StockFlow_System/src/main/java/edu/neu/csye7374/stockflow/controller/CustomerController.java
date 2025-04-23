package edu.neu.csye7374.stockflow.controller;

import edu.neu.csye7374.stockflow.designpattern.strategy.CustomerStrategy;
import edu.neu.csye7374.stockflow.designpattern.strategy.InventoryStrategy;
import edu.neu.csye7374.stockflow.model.Customer;
import edu.neu.csye7374.stockflow.repository.CustomerRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * @author mrunalipawar
 * @apiNote - Controller for Customers
 * 
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    // Dependency Injection by Autowiring for Customer Repo
    @Autowired
    private CustomerRepository customerRepo;

    // Getting All the customers
    @GetMapping("/getAll")
    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

    // Saving the customer data in DB
    @PostMapping("/save")
    public void save(@RequestBody Customer customer) {
        InventoryStrategy strategy = new InventoryStrategy(new CustomerStrategy(customerRepo, customer));
        strategy.executeAdd();
    }

    // Get specific Customer
    @GetMapping("/getCustomer/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return customerRepo.findById(id).get();
    }

    // Delete Specific Customer
    @DeleteMapping("/delete/{id}")
    public void deletebyID(@PathVariable int id) {
    	AppLogger.info("Id to be deleted :"+ id);
        InventoryStrategy strategy = new InventoryStrategy(new CustomerStrategy(customerRepo, id));
        strategy.executeDelete();
    }

    // Update Customer Info
    @PutMapping("/update/{id}")
    public void update(@RequestBody Customer customer, @PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(new CustomerStrategy(customerRepo, customer));
        strategy.executeUpdate(id);
    }

}