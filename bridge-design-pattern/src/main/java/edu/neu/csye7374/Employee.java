package edu.neu.csye7374;

public class Employee extends Person implements EmployeeAPI {
    private String job;
    private double wage;

    public Employee(int age, String name, String job, double wage) {
        super(age, name);
        this.name = "Employee " + name;
        this.job = "Employee " + job;
        this.wage = wage;
    }

    @Override
    public double getWage() {
        return this.wage;
    }

    @Override
    public void setWage(double wage) {
        this.wage = wage;
    }

    @Override
    public String getJob() {
        return this.job;
    }

    @Override
    public void setJob(String job) {
        this.job = job;
    }
}

