package com.glinboy.assignment.egs.service.impl;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.glinboy.assignment.egs.model.Category;
import com.glinboy.assignment.egs.model.Product;
import com.glinboy.assignment.egs.repository.ProductRepository;
import com.glinboy.assignment.egs.service.ProductServiceApi;
import com.glinboy.assignment.egs.service.dto.ProductDTO;
import com.glinboy.assignment.egs.service.mapper.ProductMapper;

@Service
public class ProductServiceImpl
	extends AbstractServiceImpl<ProductDTO, Product, ProductRepository, ProductMapper>
	implements ProductServiceApi {

	private final EntityManager em;
	
	public ProductServiceImpl(ProductRepository repository,
			ProductMapper mapper,
			EntityManager em) {
		super(repository, mapper);
		this.em = em;
	}

	@Override
	public ProductDTO save(ProductDTO productDTO) {
		Product product = this.mapper.toEntity(productDTO);
		product.setCategory(em.getReference(Category.class, productDTO.getCategoryId()));
		product = this.repository.save(product);
		return this.mapper.toDto(product);
	}
}
