package edu.neu.csye7374.stockflow.controller;

import edu.neu.csye7374.stockflow.designpattern.strategy.EmployeeStrategy;
import edu.neu.csye7374.stockflow.designpattern.strategy.StrategyAPI;
import edu.neu.csye7374.stockflow.model.Credentials;
import edu.neu.csye7374.stockflow.model.Employee;
import edu.neu.csye7374.stockflow.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author mrunalipawar
 */
@RestController
@RequestMapping(value = "/api/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("/getAll")
    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    @GetMapping("/getEmployee/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeRepo.findById(id).get();
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Employee employee, @PathVariable int id) {
        StrategyAPI strategy = new EmployeeStrategy(employeeRepo, employee);
        strategy.update(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletebyID(@PathVariable int id) {
        StrategyAPI strategy = new EmployeeStrategy(employeeRepo, id);
        strategy.delete();
    }

    @PostMapping("/save")
    public void save(@RequestBody Employee employee) {
        StrategyAPI strategy = new EmployeeStrategy(employeeRepo, employee);
        strategy.add();
    }

    @PostMapping("/login")
    public Employee login(@RequestBody Credentials jsoncredentials) {
        String username = jsoncredentials.getUsername();
        String password = jsoncredentials.getPassword();
        Optional<Employee> employee = employeeRepo.findByUsernameAndPassword(username, password);
        if(employee.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username/Password is incorrect");
        return employee.get();
    }
}
