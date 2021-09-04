package com.glinboy.assignment.egs.web.rest;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.glinboy.assignment.egs.model.User;
import com.glinboy.assignment.egs.service.AuthServiceApi;
import com.glinboy.assignment.egs.service.dto.JwtAuthenticationResponseDTO;
import com.glinboy.assignment.egs.service.dto.SigninRequestDTO;
import com.glinboy.assignment.egs.service.dto.SignupRequestDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	
	private final AuthServiceApi authService;

	public AuthRestController(AuthServiceApi authService) {
		this.authService = authService;
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponseDTO> authenticateUser(@Valid @RequestBody SigninRequestDTO signinRequestDTO) {
		return ResponseEntity.ok(authService.signin(signinRequestDTO));
	}

	@PostMapping("/signup")
	public ResponseEntity<Void> registerUser(@Valid @RequestBody SignupRequestDTO signupRequestDTO) {
		User result = authService.signup(signupRequestDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{id}")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
