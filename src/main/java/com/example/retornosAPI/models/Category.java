package com.example.retornosAPI.models;

import com.example.retornosAPI.exceptions.CategoryNotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public enum Category {
	ELECTRONICS,
	FASHION,
	BOOKS,
	TOYS,
	FURNITURE;

	@JsonCreator
	public static Category checkCategory(String value) {

		for (Category category : Category.values()) {

			if (category.name().equalsIgnoreCase(value)) {
				return category;
			}

		}

		throw new CategoryNotFoundException("Categoria inválida: " + value);
	}

}
