package edu.neu.csye7374;

public interface CalculatableAPI {
    enum OPERATION {ADD, SUB, MULT, DIV}

    double operation(OPERATION op, double a,
                     double b);
}
