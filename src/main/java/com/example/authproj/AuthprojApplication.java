package com.example.authproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class AuthprojApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthprojApplication.class, args);
	}

}
