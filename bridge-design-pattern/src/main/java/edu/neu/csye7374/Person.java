package edu.neu.csye7374;

public class Person implements PersonAPI{

    public Person(int age, String name) {
        this.age = age;
        this.name = "Person " + name;
    }

    int age;
    String name;

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
