package com.kroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class KrollServerApplication {

	public static void main(String[] args) {
		System.out.println("PaymentIntegration");
		SpringApplication.run(KrollServerApplication.class, args);
	}
}
