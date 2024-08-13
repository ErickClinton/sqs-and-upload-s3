package com.erick.clinton.sqss3api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Sqss3apiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sqss3apiApplication.class, args);
	}

}
