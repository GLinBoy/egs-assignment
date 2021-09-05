package com.glinboy.assignment.egs.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CategoryDTO extends BaseDTO {
	
	@NotBlank
	@Size(min = 3, max = 40)
	private String title;

}
