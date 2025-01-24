package com.example.retornosAPI.exceptions;

import com.example.retornosAPI.models.Category;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(CategoryNotFoundException exception) {
		Map<String, String> error = new LinkedHashMap<>();
		String allowedValues = Arrays.stream(Category.values())
				                     .map(Enum::name)
				                     .collect(Collectors.joining(", "));
		error.put("error", exception.getMessage());
		error.put("Valores permitidos"  , allowedValues);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
