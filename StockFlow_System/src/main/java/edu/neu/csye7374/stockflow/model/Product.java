package edu.neu.csye7374.stockflow.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Entity class representing a Product in the StockFlow system.
 * This class extends a base Product class from a design pattern (decorator).
 */
@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(nullable = false, unique = true)
    private String productName;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonManagedReference(value="product")
    private List<ProductPO> purchaseOrders;

    @Override
    public String toString() {
        return "Product(productId=" + productId + ", productName=" + productName +
                ", quantity=" + quantity + ", price=" + price + ")";
    }
}
