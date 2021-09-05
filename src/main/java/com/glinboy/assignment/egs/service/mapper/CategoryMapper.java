package com.glinboy.assignment.egs.service.mapper;

import org.mapstruct.Mapper;

import com.glinboy.assignment.egs.model.Category;
import com.glinboy.assignment.egs.service.dto.CategoryDTO;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends GenericMapper<CategoryDTO, Category> {

}
