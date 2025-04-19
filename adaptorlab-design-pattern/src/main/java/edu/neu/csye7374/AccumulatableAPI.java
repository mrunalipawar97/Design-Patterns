package edu.neu.csye7374;

import java.util.List;

public interface AccumulatableAPI {

	double accumulation(List<Double> itemPrices);
    double payment(double payment);
}
