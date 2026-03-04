package com.opsbackend.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    void shouldCreateProductWithValidData() {
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

    @Test
    void shouldFailWhenIdIsBlank() {
        // Arrange
        String id = "";
        String name = "Laptop";
        String description = "Laptop 16GB RAM";
        double price = 999.99;
        int stock = 10;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Product(id, name, description, price, stock)
        );
    }

    @Test
    void shouldFailWhenNameIsBlank(){
        // Arrange
        String id = "p1";
        String name = " ";
        String description = "Laptop 16GB RAM";
        double price = 999.99;
        int stock = 10;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, ()->
                new Product(id, name, description, price, stock)
        );
    }

    @Test
    void shouldFailWhenPriceIsZeroOrNegative(){
        // Arrange
        String id = "p1";
        String name = "Laptop";
        String description = "Laptop 16GB RAM";
        double price = 0.0;
        int stock = 10;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, ()->
                new Product(id, name, description, price, stock)
        );
    }

    @Test
    void shouldFailWhenStockIsNegative() {

        // Arrange
        String id = "p1";
        String name = "Laptop";
        String description = "Laptop 16GB RAM";
        double price = 999.99;
        int stock = -1;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Product(id, name, description, price, stock)
        );
    }

    @Test
    void shouldReduceStock() {

        // Arrange
        Product p = new Product("p1", "Laptop", "Laptop 16GB RAM", 999.99, 10);

        // Act
        p.reduceStock(3);

        // Assert
        assertEquals(7, p.getStock());
    }

    @Test
    void shouldFailWhenReducingMoreThanAvailableStock() {

        // Arrange
        Product p = new Product("p1", "Laptop", "Laptop 16GB RAM", 999.99, 5);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                p.reduceStock(10)
        );
    }

    @Test
    void shouldAddStock() {

        // Arrange
        Product p = new Product("p1", "Laptop", "Laptop 16GB RAM", 999.99, 10);

        // Act
        p.addStock(5);

        // Assert
        assertEquals(15, p.getStock());
    }

    @Test
    void shouldFailWhenAddingZeroOrNegativeStock() {

        // Arrange
        Product p = new Product("p1", "Laptop", "Laptop 16GB RAM", 999.99, 10);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                p.addStock(0)
        );
    }

    @Test
    void shouldDeactivateProduct() {

        // Arrange
        Product p = new Product("p1", "Laptop", "Laptop 16GB RAM", 999.99, 10);

        // Act
        p.deactivate();

        // Assert
        assertFalse(p.isActive());
    }
}
