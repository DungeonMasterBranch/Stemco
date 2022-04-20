package com.stemco.java_stemco_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class JavaStemcoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaStemcoBackendApplication.class, args);
	}

}
