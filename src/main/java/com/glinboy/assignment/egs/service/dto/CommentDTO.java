package com.glinboy.assignment.egs.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CommentDTO extends BaseDTO {

	@NotNull
	@Min(1) @Max(5)
	private Short rate;

	@NotBlank
	@Size(max = 256)
	private String content;

	private Long userId;

	private Long productId;
}
