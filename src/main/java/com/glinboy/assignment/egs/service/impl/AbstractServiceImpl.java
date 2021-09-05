package com.glinboy.assignment.egs.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.glinboy.assignment.egs.model.BaseEntity;
import com.glinboy.assignment.egs.service.GenericService;
import com.glinboy.assignment.egs.service.dto.BaseDTO;
import com.glinboy.assignment.egs.service.mapper.GenericMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class AbstractServiceImpl<D extends BaseDTO, E extends BaseEntity,
		S extends JpaRepository<E, Long>, M extends GenericMapper<D, E>>
		implements GenericService<D> {

	protected final S repository;
	
	protected final M mapper;

	@Override
	@Transactional
	public D save(D d) {
		E e = this.mapper.toEntity(d);
		e = repository.save(e);
		return this.mapper.toDto(e);
	}

	@Override
	public D getSingleById(Long id) {
		return this.findById(id)
				.orElseThrow(() ->
					new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Resource not found id = " + id));
	}
	
	@Override
	public Optional<D> findById(Long id) {
		return this.repository.findById(id).map(mapper::toDto);
	}

	@Override
	public Page<D> getAll(Pageable pageable) {
		return repository.findAll(pageable).map(mapper::toDto);
	}

	@Override
	public List<D> findAll() {
		return mapper.toDto(repository.findAll());
	}

	@Override
	@Transactional
	public void deleteSingleById(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteSingleByReference(D d) {
		this.deleteSingleById(d.getId());
	}
}
