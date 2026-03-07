package com.opsbackend.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void shouldCreateTransactionWithValidData() {

        // Arrange
        String id = "t1";
        String orderId = "o1";
        double amount = 500.00;
        TransactionStatus status = TransactionStatus.PENDING;

        // Act
        Transaction t = new Transaction(id, orderId, amount, status);

        // Assert
        assertEquals("t1", t.getId());
        assertEquals("o1", t.getOrderId());
        assertEquals(500.00, t.getAmount(), 0.01);
        assertEquals(TransactionStatus.PENDING, t.getStatus());
        assertNotNull(t.getCreatedAt());
    }
}