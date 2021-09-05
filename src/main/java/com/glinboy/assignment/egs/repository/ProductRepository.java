package com.glinboy.assignment.egs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.glinboy.assignment.egs.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Modifying
	@Query(value =  "UPDATE Product p SET p.rate = ( p.rate - ?2 + ?3 ) WHERE p.id = ?1",
		nativeQuery = true)
	void updateUserRateByProductId(Long productId, Short oldRate, Short newRate);

}
