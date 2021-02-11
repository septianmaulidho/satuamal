package com.testing.LastProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class LastProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LastProjectApplication.class, args);
	}

}
