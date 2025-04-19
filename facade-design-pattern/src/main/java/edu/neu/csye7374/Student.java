package edu.neu.csye7374;

public class Student {

    private String firstName;

    private String lastName;

    private String id;

    public Student(String firstName, String lastName, String studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(String studentId) {
        this.id = studentId;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return firstName + "," + lastName + "," + id;
    }

}
