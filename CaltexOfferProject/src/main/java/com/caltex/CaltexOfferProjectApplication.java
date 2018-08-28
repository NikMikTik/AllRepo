package com.caltex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CaltexOfferProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaltexOfferProjectApplication.class, args);
	}
}
