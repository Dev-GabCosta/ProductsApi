package com.example.retornosAPI.dtos;

import com.example.retornosAPI.models.Category;
import jakarta.validation.constraints.NotNull;

public record ProductDto(
		Long id,
		String name,
		String description,
		Double price,
		int amount,
		Category category
) {
}