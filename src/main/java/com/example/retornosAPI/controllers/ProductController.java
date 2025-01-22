package com.example.retornosAPI.controllers;

import com.example.retornosAPI.dtos.ProductDto;
import com.example.retornosAPI.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
		ProductDto createdProductDto = service.createProduct(productDto);
		URI location = URI.create(String.format("/products/%d", createdProductDto.id()));
		return ResponseEntity.created(location).body(createdProductDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getProductById(id));
	}

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		return ResponseEntity.ok(service.getAllProducts());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		service.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}