package edu.neu.csye7374;

public class SeasonalDiscount implements StrategyAPI {

    double seasonDisc;


    @Override
    public double calculateDiscount(double price) {

        if (price < 500) {
            seasonDisc = 0.05;
        } else if (price < 1000) {
            seasonDisc = 0.15;
        } else {
            seasonDisc = 0.25;
        }
        return (price * seasonDisc);

    }
}
