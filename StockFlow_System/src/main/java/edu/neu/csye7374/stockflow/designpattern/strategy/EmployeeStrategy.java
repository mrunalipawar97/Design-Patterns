package edu.neu.csye7374.stockflow.designpattern.strategy;

import edu.neu.csye7374.stockflow.model.Employee;
import edu.neu.csye7374.stockflow.repository.EmployeeRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class EmployeeStrategy implements StrategyAPI {
    private final EmployeeRepository employeeRepo;
    private int id;
    private Employee employee;

    public EmployeeStrategy(EmployeeRepository employeeRepo, Employee employee) {
        this.employeeRepo = employeeRepo;
        this.employee = employee;
    }

    public EmployeeStrategy(EmployeeRepository employeeRepo, int id) {
        this.employeeRepo = employeeRepo;
        this.id = id;
    }
    @Override
    public void add() {
        if (this.employeeRepo.findByUsername(this.employee.getUsername()).isPresent()) {
        	AppLogger.error("[Strategy Pattern] Employee Strategy - Username already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        } else {
            this.employeeRepo.save(this.employee);
            AppLogger.info("[Strategy Pattern] Employee Strategy - " + this.employee+ " added");
        }
    }
    @Override
    public void update(int id) {
        Optional<Employee> emp = this.employeeRepo.findById(id);
        if (emp.isEmpty()) {
        	AppLogger.error("[Strategy Pattern] Employee Strategy - Employee does not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee does not exist");
        }
        emp.get().setDesignation(this.employee.getDesignation());
        emp.get().setDob(this.employee.getDob());
        emp.get().setFullName(this.employee.getFullName());
        emp.get().setPassword(this.employee.getPassword());
        emp.get().setRating(this.employee.getRating());
        emp.get().setSalary(this.employee.getSalary());
        emp.get().setUsername(this.employee.getUsername());

        this.employeeRepo.save(emp.get());
        AppLogger.info("[Strategy Pattern] Employee Strategy - " + emp.get()+ " updated");
    }
    @Override
    public void delete() {
        Optional<Employee> emp = this.employeeRepo.findById(this.id);
        if (emp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee does not exist");
        }
        this.employeeRepo.delete(emp.get());
        AppLogger.info("[Strategy Pattern] Employee Strategy - " + emp.get()+ " deleted");
    }
}
