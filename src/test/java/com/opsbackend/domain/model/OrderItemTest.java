package com.opsbackend.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void shouldCreateOrderItemWithValidData() {

        // Arrange
        String id = "oi1";
        String productId = "p1";
        int quantity = 3;
        double unitPrice = 999.99;

        // Act
        OrderItem item = new OrderItem(id, productId, quantity, unitPrice);

        // Assert
        assertEquals("oi1", item.getId());
        assertEquals("p1", item.getProductId());
        assertEquals(3, item.getQuantity());
        assertEquals(999.99, item.getUnitPrice(), 0.01);
        assertEquals(2999.97, item.getSubtotal(), 0.01);
    }

    @Test
    void shouldFailWhenIdIsBlank() {

        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem("   ", "p1", 3, 999.99)
        );
    }

    @Test
    void shouldFailWhenProductIdIsBlank() {

        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem("oi1", "   ", 3, 999.99)
        );
    }

    @Test
    void shouldFailWhenQuantityIsZeroOrNegative() {

        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem("oi1", "p1", 0, 999.99)
        );
    }

    @Test
    void shouldFailWhenUnitPriceIsZeroOrNegative() {

        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem("oi1", "p1", 3, 0.0)
        );
    }
}