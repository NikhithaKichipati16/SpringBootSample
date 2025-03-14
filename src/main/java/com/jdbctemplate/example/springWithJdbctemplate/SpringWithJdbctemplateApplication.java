package com.jdbctemplate.example.springWithJdbctemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
public class SpringWithJdbctemplateApplication {

	public static void main(String[] args) {
		log.info("Main method Started");
		SpringApplication.run(SpringWithJdbctemplateApplication.class, args);
		log.info("Main method ended");
	}

}
 