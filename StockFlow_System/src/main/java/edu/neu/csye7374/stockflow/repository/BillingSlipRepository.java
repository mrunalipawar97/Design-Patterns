package edu.neu.csye7374.stockflow.repository;


import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.csye7374.stockflow.model.BillingSlip;

import org.springframework.data.jpa.repository.JpaRepository;

/****
 * Repository interface for BillingSlip entity.
 * Provides CRUD operations and custom query methods for BillingSlip.
 */

@Repository
@Transactional
public interface BillingSlipRepository extends JpaRepository<BillingSlip, Integer> {

	/**
     * Retrieves a BillingSlip by its unique identifier.
     *
     * @param id the ID of the BillingSlip
     * @return an Optional containing the BillingSlip if found, otherwise empty
     */
	Optional<BillingSlip> findById(int id);

	// void update(BillingSlip billingSlip);

	// void delete(BillingSlip billingSlip);
}
