package edu.neu.csye7374.stockflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.csye7374.stockflow.model.ProductPO;

import java.util.Optional;

@Repository
@Transactional
public interface ProductPORepository extends JpaRepository<ProductPO,Integer> {

    //Find By ProductPO Id
    Optional<ProductPO> findByproductPOId(int id);
}
