package com.opsbackend.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest {
    @Test
    void shouldCreateProductWithValidData(){
        // Arrange
        String id = "p1";
        String name = "Laptop";
        String description = "Laptop 16GB RAM";
        double price = 999.99;
        int stock = 10;
        // Act
        Product p = new Product(id, name, description, price, stock);

        // Assert
        assertEquals("p1", p.getId());
        assertEquals("Laptop", p.getName());
        assertEquals("Laptop 16GB RAM", p.getDescription());
        assertEquals(999.99, p.getPrice(), 0.01);
        assertEquals(10, p.getStock());
        assertTrue(p.isActive());


    }
}
