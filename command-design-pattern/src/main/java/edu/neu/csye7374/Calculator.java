package edu.neu.csye7374;


/***
 *  Concrete Calculator Implementation
 */
public class Calculator implements CalculatorAPI {

	@Override
	public double add(double a, double b) {
		return a + b;
	}

	@Override
	public double subtract(double a, double b) {
		return a - b;
	}

	@Override
	public double multiply(double a, double b) {
		return a * b;
	}

	@Override
	public double divide(double a, double b) {
		return a / b;
	}

	@Override
	public double sum(double[] a) {
		double sum = 0;
		for (double i : a) {
			sum += i;
		}
		return sum;
	}

}
