package edu.neu.csye7374;

public class DivCommand implements Command{
    private CalculatorAPI calculator;
    private double x;
    private double y;

    public DivCommand(CalculatorAPI calculator, double x, double y) {
        this.calculator = calculator;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        System.out.println(x + " divided by "+ y + " = " + calculator.divide(x, y));
    }
}
