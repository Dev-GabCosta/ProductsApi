package com.example.retornosAPI.exceptions;

import com.example.retornosAPI.models.Category;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
		error.put("Valores permitidos", allowedValues);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();

		exception.getBindingResult()
				.getAllErrors()
				.forEach(error -> {
					String fieldName = ((FieldError) error).getField();
					String message = error.getDefaultMessage();
					errors.put(fieldName, message);
				});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException exception){
		Map<String, String> error = new LinkedHashMap<>();
		error.put("error", "Ops! Esse objeto não pôde ser encontrado. Talvez você esteja procurando por outra coisa");
		error.put("message", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
