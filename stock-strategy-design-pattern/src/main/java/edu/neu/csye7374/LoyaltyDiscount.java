package edu.neu.csye7374;

public class LoyaltyDiscount implements StrategyAPI {
    double disc = 0.0;

    @Override
    public double calculateDiscount(double price) {
        if (price <= 50000) {
            disc = price * 0.15;
        } else if (price <= 100000) {
            disc = 50000 * 0.15 + (price - 50000) * 0.2;
        } else {
            disc = 50000 * 0.5 + 50000 * 0.2 + (price - 100000) * 0.3;
        }
        return disc;
    }
}
