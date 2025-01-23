package com.example.retornosAPI.models;

import com.fasterxml.jackson.annotation.JsonCreator;

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

		throw new IllegalArgumentException("Categoria inválida: " + value);
	}

	}
