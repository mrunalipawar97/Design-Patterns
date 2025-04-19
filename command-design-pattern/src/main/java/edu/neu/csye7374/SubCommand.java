package edu.neu.csye7374;

public class SubCommand implements Command{

    private CalculatorAPI calculator;
    private double a;
    private double b;

    public SubCommand(CalculatorAPI calculator, double a, double b) {
        this.calculator = calculator;
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        System.out.println("Result of sub  " + b + " from "+ a + " = " + calculator.subtract(a, b));
    }
}
