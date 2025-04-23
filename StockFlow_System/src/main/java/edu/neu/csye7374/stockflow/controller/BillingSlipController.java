package edu.neu.csye7374.stockflow.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import edu.neu.csye7374.stockflow.designpattern.strategy.BillingSlipStrategy;
import edu.neu.csye7374.stockflow.designpattern.strategy.InventoryStrategy;
import edu.neu.csye7374.stockflow.model.BillingSlip;
import edu.neu.csye7374.stockflow.repository.BillingSlipRepository;
import edu.neu.csye7374.stockflow.repository.OrderRepository;

/***
 * 
 * @author mrunalipawar
 *
 * REST Controller for handling Billing related operations.
 * Provides APIs to generate, retrieve, and update billing slips for stock purchase orders.
 */
@RestController
@RequestMapping("/billing")
public class BillingSlipController {

	@Autowired
    private BillingSlipRepository billingSlipRepository;

    @Autowired
    private OrderRepository purchaseOrderRepository;

    /**
     * Retrieve all Billing Slips.
     *
     * @return List of all BillingSlip entities from the database.
     */
    @GetMapping("/getAll")
    public List<BillingSlip> getAll() {
        return billingSlipRepository.findAll();
    }
    
    /**
     * Retrieve a specific Billing Slip by ID.
     *
     * @param id the ID of the Billing Slip to retrieve.
     * @return BillingSlip entity if found.
     */
    @GetMapping("/getBilling/{id}")
    public BillingSlip getBillingSlip(@PathVariable int id) {
        return billingSlipRepository.findById(id).get();
    }

    /**
     * Generate and save a new Billing Slip for the given Purchase Order ID.
     *
     * @param id the ID of the Purchase Order.
     */
    @PostMapping("/generateBillingSlip/{id}")
    public void save(@PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(
        		new BillingSlipStrategy(billingSlipRepository, id, purchaseOrderRepository));
        strategy.executeAdd();
    }

    /**
     * Update an existing Billing Slip.
     *
     * @param id the ID of the Billing Slip to update.
     */
    @PutMapping("/update/{id}")
    public void update(@RequestBody BillingSlip billingSlip, @PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(
        		new BillingSlipStrategy(billingSlipRepository, billingSlip));
        strategy.executeUpdate(id);
    }

    /**
     * Delete an existing Billing Slip.
     *
     * @param id the ID of the Billing Slip to delete.
     */
    @DeleteMapping("/delete/{id}")
    public void deletebyID(@PathVariable int id) {
        InventoryStrategy strategy = new InventoryStrategy(
        		new BillingSlipStrategy(billingSlipRepository, id));
        strategy.executeDelete();
    }
}
