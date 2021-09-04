package com.glinboy.assignment.egs.service;

import com.glinboy.assignment.egs.model.User;
import com.glinboy.assignment.egs.service.dto.JwtAuthenticationResponseDTO;
import com.glinboy.assignment.egs.service.dto.SigninRequestDTO;
import com.glinboy.assignment.egs.service.dto.SignupRequestDTO;

public interface AuthServiceApi {

	JwtAuthenticationResponseDTO signin(SigninRequestDTO signinRequestDTO);
	User signup(SignupRequestDTO signupRequestDTO);

}
