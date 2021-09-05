package com.glinboy.assignment.egs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.glinboy.assignment.egs.service.dto.BaseDTO;

public interface GenericService<D extends BaseDTO> {
	D save(D d);

	D getSingleById(Long id);

	Optional<D> findById(Long id);

	Page<D> getAll(Pageable pageable);

	List<D> findAll();

	void deleteSingleById(Long id);

	void deleteSingleByReference(D d);

}
