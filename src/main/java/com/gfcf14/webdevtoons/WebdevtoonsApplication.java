package com.gfcf14.webdevtoons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class WebdevtoonsApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USER", dotenv.get("DB_USER"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("FRONT_END_ORIGIN", dotenv.get("FRONT_END_ORIGIN"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		System.setProperty("ADMIN_USER", dotenv.get("ADMIN_USER"));
		SpringApplication.run(WebdevtoonsApplication.class, args);
	}

}
