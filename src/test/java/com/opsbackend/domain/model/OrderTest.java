package com.opsbackend.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldCreateOrderWithValidData() {

        // Arrange
        String id = "o1";
        String customerId = "c1";

        // Act
        Order order = new Order(id, customerId);

        // Assert
        assertEquals("o1", order.getId());
        assertEquals("c1", order.getCustomerId());
        assertEquals(OrderStatus.PENDING, order.getStatus());
        assertEquals(0.0, order.getTotal(), 0.01);
        assertNotNull(order.getCreatedAt());
        assertTrue(order.getItems().isEmpty());
    }

    @Test
    void shouldFailWhenIdIsBlank() {

        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Order("   ", "c1")
        );
    }

    @Test
    void shouldFailWhenCustomerIdIsBlank() {

        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Order("o1", "   ")
        );
    }
}