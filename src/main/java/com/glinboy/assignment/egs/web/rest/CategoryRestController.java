package com.glinboy.assignment.egs.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glinboy.assignment.egs.service.CategoryServiceApi;
import com.glinboy.assignment.egs.service.dto.CategoryDTO;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryRestController extends GenericRestController<CategoryDTO, CategoryServiceApi> {

	public CategoryRestController(CategoryServiceApi service) {
		super(service);
	}

}
