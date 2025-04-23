package edu.neu.csye7374.stockflow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.neu.csye7374.stockflow.designpattern.decorator.Purchase;
import edu.neu.csye7374.stockflow.designpattern.state.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Entity class representing a Purchase Order in the StockFlow system.
 * This class stores details of a purchase order, including the associated products, customer, 
 * payment status, and the billing slip.
 */
@Entity
@Data
public class PurchaseOrder implements Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productOrderId;

	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<ProductPO> products;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonBackReference(value="customer")
	private Customer customer;

	@Column(nullable = false)
	private boolean paid;

	@Column(nullable = false)
	private double totalAmount;

	/**
     * One-to-one relationship with BillingSlip.
     * A purchase order can have one billing slip.
     * The @JsonBackReference annotation ensures that the relationship is not serialized in both directions.
     */
	@OneToOne(mappedBy = "purchaseOrder")
	@JsonBackReference
	private BillingSlip billingSlip;

	@Transient // Not persisted in database
	private OrderState state;

	@Column(nullable = false)
	private String stateValue;

	// Constructor
	public PurchaseOrder() {
		// Initialize with default state
		this.state = new CreatedState();
		this.stateValue = this.state.getStateName();
		this.paid = false;
	}

	// Methods to handle state transitions
	public void nextState() {
		state.updateState(this);
		// Update the persisted state value
		this.stateValue = this.state.getStateName();
	}

	public void processOrder() {
		state.processOrder(this);
	}

	// Method to restore state from database
	@PostLoad
	private void restoreStateFromDatabase() {
        switch (this.stateValue) {
            case "CREATED" -> this.state = new CreatedState();
            case "PROCESSING" -> this.state = new ProcessingState();
            case "SHIPPED" -> this.state = new ShippedState();
            case "COMPLETED" -> this.state = new CompletedState();
            default -> this.state = new CreatedState();
        }
	}

	// Setter for state that also updates the persisted stateValue
	public void setState(OrderState state) {
		this.state = state;
		this.stateValue = state.getStateName();
	}

	@Override
	public String toString() {
		return "PurchaseOrder(productOrderId=" + productOrderId +
				", paid=" + paid +
				", totalAmount=" + totalAmount +
				", customer=" + (customer != null ? customer.getId() : "null") + ")";
	}
}
