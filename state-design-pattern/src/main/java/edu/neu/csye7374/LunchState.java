package edu.neu.csye7374;

import java.util.ArrayList;

/***
 * Represents the lunch state in the restaurant's state-based menu system
 */
public class LunchState implements RestaurantState {

	private ArrayList<MenuItem> lunchMenu;

	public LunchState() {
		lunchMenu = new ArrayList<>();
		lunchMenu.add(new MenuItem(1, "hot dog", 1.99));
		lunchMenu.add(new MenuItem(2, "salad", 2.99));
		lunchMenu.add(new MenuItem(3, "hamburger", 3.99));
	}

	@Override
	public void displayState() {
		System.out.println("Restaurant is currently Open and serving Lunch menu");
	}

	@Override
	public void displayMenu() {
		System.out.println("Lunch Menu:");
		System.out.println("ID\tPRICE\tITEM");
		for (MenuItem item : lunchMenu) {
			System.out.println(item);
		}
	}

}
