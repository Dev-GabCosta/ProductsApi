package com.example.retornosAPI.services;

import com.example.retornosAPI.dtos.ProductDto;
import com.example.retornosAPI.exceptions.ResourceNotFoundException;
import com.example.retornosAPI.models.ProductEntity;
import com.example.retornosAPI.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public ProductDto createProduct(ProductDto productDto) {
		ProductEntity entity = new ProductEntity(null, productDto.name(), productDto.description(), productDto.price(), productDto.amount(), productDto.category());
		ProductEntity savedEntity = repository.save(entity);
		return new ProductDto(savedEntity.getId(), savedEntity.getName(), savedEntity.getDescription(), savedEntity.getPrice(), savedEntity.getAmount(), savedEntity.getCategory());
	}

	public ProductDto getProductById(Long id) {
		ProductEntity entity = repository.findById(id)
				                       .orElseThrow(() -> new ResourceNotFoundException(showMessageNotFound(id)));
		return new ProductDto(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getAmount(), entity.getCategory());
	}

	private String showMessageNotFound(Long id) {
		return "Nenhum produto com o id " + id + " foi encontrado";
	}

	public List<ProductDto> getAllProducts() {
		return repository.findAll().stream()
				       .map(entity -> new ProductDto(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getAmount(), entity.getCategory()))
				       .collect(Collectors.toList());
	}

	public void deleteProduct(Long id) {
		repository.findById(id)
				                       .orElseThrow(() -> new ResourceNotFoundException(showMessageNotFound(id)));
		repository.deleteById(id);
	}

	// Atualizar um produto existente
	public ProductDto updateProduct(Long id, ProductDto updatedProductDto) {
		// Verificar se o produto existe
		ProductEntity existingEntity = repository.findById(id)
				                               .orElseThrow(() -> new ResourceNotFoundException(showMessageNotFound(id)));

		// Atualizar os dados do produto
		existingEntity.setName(updatedProductDto.name());
		existingEntity.setPrice(updatedProductDto.price());

		// Salvar as alterações no banco de dados
		ProductEntity savedEntity = repository.save(existingEntity);

		// Retornar o produto atualizado
		return new ProductDto(savedEntity.getId(), savedEntity.getName(), savedEntity.getDescription(), savedEntity.getPrice(), savedEntity.getAmount(), savedEntity.getCategory());
	}

	// Buscar produtos pelo nome
	public List<ProductDto> getProductsByName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
		}

		List<ProductEntity> entities = repository.findByNameContainingIgnoreCase(name);
		if (entities.isEmpty()) {
			System.out.println("Nenhum produto encontrado com o nome: " + name);
		} else {
			System.out.println("Produtos encontrados com o nome '" + name + "': " + entities.size());
		}
		return entities.stream()
				       .map(
						       entity -> new ProductDto(
								       entity.getId(),
								       entity.getName(),
								       entity.getDescription(),
								       entity.getPrice(),
								       entity.getAmount(),
								       entity.getCategory()
						       )
				       )
				       .collect(Collectors.toList());
	}
}