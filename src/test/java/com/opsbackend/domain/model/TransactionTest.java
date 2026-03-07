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

    @Test
    void shouldFailWhenIdIsBlank() {

        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("   ", "o1", 500.00, TransactionStatus.PENDING)
        );
    }

    @Test
    void shouldFailWhenOrderIdIsBlank() {

        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("t1", "   ", 500.00, TransactionStatus.PENDING)
        );
    }

    @Test
    void shouldFailWhenAmountIsZeroOrNegative() {

        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("t1", "o1", 0.0, TransactionStatus.PENDING)
        );
    }

    @Test
    void shouldFailWhenStatusIsNull() {

        // Arrange + Act + Assert
        assertThrows(NullPointerException.class, () ->
                new Transaction("t1", "o1", 500.00, null)
        );
    }
}