package com.opsbackend.infrastructure.persistence.inmemory;

import com.opsbackend.domain.model.Order;
import com.opsbackend.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryOrderRepositoryTest {

    private OrderRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryOrderRepository();
    }

    @Test
    void shouldSaveAndFindOrderById() {

        // Arrange
        Order order = new Order("o1", "c1");

        // Act
        repository.save(order);
        Optional<Order> result = repository.findById("o1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("c1", result.get().getCustomerId());
    }

    @Test
    void shouldFindOrdersByCustomerId() {

        // Arrange
        repository.save(new Order("o1", "c1"));
        repository.save(new Order("o2", "c1"));
        repository.save(new Order("o3", "c2"));

        // Act
        List<Order> orders = repository.findByCustomerId("c1");

        // Assert
        assertEquals(2, orders.size());
    }

    @Test
    void shouldDeleteOrderById() {

        // Arrange
        repository.save(new Order("o1", "c1"));

        // Act
        repository.deleteById("o1");
        Optional<Order> result = repository.findById("o1");

        // Assert
        assertTrue(result.isEmpty());
    }
}