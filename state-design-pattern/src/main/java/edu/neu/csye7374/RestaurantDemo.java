package edu.neu.csye7374;

public class RestaurantDemo {

    private RestaurantState state;

    public RestaurantDemo() {
        this.state = new ClosedState();
    }

    public void setState(RestaurantState state) {
        this.state = state;
    }

    public void displayState() {
        this.state.displayState();
    }

    public void displayMenu() {
        this.state.displayMenu();
    }

    public void open() {
        if (state instanceof ClosedState) {
            System.out.println("Moving from Closed to Open state.");
            state = new OpenState();
        } else {
            System.out.println("Invalid operation: The restaurant is already open.");
        }
    }

    public void close() {
        if (state instanceof DinnerState) {
            System.out.println("Moving from Dinner to Closed state.");
            state = new ClosedState();
        } else if (state instanceof ClosedState) {
            System.out.println("Invalid operation: The restaurant is already closed.");
        }else {
            System.out.println("Invalid operation: Restaurant cannot be closed at this time.");
        }
    }

    public void serveLunch() {
        if (state instanceof OpenState) {
            System.out.println("Moving from Open to Lunch state.");
            state = new LunchState();
        } else if (state instanceof LunchState) {
            System.out.println("Invalid operation: The restaurant is already serving lunch.");
        } else {
            System.out.println("Invalid operation: Cannot serve lunch at this time.");
        }
    }

    public void serveDinner() {
        if (state instanceof LunchState) {
            System.out.println("Moving from Lunch to Dinner state.");
            state = new DinnerState();
        } else if (state instanceof DinnerState) {
            System.out.println("Invalid operation: The restaurant is already serving dinner.");
        } else {
            System.out.println("Invalid operation: Cannot serve dinner at this time.");
        }
    }

    public static void demo(){
        // Create a new RestaurantContext object
        RestaurantDemo restaurant = new RestaurantDemo();
        // Display the current state and menu of the restaurant
        restaurant.displayState();
        restaurant.displayMenu();
        System.out.println("===============================");
        // Open the restaurant and display the current state and menu
        restaurant.open();
        restaurant.displayState();
        restaurant.displayMenu();
        System.out.println("===============================");
        // Serve lunch and display the current state and menu
        restaurant.serveLunch();
        restaurant.displayState();
        restaurant.displayMenu();
        System.out.println("===============================");
        // Serve dinner and display the current state and menu
        restaurant.serveDinner();
        restaurant.displayState();
        restaurant.displayMenu();
        System.out.println("===============================");
        // Close the restaurant and display the current state and menu
        restaurant.close();
        restaurant.displayState();
        restaurant.displayMenu();
        System.out.println("===============================");
        // try to close the restaurant when it is already closed
        restaurant.close();
        restaurant.displayState();
        restaurant.displayMenu();
        System.out.println("===============================");
        // try to serve lunch and dinner when the restaurant is closed
        restaurant.serveLunch();
        restaurant.serveDinner();
    }
}

