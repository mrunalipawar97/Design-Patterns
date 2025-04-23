package edu.neu.csye7374.stockflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.csye7374.stockflow.model.Employee;

import java.util.Optional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findById(int id);

	Optional<Employee> findByUsernameAndPassword(String username, String password);
	
	Optional<Employee> findByUsername(String username);
}
