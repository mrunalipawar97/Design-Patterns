package edu.neu.csye7374.stockflow.designpattern.strategy;


public class StockStrategy {
    private StrategyAPI strategy;

    public StockStrategy(StrategyAPI strategy) {
        this.strategy = strategy;
    }

    public void executeAdd() {
        this.strategy.add();
    }

    public void executeDelete() {
        this.strategy.delete();
    }

    public void executeUpdate(int id) {
        this.strategy.update(id);
    }
}
