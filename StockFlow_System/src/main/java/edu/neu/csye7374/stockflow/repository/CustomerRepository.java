package edu.neu.csye7374.stockflow.repository;

import edu.neu.csye7374.stockflow.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> searchById(int customerId);

    Optional<Customer> searchByCompanyName(String companyName);

}
