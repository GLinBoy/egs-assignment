package com.glinboy.assignment.egs.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {

	@NotBlank
	@Size(min = 3, max = 40)
	private String firstname;

	@NotBlank
	@Size(min = 3, max = 40)
	private String lastname;

	@NotBlank
	@Size(max = 100)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;
}
