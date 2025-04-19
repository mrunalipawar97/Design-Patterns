package edu.neu.csye7374;

public class Calculator implements CalculatableAPI {
    @Override
    public double operation(OPERATION op, double a, double b) {
        switch (op) {
            case ADD:
                return a + b;
            case SUB:
                return a - b;
            case MULT:
                return a * b;
            case DIV:
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }
}
