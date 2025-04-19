package edu.neu.csye7374;

import edu.neu.csye7374.MyCloneable.MyCloneableAbstractFactory;
import edu.neu.csye7374.MyCloneable.MyCloneableAbstractFactorySingleton;
import edu.neu.csye7374.MyCloneable.MyCloneableItem;

/***
 * Prototype Pattern – Cloning objects instead of creating new ones.
 * Abstract Factory Pattern – A factory managing different item prototypes. 
 * 
 * @author mrunalipawar
 */
public class CloneableDemo {

	public static void demo() {
		MyCloneableAbstractFactory factory = MyCloneableAbstractFactorySingleton.getInstance();

		// Load factory with 26 prototype items (A-Z)
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			int id = letter - 'A' + 1;
			double price = id + 0.99;
			factory.load(id, new MyCloneableItem(id, String.valueOf(letter), price));
		}
		// Print table header
        System.out.println(String.format("%-10s %-5s %s", "Item ID", "Name", "  Price"));
        System.out.println("--------------------------------");

		// Demonstrate cloning and printing of all 26 letters
		for (int id = 1; id <= 26; id++) {
			MyCloneable clonedItem = factory.getObject(id);
			if (clonedItem != null) {
	            System.out.println(clonedItem);
	        } else {
	            System.out.println("Error: Cloning failed for ID " + id);
	        }
		}

	}
}
