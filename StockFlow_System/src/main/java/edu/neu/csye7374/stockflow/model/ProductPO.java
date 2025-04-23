package edu.neu.csye7374.stockflow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing the association between a Product and a PurchaseOrder.
 * This class captures the details of a product in a purchase order.
 */
@Entity
@Data
public class ProductPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productPOId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value="product")
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "purchase_order_id")
    @JsonBackReference
    private PurchaseOrder purchaseOrder;

    @Column(nullable = false)
    private int productPOQuantity;

    public double getPrice() {
        return this.product.getPrice() * this.productPOQuantity;
    }

    @Override
    public String toString() {
        return "ProductPO(productPOId=" + productPOId +
                ", productPOQuantity=" + productPOQuantity +
                ", product=" + (product != null ? product.getProductId() : "null") +
                ", purchaseOrder=" + (purchaseOrder != null ? purchaseOrder.getProductOrderId() : "null") + ")";
    }
}
