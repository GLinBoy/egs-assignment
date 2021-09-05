package com.glinboy.assignment.egs.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.glinboy.assignment.egs.model.Product;
import com.glinboy.assignment.egs.service.dto.ProductDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends GenericMapper<ProductDTO, Product> {

	@Mapping(source = "category.id", target = "categoryId")
	ProductDTO toDto(Product entity);
	
	@Mapping(source = "categoryId", target = "category.id")
	Product toEntity(ProductDTO dto);

}
