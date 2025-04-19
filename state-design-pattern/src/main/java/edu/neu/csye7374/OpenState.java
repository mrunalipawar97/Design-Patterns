package edu.neu.csye7374;

public class OpenState implements RestaurantState{



    @Override
    public void displayState() {
        System.out.println("Restaurant is Open, Choose a menu option");
    }

    @Override
    public void displayMenu() {
        System.out.println("No Menu to display, Choose a menu option");
    }
}
