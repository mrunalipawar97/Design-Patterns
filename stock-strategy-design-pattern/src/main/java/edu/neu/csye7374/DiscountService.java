package edu.neu.csye7374;

public class DiscountService {
    private StrategyAPI strategyAPI;

    public void setStrategy(StrategyAPI strategyAPI) {
        this.strategyAPI = strategyAPI;
    }

    public double calculateDiscount(double price) {
        return strategyAPI.calculateDiscount(price);
    }

    public static void demo() {
        DiscountService discountService = new DiscountService();
        discountService.setStrategy(new RegularDiscount());
        double discount = discountService.calculateDiscount(1000);
        System.out.println("The RegularDiscount for 1000 is: " + discount);

        discountService.setStrategy(new LoyaltyDiscount());
        double discount1 = discountService.calculateDiscount(2200);

        System.out.println("The LoyaltyDiscount for 2200 is: " + discount1);

        discountService.setStrategy(new SeasonalDiscount());
        double discount2 = discountService.calculateDiscount(3000);
        System.out.println("The SeasonalDiscount for 3000 is: " + discount2);
    }
}
