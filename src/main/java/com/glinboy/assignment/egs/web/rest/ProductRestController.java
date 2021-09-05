package com.glinboy.assignment.egs.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glinboy.assignment.egs.service.ProductServiceApi;
import com.glinboy.assignment.egs.service.dto.ProductDTO;

@RestController
@RequestMapping(path = "/api/products")
public class ProductRestController extends GenericRestController<ProductDTO, ProductServiceApi> {

	public ProductRestController(ProductServiceApi service) {
		super(service);
	}

}
