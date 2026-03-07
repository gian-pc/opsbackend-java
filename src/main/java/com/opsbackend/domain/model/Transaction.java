package com.opsbackend.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private final String id;
    private final String orderId;
    private final double amount;
    private final TransactionStatus status;
    private final LocalDateTime createdAt;

    public Transaction(String id, String orderId, double amount, TransactionStatus status) {
        this.id = requireText(id, "id");
        this.orderId = requireText(orderId, "orderId");
        this.amount = validateAmount(amount);
        this.status = requireStatus(status);
        this.createdAt = LocalDateTime.now();
    }

    public String getId()                { return id; }
    public String getOrderId()           { return orderId; }
    public double getAmount()            { return amount; }
    public TransactionStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt()  { return createdAt; }

    private static String requireText(String value, String field) {
        String text = Objects.requireNonNull(value, field + " is required").trim();
        if (text.isEmpty()) throw new IllegalArgumentException(field + " cannot be blank");
        return text;
    }

    private static double validateAmount(double value) {
        if (value <= 0) throw new IllegalArgumentException("amount must be greater than zero");
        return value;
    }

    private static TransactionStatus requireStatus(TransactionStatus value) {
        return Objects.requireNonNull(value, "status is required");
    }
}