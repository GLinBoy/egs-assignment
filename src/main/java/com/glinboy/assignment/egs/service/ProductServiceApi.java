package com.glinboy.assignment.egs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.glinboy.assignment.egs.model.Product;
import com.glinboy.assignment.egs.service.dto.ProductDTO;

public interface ProductServiceApi extends GenericService<ProductDTO> {

	void updateUserRate(Long productId, Short oldRate, Short newRate);

	Page<ProductDTO> searchProducts(Specification<Product> specs, Pageable pageable);
}
