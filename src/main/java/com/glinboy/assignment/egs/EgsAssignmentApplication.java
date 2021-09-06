package com.glinboy.assignment.egs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.glinboy.assignment.egs.util.ApplicationInformation;
import com.glinboy.assignment.egs.util.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ ApplicationProperties.class, ApplicationInformation.class })
public class EgsAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgsAssignmentApplication.class, args);
	}

}
