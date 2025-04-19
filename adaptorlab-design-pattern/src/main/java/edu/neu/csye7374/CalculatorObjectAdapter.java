package edu.neu.csye7374;

import java.util.List;

public class CalculatorObjectAdapter implements AccumulatableAPI {
	
	private final CalculatableAPI calculator;
    private double sum;

    public CalculatorObjectAdapter(CalculatableAPI calc) {
        super();
        this.calculator = calc;
        this.sum = 0;
    }

	@Override
	public double accumulation(List<Double> itemPrices) {
		// TODO Auto-generated method stub
		sum = 0;
        for (double itemPrice : itemPrices) {
            sum = this.calculator.operation(CalculatableAPI.OPERATION.ADD, sum, itemPrice);
        }
        return sum;
	}

	@Override
	public double payment(double payment) {
		// TODO Auto-generated method stub
		return this.calculator.operation(CalculatableAPI.OPERATION.SUB, payment, sum);
	}
}
