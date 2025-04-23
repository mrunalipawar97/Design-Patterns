package edu.neu.csye7374.stockflow.controller;

import edu.neu.csye7374.stockflow.designpattern.command.OrderCommandService;
import edu.neu.csye7374.stockflow.designpattern.decorator.ServiceFeeDecorator;
import edu.neu.csye7374.stockflow.designpattern.decorator.TaxDecorator;
import edu.neu.csye7374.stockflow.designpattern.state.CreatedState;
import edu.neu.csye7374.stockflow.model.Product;
import edu.neu.csye7374.stockflow.model.ProductPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.neu.csye7374.stockflow.designpattern.strategy.OrderStrategy;
import edu.neu.csye7374.stockflow.designpattern.strategy.StockStrategy;
import edu.neu.csye7374.stockflow.model.PurchaseOrder;
import edu.neu.csye7374.stockflow.repository.OrderRepository;
import edu.neu.csye7374.stockflow.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/***
 * @author mrunalipawar
 */
@RestController
@RequestMapping("/purchaseOrder")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderCommandService orderCommandService;

	@GetMapping("/getAll")
	public List<PurchaseOrder> getAll() {
		return orderRepository.findAll();
	}
	
	@GetMapping("/getPurchaseOrder/{id}")
	public PurchaseOrder getPurchaseOrder(@PathVariable int id) {
		return orderRepository.findById(id).get();
	}
	
	@PutMapping("/update/{id}")
	public void update(@RequestBody PurchaseOrder purchaseOrder, @PathVariable int id) {
		StockStrategy strategy = new StockStrategy(new OrderStrategy(orderRepository, purchaseOrder));
		strategy.executeUpdate(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		StockStrategy strategy = new StockStrategy(new OrderStrategy(orderRepository, id));
		strategy.executeDelete();
	}

	@PostMapping("/save")
	public void save(@RequestBody PurchaseOrder purchaseOrder) {
		for(ProductPO productPO: purchaseOrder.getProducts()) {
			Optional<Product> product = productRepository.findById(productPO.getProduct().getProductId());
			if (product.isPresent()) {
				productPO.setProduct(product.get());
			} else {
				throw new RuntimeException("Product not found");
			}
		}

		purchaseOrder.setTotalAmount(purchaseOrder.getProducts().stream()
				.mapToDouble(ProductPO::getPrice)
				.sum());
		purchaseOrder.setTotalAmount(new ServiceFeeDecorator(new TaxDecorator(purchaseOrder)).getTotalAmount());
		purchaseOrder.setState(new CreatedState());
		PurchaseOrder insertedPO = orderRepository.save(purchaseOrder);
		StockStrategy strategy = new StockStrategy(new OrderStrategy
				(orderRepository, productRepository, insertedPO ,purchaseOrder));
		strategy.executeAdd();
	}

	@PutMapping("/changeState/{id}")
	public void changeOrderState(@PathVariable int id, @RequestParam String newState) {
		orderCommandService.changeOrderState(id, newState);
	}

	@PutMapping("/undoLastOperation")
	public void undoLastOperation() {
		orderCommandService.undoLastOperation();
	}
}
