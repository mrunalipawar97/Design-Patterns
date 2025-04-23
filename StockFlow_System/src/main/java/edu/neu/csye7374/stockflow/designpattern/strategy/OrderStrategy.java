package edu.neu.csye7374.stockflow.designpattern.strategy;

import java.util.List;
import java.util.Optional;
import edu.neu.csye7374.stockflow.designpattern.state.StateAPI;
import edu.neu.csye7374.stockflow.designpattern.state.StockAlert;
import edu.neu.csye7374.stockflow.model.Product;
import edu.neu.csye7374.stockflow.model.ProductPO;
import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.repository.OrderRepository;
import edu.neu.csye7374.stockflow.repository.ProductRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class  OrderStrategy implements StrategyAPI {
	private ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private PurchaseOrder insertedPO;
	private PurchaseOrder purchaseOrder;
	private int id;

	public OrderStrategy(OrderRepository orderRepository, ProductRepository productRepository, PurchaseOrder insertedPO,
			PurchaseOrder purchaseOrder) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.purchaseOrder = purchaseOrder;
		this.insertedPO = insertedPO;
	}

	public OrderStrategy(OrderRepository orderRepo, PurchaseOrder purchaseOrder) {
		this.orderRepository = orderRepo;
		this.purchaseOrder = purchaseOrder;
	}

	public OrderStrategy(OrderRepository orderRepo, int id) {
		this.orderRepository = orderRepo;
		this.id = id;
	}

	@Override
	public void add() {
		AppLogger.info("[Strategy Pattern] Order Strategy - " + this.insertedPO.getProductOrderId() + " Inserted");
		List<ProductPO> productPOs = this.insertedPO.getProducts();

        for (ProductPO proPO : productPOs) {
            StateAPI state;
            Product product = proPO.getProduct();
            product.setQuantity(product.getQuantity() - proPO.getProductPOQuantity());
            productRepository.save(product);
            AppLogger.info("[Strategy Pattern] Order Strategy - Purchase order : " + product + " added");

            int difference = product.getQuantity();
            int count;

            if (difference <= 100) {
                state = new StockAlert(product, this.productRepository);
                count = difference;
                state.alertStock(count);
            }
        }
	}

	@Override
	public void update(int id) {
		Optional<PurchaseOrder> purchaseOrder = this.orderRepository.findById(id);
		if (purchaseOrder.isEmpty()) {
			AppLogger.error("[Strategy Pattern] Order Strategy - Purchase Order does not exist");
			throw new RuntimeException("Purchase Order does not exist");
		}
		purchaseOrder.get().setPaid(this.purchaseOrder.isPaid());
		this.orderRepository.save(purchaseOrder.get());
		AppLogger.info("[Strategy Pattern] Order Strategy - Purchase order : " + purchaseOrder.get() + " Updated");
	}

	@Override
	public void delete() {
		Optional<PurchaseOrder> purchaseOrder = this.orderRepository.findById(this.id);
		this.orderRepository.delete(purchaseOrder.get());
		AppLogger.info("[Strategy Pattern] Order Strategy - Purchase order : " + purchaseOrder.get() + " deleted");
	}
}
