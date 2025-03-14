package com.jdbctemplate.example.springWithJdbctemplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.ExceptionHandling;

public class GlobalExceptionHandler {
	@ExceptionHandler(ExceptionHandling.class)
	public ResponseEntity<String> handleResponseNotFound(ExceptionHandling eh) {
		

		return new ResponseEntity<>(eh.getMessage(), HttpStatus.NOT_FOUND);
	}

}
