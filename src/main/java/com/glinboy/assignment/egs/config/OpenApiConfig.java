package com.glinboy.assignment.egs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.glinboy.assignment.egs.util.ApplicationInformation;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@SecurityScheme(name = "bearerAuth",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer")
public class OpenApiConfig {

	private final ApplicationInformation info;

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title(info.getName()).description(info.getDescription()).version(info.getVersion())
						.license(new License().name(info.getLicense()).url(info.getLicenseUrl())))
				.externalDocs(new ExternalDocumentation().description("Source code").url(info.getGithubUrl()));
	}
}
