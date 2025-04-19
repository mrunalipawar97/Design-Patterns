package edu.neu.csye7374;

public class SumCommand implements Command{

    private CalculatorAPI calculator;
    private double[] numbers;

    public SumCommand(CalculatorAPI calculator, double[] numbers) {
        this.calculator = calculator;
        this.numbers = numbers;
    }

    @Override
    public void execute() {

        System.out.println("Result of summing up numbers = " + calculator.sum(numbers));
    }
}
