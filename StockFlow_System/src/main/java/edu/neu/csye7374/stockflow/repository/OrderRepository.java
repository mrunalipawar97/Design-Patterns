package edu.neu.csye7374.stockflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.csye7374.stockflow.model.PurchaseOrder;

import java.util.Optional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {

	Optional<PurchaseOrder> findById(int id);

}
