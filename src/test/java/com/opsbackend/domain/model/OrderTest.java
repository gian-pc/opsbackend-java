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

    @Test
    void shouldAddItemAndRecalculateTotal() {

        // Arrange
        Order order = new Order("o1", "c1");
        OrderItem item1 = new OrderItem("oi1", "p1", 2, 100.00);
        OrderItem item2 = new OrderItem("oi2", "p2", 1, 50.00);

        // Act
        order.addItem(item1);
        order.addItem(item2);

        // Assert
        assertEquals(2, order.getItems().size());
        assertEquals(250.00, order.getTotal(), 0.01);
    }

    @Test
    void shouldConfirmOrder() {

        // Arrange
        Order order = new Order("o1", "c1");

        // Act
        order.confirm();

        // Assert
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void shouldCancelOrder() {

        // Arrange
        Order order = new Order("o1", "c1");

        // Act
        order.cancel();

        // Assert
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void shouldDeliverOrder() {

        // Arrange
        Order order = new Order("o1", "c1");
        order.confirm();

        // Act
        order.deliver();

        // Assert
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void shouldFailWhenConfirmingCancelledOrder() {

        // Arrange
        Order order = new Order("o1", "c1");
        order.cancel();

        // Act + Assert
        assertThrows(IllegalStateException.class, () ->
                order.confirm()
        );
    }
}