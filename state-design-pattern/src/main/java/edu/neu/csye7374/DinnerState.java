package edu.neu.csye7374;

import java.util.ArrayList;

/***
 * Represents the dinner state in the restaurant's state-based menu system
 */
public class DinnerState implements RestaurantState {

	private ArrayList<MenuItem> dinnerMenu;

	public DinnerState() {
		dinnerMenu = new ArrayList<>();
		dinnerMenu.add(new MenuItem(1, "soup", 11.99));
		dinnerMenu.add(new MenuItem(2, "salad", 12.99));
		dinnerMenu.add(new MenuItem(3, "steak", 13.99));
	}

	@Override
	public void displayState() {
		System.out.println("Restaurant is currently Open and serving Dinner menu");
	}

	@Override
	public void displayMenu() {
		System.out.println("Dinner Menu:");
		System.out.println("ID\tPRICE\tITEM");
		for (MenuItem item : dinnerMenu) {
			System.out.println(item);
		}
	}

}
