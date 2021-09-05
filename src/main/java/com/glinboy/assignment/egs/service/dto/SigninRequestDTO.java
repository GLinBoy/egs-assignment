package com.glinboy.assignment.egs.service.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequestDTO {

	@NotBlank
	private String email;

	@NotBlank
	private String password;
}
