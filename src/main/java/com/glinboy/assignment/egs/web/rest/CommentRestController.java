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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.glinboy.assignment.egs.service.CommentServiceApi;
import com.glinboy.assignment.egs.service.dto.CommentDTO;
import com.glinboy.assignment.egs.util.PaginationUtil;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/products/{product_id}/comments")
public class CommentRestController {

	protected final ResourceBundle messages = PropertyResourceBundle.getBundle("i18n/messages");

	private CommentServiceApi service;

	public CommentRestController(CommentServiceApi service) {
		this.service = service;
	}
	
	@GetMapping
	@PageableAsQueryParam
	public ResponseEntity<List<CommentDTO>> getAll(@Parameter(hidden = true) Pageable pageable, HttpServletRequest request,
			@PathVariable(value = "product_id") Long productId) {
		Page<CommentDTO> page = service.getAll(productId, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, request.getRequestURI());
		headers.setAccessControlExposeHeaders(Arrays.asList(HttpHeaders.LINK, "X-Total-Count"));
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CommentDTO> save(@Valid @RequestBody CommentDTO commentDTO, HttpServletRequest request,
			@PathVariable(value = "product_id") Long productId) {
		commentDTO.setProductId(productId);
		CommentDTO saved = service.save(commentDTO);
		URI location = URI.create(String.format("%s/%s", request.getRequestURI(), saved.getId()));
		return ResponseEntity.created(location).contentType(MediaType.APPLICATION_JSON).body(saved);
	}

	@PutMapping
	public ResponseEntity<CommentDTO> update(@Valid @RequestBody CommentDTO commentDTO,
			@PathVariable(value = "product_id") Long productId) {
		if (commentDTO.getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messages.getString("common.error.empty.id"));
		}
		commentDTO.setProductId(productId);
		CommentDTO updated = service.update(commentDTO);
		return ResponseEntity.ok().body(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id,
			@PathVariable(value = "product_id") Long productId) {
		service.deleteSingleById(productId, id);
		return ResponseEntity.noContent().build();
	}

}
