package edu.neu.csye7374;

public class MenuItem {
    private int id;
    private String itemName;
    private double price;

    public MenuItem(int id, String itemName, double price) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + "\t$" + price + "\t" + itemName;
    }
}