package com.example.authproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.authproj.config.JwtProperties;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableConfigurationProperties(JwtProperties.class)
public class AuthprojApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthprojApplication.class, args);
	}

}
