package com.opsbackend.infrastructure.persistence.inmemory;

import com.opsbackend.domain.model.Product;
import com.opsbackend.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryProductRepositoryTest {

    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryProductRepository();
    }

    @Test
    void shouldSaveAndFindProductById() {

        // Arrange
        Product product = new Product("p1", "Laptop", "Laptop 16GB", 999.99, 10);

        // Act
        repository.save(product);
        Optional<Product> result = repository.findById("p1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenProductNotFound() {

        // Act
        Optional<Product> result = repository.findById("no-existe");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindAllProducts() {

        // Arrange
        repository.save(new Product("p1", "Laptop", "Laptop 16GB", 999.99, 10));
        repository.save(new Product("p2", "Mouse", "Mouse inalambrico", 29.99, 50));

        // Act
        List<Product> products = repository.findAll();

        // Assert
        assertEquals(2, products.size());
    }

    @Test
    void shouldDeleteProductById() {

        // Arrange
        repository.save(new Product("p1", "Laptop", "Laptop 16GB", 999.99, 10));

        // Act
        repository.deleteById("p1");
        Optional<Product> result = repository.findById("p1");

        // Assert
        assertTrue(result.isEmpty());
    }
}