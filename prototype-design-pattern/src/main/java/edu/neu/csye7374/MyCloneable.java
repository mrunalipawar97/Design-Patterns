package edu.neu.csye7374;

import java.util.HashMap;

/***
 * MyCloneable class implementing Cloneable interface
 * 
 * @author mrunalipawar
 */
public abstract class MyCloneable implements Cloneable {
	protected int id;
	protected String name;
	protected double price;

	public MyCloneable(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public MyCloneable() {

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public Object clone() {
		try {
			return (MyCloneable) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Cloning failed", e);
		}
	}

	@Override
	public String toString() {
		return "ID: " + id + "\tName: " + name + "\t Price: $" + price;
	}
	

	/**
	 * Abstract Factory API API for abstract factory of cloneable objects
	 * 
	 */
	public static abstract class MyCloneableAbstractFactoryAPI {

		/**
		 * @param id        ID of prototype
		 * @param prototype one cloneable prototype object
		 */
		public abstract void load(int id, MyCloneable prototype);

		/**
		 * dynamically load list of prototypes
		 * 
		 * @param prototypeList list of cloneable prototype objects
		 */
		public abstract void load(HashMap<Integer, MyCloneable> prototypeList);

		/**
		 * return clone of requested object
		 * 
		 * @param id ID of requested object
		 * @return cloned object
		 */
		public abstract MyCloneable getObject(int id);

	}

	/**
	 * MyCloneableItem derived from MyCloneable
	 *
	 */
	public static class MyCloneableItem extends MyCloneable {

		public MyCloneableItem(int id, String name, double price) {
			super(id, name, price);
		}

	}

	/**
	 * Abstract Factory Pattern – A factory managing different item prototypes.
	 * MyCloneableAbstractFactory implementing the API
	 */
	public static class MyCloneableAbstractFactory extends MyCloneableAbstractFactoryAPI {

		private final HashMap<Integer, MyCloneable> prototypes = new HashMap<>();

		@Override
		public void load(int id, MyCloneable prototype) {
			prototypes.put(id, prototype);
		}

		@Override
		public void load(HashMap<Integer, MyCloneable> prototypeList) {
			prototypes.putAll(prototypeList);
		}

		@Override
		public MyCloneable getObject(int id) {
			MyCloneable prototype = prototypes.get(id);
			if (prototype == null) {
				return null; // Return null if no prototype exists for given item ID
			}

			return (MyCloneable) prototype.clone();
		}

	}

	/**
	 * Singleton Pattern – Ensuring a single instance of the factory. 
	 * Singleton Factory Class
	 * 
	 */
	public static class MyCloneableAbstractFactorySingleton {
		private static MyCloneableAbstractFactory instance = null;

		private MyCloneableAbstractFactorySingleton() {
		}

		public static MyCloneableAbstractFactory getInstance() {
			if (instance == null) {
				instance = new MyCloneableAbstractFactory();
			}
			return instance;
		}
	}

}
