package edu.neu.csye7374;

public class RegularDiscount implements StrategyAPI {

	@Override
	public double calculateDiscount(double price) {
		double discountRate = 0.10; // 10% discount
		return price * discountRate;

	}

}
