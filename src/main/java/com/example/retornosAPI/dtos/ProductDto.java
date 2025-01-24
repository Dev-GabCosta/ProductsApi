package com.example.retornosAPI.dtos;

import com.example.retornosAPI.models.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProductDto(
		Long id,
		@Size(min = 3, max = 100)
		@NotNull(message = "Nome é obrigatório")
		String name,
		@Size(max = 500)
		String description,
		@NotNull(message = "O preço é obrigatório")
		@Positive(message = "O preço deve ser maior que 0")
		Double price,
		@NotNull(message = "É obrigatório informar a quantidade de produtos em estoque")
		@PositiveOrZero(message = "Esse valor não pode ser menor que 0")
		int amount,
		@NotNull(message = "A categoria do produto é obrigatória")
		Category category
) {
}