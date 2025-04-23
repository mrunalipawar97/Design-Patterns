package edu.neu.csye7374.stockflow.designpattern.state;

public class State implements StateAPI {

    public State() {
    }

    @Override
    public void alertStock(int stock) {
        System.out.println("Err! Default state, cannot alert stock ");
    }
}