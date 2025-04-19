package edu.neu.csye7374;

public class MulCommand implements Command {

    private CalculatorAPI calculator;
    private double a;
    private double b;

    public MulCommand(CalculatorAPI calculator, double a, double b) {
        this.calculator = calculator;
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        System.out.println("Result of multiplying   " + a + " and "+ b + " = " + calculator.multiply(a, b));
    }
}
