package com.glinboy.assignment.egs.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public final class ApplicationProperties {
	private String jwtSecret;
	private String jwtExpirationInMs;
}
