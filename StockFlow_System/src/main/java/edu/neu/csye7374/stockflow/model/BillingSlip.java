package edu.neu.csye7374.stockflow.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

/***
 * Entity class representing the Billing Slip details for a Purchase Order.
 * This class captures the payment-related information associated with a specific stock purchase.
 */

@Entity
@Data
public class BillingSlip {

	/**
     * Primary key for the BillingSlip entity.
     * Auto-generated using identity strategy.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
     * One-to-one mapping with the PurchaseOrder entity.
     * Represents the purchase order for which this billing slip is generated.
     */
	@OneToOne
	@JoinColumn(name = "purchaseOrder_id")
	@JsonManagedReference
	private PurchaseOrder purchaseOrder;

	/**
     * Date of payment for the billing slip.
     * Cannot be null.
     */
	@Column(nullable = false)
	private String paymentDate;
}
