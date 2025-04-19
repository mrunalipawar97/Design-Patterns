package edu.neu.csye7374;

public class AddCommand implements Command{
    private CalculatorAPI calculator;
    private double x;
    private double y;

    public AddCommand(CalculatorAPI calculator, double x, double y) {
        this.calculator = calculator;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(){
        System.out.println("Sum of  " + x + " and "+ y + " = " + calculator.add(x, y));
    }
}
