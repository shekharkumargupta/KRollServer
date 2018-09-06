package com.kroll;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KrollServerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(KrollServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("KrollServerApplication Initiated!");

	}
}
