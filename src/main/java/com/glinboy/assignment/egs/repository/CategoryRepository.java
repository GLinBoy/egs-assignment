package com.glinboy.assignment.egs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glinboy.assignment.egs.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
