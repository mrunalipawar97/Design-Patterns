package edu.neu.csye7374;


public class BPerson extends APerson implements AmbulatePersonAPI {
    protected PersonAPI person;

    public BPerson() {
        super();
    }

    public BPerson(PersonAPI person) {
        super();
        this.person = person;
    }

    @Override
    public void walk() {
        System.out.println("BPerson Walking. " + "Name: " + this.person.getName() + ",Age: " + this.person.getAge());
    }

    @Override
    public void run() {
        System.out.println("BPerson Running. " + "Name: " + this.person.getName() + ",Age: " + this.person.getAge());

    }

    @Override
    public void jump() {
        System.out.println("BPerson Jumping. " + "Name: " + this.person.getName() + ",Age: " + this.person.getAge());
    }

    @Override
    public void talk() {
        System.out.println("BPerson Talking. " + "Name: " + this.person.getName() + ",Age: " + this.person.getAge());
    }
}
