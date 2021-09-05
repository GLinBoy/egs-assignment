package com.glinboy.assignment.egs.service.dto;

public class JwtAuthenticationResponseDTO {

	private String tokenType = "Bearer";
	private String accessToken;

	public JwtAuthenticationResponseDTO(String token) {
		this.accessToken = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

}
