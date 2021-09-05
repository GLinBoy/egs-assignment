package com.glinboy.assignment.egs.service.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductDTO extends BaseDTO {

	@NotBlank
	@Size(max = 40)
	private String title;

	@Size(max = 256)
	private String descriptiuon;

	@DecimalMin(value = "0.1", inclusive = false)
	@Digits(integer = 6, fraction = 2)
	private BigDecimal price;

	@Size(max = 128)
	private String image;

	@NotNull
	private Long categoryId;
}
