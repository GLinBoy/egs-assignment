package com.glinboy.assignment.egs.web.rest;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.glinboy.assignment.egs.service.GenericService;
import com.glinboy.assignment.egs.service.dto.BaseDTO;
import com.glinboy.assignment.egs.util.PaginationUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class GenericRestController<T extends BaseDTO, S extends GenericService<T>> {

	protected final ResourceBundle messages = PropertyResourceBundle.getBundle("i18n/messages");

	protected final S service;

	@GetMapping
	@PageableAsQueryParam
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<T>> getAll(@Parameter(hidden = true) Pageable pageable, HttpServletRequest request) {
		Page<T> page = service.getAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, request.getRequestURI());
		headers.setAccessControlExposeHeaders(Arrays.asList(HttpHeaders.LINK, "X-Total-Count"));
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<T> getById(@PathVariable Long id) {
		T entity = service.getSingleById(id);
		return ResponseEntity.ok().body(entity);
	}

	@PostMapping
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<T> save(@Valid @RequestBody T entity, HttpServletRequest request) {
		T savedEntity = service.save(entity);
		URI location = URI.create(String.format("%s/%s", request.getRequestURI(), savedEntity.getId()));
		return ResponseEntity.created(location).contentType(MediaType.APPLICATION_JSON).body(savedEntity);
	}

	@PutMapping
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<T> update(@Valid @RequestBody T entity) {
		if (entity.getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messages.getString("common.error.empty.id"));
		}
		T updatedEntity = service.save(entity);
		return ResponseEntity.ok().body(updatedEntity);
	}

	@DeleteMapping("/{id}")
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteSingleById(id);
		return ResponseEntity.noContent().build();
	}

}
