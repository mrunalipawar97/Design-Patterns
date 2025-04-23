package edu.neu.csye7374.stockflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.csye7374.stockflow.model.Product;

import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Find By Product Id
    Optional<Product> findByProductId(int id);

    //Find By Product Name
    Optional<Product> findByProductName(String productName);
}
