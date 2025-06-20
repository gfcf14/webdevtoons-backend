package com.gfcf14.webdevtoons;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class WebdevtoonsApplication {

	public static void main(String[] args) {
		if (Files.exists(Paths.get(".env"))) {
			Dotenv dotenv = Dotenv.load();
			System.setProperty("DB_URL", dotenv.get("DB_URL"));
			System.setProperty("FRONT_END_ORIGIN", dotenv.get("FRONT_END_ORIGIN"));
			System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		}
		SpringApplication.run(WebdevtoonsApplication.class, args);
	}

}
