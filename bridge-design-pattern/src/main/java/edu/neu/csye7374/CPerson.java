package edu.neu.csye7374;


public class CPerson extends BPerson implements AmbulatePersonAPI {
    protected EmployeeAPI employee;

    public CPerson(EmployeeAPI person) {
        this.employee = person;
    }

    @Override
    public void walk() {
        System.out.println("CPerson Walking. " + "Name: " + this.employee.getName() + ",Age: " + this.employee.getAge() +
                " , Job: " + this.employee.getJob() + ", Wage: " + this.employee.getWage());
    }

    @Override
    public void run() {
        System.out.println("CPerson Running. " + "Name: " + this.employee.getName() + ",Age: " + this.employee.getAge() +
                " , Job: " + this.employee.getJob() + ", Wage: " + this.employee.getWage());
    }

    @Override
    public void jump() {
        System.out.println("CPerson Jumping. " + "Name: " + this.employee.getName() + ",Age: " + this.employee.getAge() +
                " , Job: " + this.employee.getJob() + ", Wage: " + this.employee.getWage());
    }

    @Override
    public void talk() {
        System.out.println("CPerson Talking. " + "Name: " + this.employee.getName() + ",Age: " + this.employee.getAge() +
                " , Job: " + this.employee.getJob() + ", Wage: " + this.employee.getWage());
    }
}

