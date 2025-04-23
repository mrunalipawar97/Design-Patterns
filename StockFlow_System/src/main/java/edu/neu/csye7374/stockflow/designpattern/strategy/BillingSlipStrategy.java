package edu.neu.csye7374.stockflow.designpattern.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import edu.neu.csye7374.stockflow.designpattern.facade.FacadeClient;
import edu.neu.csye7374.stockflow.designpattern.facade.PDFGen;
import edu.neu.csye7374.stockflow.model.BillingSlip;
import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.repository.BillingSlipRepository;
import edu.neu.csye7374.stockflow.repository.OrderRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

/***
 * Strategy implementation for managing Billing Slip operations.
 * Handles the addition, update, and deletion of BillingSlips.
 */
public class BillingSlipStrategy implements StrategyAPI {

	private int id;
	private BillingSlip billingSlip;
	private OrderRepository orderRepository;
	private BillingSlipRepository billingSlipRepository;

	/**
     * Constructor for updating an existing Billing Slip.
     *
     * @param billingSlipRepository the repository for billing slips
     * @param billingSlip the BillingSlip to be updated
     */
	public BillingSlipStrategy(BillingSlipRepository billingSlipRepository, BillingSlip billingSlip) {
		this.billingSlipRepository = billingSlipRepository;
		this.billingSlip = billingSlip;
	}

	/**
     * Constructor for adding a new Billing Slip with an associated Purchase Order.
     *
     * @param billingSlipRepository the repository for billing slips
     * @param id the ID of the purchase order associated with the billing slip
     * @param orderRepo the repository for orders
     */
	public BillingSlipStrategy(BillingSlipRepository billingSlipRepository, int id, OrderRepository orderRepo) {
		this.billingSlipRepository = billingSlipRepository;
	 	this.id = id;
	    this.orderRepository = orderRepo;
	}

	/**
     * Constructor for deleting a Billing Slip by ID.
     *
     * @param billingSlipRepository the repository for billing slips
     * @param id the ID of the Billing Slip to be deleted
     */
	public BillingSlipStrategy(BillingSlipRepository billingSlipRepository, int id) {
		this.billingSlipRepository = billingSlipRepository;
		this.id = id;
	}

	/**
     * Adds a new Billing Slip for a Purchase Order. 
     * Marks the order as paid and generates a PDF for the invoice.
     */
	@Override
	public void add() {
		Optional<PurchaseOrder> po = this.orderRepository.findById(this.id);
        if (po.isEmpty()) {
            throw new RuntimeException("Purchase order does not exist");
        }
        po.get().setPaid(true);
        this.orderRepository.save(po.get());
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String paymentDate = formatter.format(date);
        BillingSlip billingSlip = new BillingSlip();
        billingSlip.setPaymentDate(paymentDate);
        billingSlip.setPurchaseOrder(po.get());
        
        // Save the new BillingSlip and generate a PDF invoice
        int invoiceID = this.billingSlipRepository.save(billingSlip).getId();
        AppLogger.info("[Strategy Pattern] Billing Slip Strategy - " + invoiceID + " added");
        FacadeClient facadeClient = new FacadeClient(new PDFGen());
        facadeClient.generatePDF(invoiceID, this.billingSlipRepository);
        AppLogger.info("[Strategy Pattern] Billing Slip Strategy - " + invoiceID + " PDF generated");
	}

	/**
     * Updates an existing Billing Slip.
     *
     * @param id the ID of the Billing Slip to be updated
     */
	@Override
	public void update(int id) {
		Optional<BillingSlip> billingSlip = this.billingSlipRepository.findById(id);
        if (billingSlip.isEmpty()) {
        	AppLogger.error("[Strategy Pattern] Billing Slip Strategy - " + this.billingSlip + " Invoice does not exist");
            throw new RuntimeException("Invoice does not exist");       
        }
        billingSlip.get().setPaymentDate(this.billingSlip.getPaymentDate());
        billingSlip.get().setPurchaseOrder(this.billingSlip.getPurchaseOrder());
        this.billingSlipRepository.save(billingSlip.get());
        AppLogger.info("[Strategy Pattern] Billing Slip Strategy - " + billingSlip.get()+ " updated");
	}

	/**
     * Deletes a Billing Slip by ID.
     */
	@Override
	public void delete() {
		Optional<BillingSlip> billingSlip = this.billingSlipRepository.findById(this.id);
        this.billingSlipRepository.delete(billingSlip.get());
        AppLogger.info("[Strategy Pattern] Billing Slip Strategy - " + billingSlip.get()+ " deleted");
	}

}
