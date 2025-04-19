package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void runObject() {
        System.out.println("Running Object Adapter");
        AccumulatableAPI classAdapter = new CalculatorClassAdapter();

        List<Double> itemPrices = List.of(40.0, 68.0, 70.0);
        double total = classAdapter.accumulation(itemPrices);
        System.out.println("Total Price: " + total);

        double cash = 500;
        double change = classAdapter.payment(cash);
        System.out.println("Total Cash paid is: "+cash+" & Change given: " + change);
    }


    public static void runClass() {
        System.out.println("Running Class Adapter");
        CalculatableAPI concreteCalculator = new Calculator();

        AccumulatableAPI objectAdapter = new CalculatorObjectAdapter(concreteCalculator);

        List<Double> itemPrices = new ArrayList<>();
        itemPrices.add(68.0);
        itemPrices.add(40.0);
        itemPrices.add(70.0);

        double totalPrice = objectAdapter.accumulation(itemPrices);

        System.out.println("Total price: " + totalPrice);

        double cash = 500;
        double change = objectAdapter.payment(cash);

        System.out.println("Total Cash paid is: "+cash+" & Change given: " + change);
    }
}
