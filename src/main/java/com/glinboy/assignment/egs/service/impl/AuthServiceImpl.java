package com.glinboy.assignment.egs.service.impl;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.glinboy.assignment.egs.model.Role;
import com.glinboy.assignment.egs.model.RoleName;
import com.glinboy.assignment.egs.model.User;
import com.glinboy.assignment.egs.repository.RoleRepository;
import com.glinboy.assignment.egs.repository.UserRepository;
import com.glinboy.assignment.egs.security.JwtTokenProvider;
import com.glinboy.assignment.egs.service.AuthServiceApi;
import com.glinboy.assignment.egs.service.dto.JwtAuthenticationResponseDTO;
import com.glinboy.assignment.egs.service.dto.SigninRequestDTO;
import com.glinboy.assignment.egs.service.dto.SignupRequestDTO;

@Service
public class AuthServiceImpl implements AuthServiceApi {

	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtTokenProvider tokenProvider;

	public AuthServiceImpl(AuthenticationManager authenticationManager,
			UserRepository userRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder,
			JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}

	@Override
	public JwtAuthenticationResponseDTO signin(SigninRequestDTO signinRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequestDTO.getEmail(), signinRequestDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new JwtAuthenticationResponseDTO(tokenProvider.generateToken(authentication));
	}

	@Override
	public User signup(SignupRequestDTO signupRequestDTO) {
		if (userRepository.existsByEmail(signupRequestDTO.getEmail()).booleanValue()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Address already in use!");
		}
		User user = new User(null, signupRequestDTO.getFirstname(), signupRequestDTO.getLastname(), signupRequestDTO.getEmail(),
				signupRequestDTO.getPassword(), Collections.emptySet());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() ->  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Role not set."));
		user.setRoles(Collections.singleton(userRole));
		return userRepository.save(user);
	}

}
