package com.glinboy.assignment.egs.service.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserDTO extends BaseDTO {

	@NotBlank
	@Size(max = 40)
	private String firstname;

	@NotBlank
	@Size(max = 40)
	private String lastname;

	@NotBlank
	@Size(max = 100)
	@Email
	private String email;

	@NotBlank
	private Boolean blocked;

	private Set<RoleDTO> roles = new HashSet<>();
}
