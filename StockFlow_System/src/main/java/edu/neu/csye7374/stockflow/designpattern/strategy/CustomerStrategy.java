package edu.neu.csye7374.stockflow.designpattern.strategy;

import edu.neu.csye7374.stockflow.model.Customer;
import edu.neu.csye7374.stockflow.repository.CustomerRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class CustomerStrategy implements StrategyAPI {
    private int customerId;
    private final CustomerRepository customerRepo;
    private Customer customer;

    public CustomerStrategy(CustomerRepository customerRepo, Customer customer) {
        this.customerRepo = customerRepo;
        this.customer = customer;
    }

    public CustomerStrategy(CustomerRepository customerRepo, int customerId) {
        this.customerId = customerId;
        this.customerRepo = customerRepo;
    }
    @Override
    public void add() {
        if (this.customerRepo.searchByCompanyName(this.customer.getCompanyName()).isPresent()) {
            AppLogger.error("[Strategy Pattern] Company already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company already exists");
        } else {
            this.customerRepo.save(this.customer);
            AppLogger.info("[Strategy Pattern] Customer Strategy - " + this.customer.getCompanyName() + " added");
        }
    }

    @Override
    public void update(int id) {
        Optional<Customer> customer = this.customerRepo.searchById(id);
        if (customer.isEmpty()) {
            AppLogger.error("[Strategy Pattern] Customer does not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer does not exist");
        }
        customer.get().setCompanyName(this.customer.getCompanyName());
        customer.get().setOrders(this.customer.getOrders());
        customer.get().setZipcode(this.customer.getZipcode());
        customer.get().setOwnerName(this.customer.getOwnerName());
        this.customerRepo.save(customer.get());
        AppLogger.info("[Strategy Pattern] Customer Strategy - " + this.customer.getCompanyName());
    }
    @Override
    public void delete() {
        Optional<Customer> customer = this.customerRepo.searchById(this.customerId);
        if (customer.isEmpty()) {
            AppLogger.error("[Strategy Pattern] Customer does not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer does not exist");
        }
        AppLogger.info("[Strategy Pattern] Customer Strategy - " + customer.get().getCompanyName() + " deleted");
        this.customerRepo.delete(customer.get());
    }
}

