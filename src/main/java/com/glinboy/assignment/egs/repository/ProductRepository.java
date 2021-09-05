package com.glinboy.assignment.egs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glinboy.assignment.egs.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
