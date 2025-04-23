package edu.neu.csye7374.stockflow.designpattern.decorator;


public class CostDecorator implements Purchase {
	protected Purchase purchase;

	public CostDecorator(Purchase purchase) {
		this.purchase = purchase;
	}

	@Override
	public double getTotalAmount() {
		return purchase.getTotalAmount();
	}
}
