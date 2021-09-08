package com.glinboy.assignment.egs.web.rest;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glinboy.assignment.egs.model.Product;
import com.glinboy.assignment.egs.rsql.CustomRsqlVisitor;
import com.glinboy.assignment.egs.service.ProductServiceApi;
import com.glinboy.assignment.egs.service.dto.ProductDTO;
import com.glinboy.assignment.egs.util.PaginationUtil;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "/api/products")
public class ProductRestController extends GenericRestController<ProductDTO, ProductServiceApi> {

	public ProductRestController(ProductServiceApi service) {
		super(service);
	}

	@GetMapping("search")
	@PageableAsQueryParam
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<ProductDTO>> searchProducts(
			@RequestParam(value = "query", required = false, defaultValue = "") String query,
			@Parameter(hidden = true) Pageable pageable,
			HttpServletRequest request) {
		Specification<Product> spec = Specification.where(null); 
		if(!StringUtils.isBlank(query)) {
			Node rootNode = new RSQLParser().parse(query);
			spec = rootNode.accept(new CustomRsqlVisitor<Product>());
		}
		Page<ProductDTO> page = service.searchProducts(spec, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, request.getRequestURI());
		headers.setAccessControlExposeHeaders(Arrays.asList(HttpHeaders.LINK, "X-Total-Count"));
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

}
