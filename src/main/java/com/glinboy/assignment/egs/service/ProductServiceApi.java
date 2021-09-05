package com.glinboy.assignment.egs.service;

import com.glinboy.assignment.egs.service.dto.ProductDTO;

public interface ProductServiceApi extends GenericService<ProductDTO> {

	void updateUserRate(Long productId, Short oldRate, Short newRate);
}
