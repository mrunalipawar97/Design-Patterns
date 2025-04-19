package edu.neu.csye7374;

public class ClosedState implements RestaurantState{

    @Override
    public void displayState() {
        System.out.println("Restaurant is currently Closed");
    }

    @Override
    public void displayMenu() {
        System.out.println("No Menu to display, Restaurant is Closed");
    }
}
