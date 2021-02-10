package com.uirmfk.uirmfk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UirmfkApplication {

	public static void main(String[] args) {
		SpringApplication.run(UirmfkApplication.class, args);
	}

}
