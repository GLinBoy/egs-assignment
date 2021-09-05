package com.glinboy.assignment.egs.service.impl;

import org.springframework.stereotype.Service;

import com.glinboy.assignment.egs.model.Category;
import com.glinboy.assignment.egs.repository.CategoryRepository;
import com.glinboy.assignment.egs.service.CategoryServiceApi;
import com.glinboy.assignment.egs.service.dto.CategoryDTO;
import com.glinboy.assignment.egs.service.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl
	extends AbstractServiceImpl<CategoryDTO, Category, CategoryRepository, CategoryMapper>
	implements CategoryServiceApi {

	public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
		super(repository, mapper);
	}

}
