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
}