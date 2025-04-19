package edu.neu.csye7374;

/**
 * Represents a specific type of Person that implements ArticulatePersonAPI.
 */

public class APerson implements ArticulatePersonAPI {
	
    public APerson() {}

    protected PersonAPI person;

    public APerson(PersonAPI person) {
        this.person = person;
    }

    @Override
    public void talk() {
        System.out.println("APerson talking. " + "Name: " + this.person.getName() + ",Age: " + this.person.getAge());
    }

}
