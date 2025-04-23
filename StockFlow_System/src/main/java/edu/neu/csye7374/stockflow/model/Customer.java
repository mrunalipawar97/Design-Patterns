package edu.neu.csye7374.stockflow.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/**
 * Entity class representing a Customer in the system.
 * Annotated with Lombok's @Data for boilerplate code generation (getters, setters, equals, hashCode, etc.).
 */
@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String ownerName;

	@Column(nullable = false, unique = true)
	private String companyName;

	@Column(nullable = false)
	private String zipcode;
	
	/**
     * List of purchase orders associated with the customer.
     * Mapped by the 'customer' field in the PurchaseOrder entity.
     * Uses MERGE cascade type and lazy loading.
     */
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "customer", fetch = FetchType.LAZY)
	@JsonManagedReference(value="customer")
	private List<PurchaseOrder> orders;
	
	@Override
	public String toString() {
		return this.id + " " + this.ownerName; 
	}
	
}
