package com.opsbackend.domain.model;

import java.util.Objects;

public class OrderItem {
    private final String id;
    private final String productId;
    private final int quantity;
    private final double unitPrice;
    private final double subtotal;

    public OrderItem(String id, String productId, int quantity, double unitPrice) {
        this.id = requireText(id, "id");
        this.productId = requireText(productId, "productId");
        this.quantity = validateQuantity(quantity);
        this.unitPrice = validateUnitPrice(unitPrice);
        this.subtotal = this.quantity * this.unitPrice;
    }

    public String getId() {
        return id;
    }
    public String getProductId() {
        return productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public double getSubtotal() {
        return subtotal;
    }

    private static String requireText(String value, String field) {
        String text = Objects.requireNonNull(value, field + " is required").trim();
        if (text.isEmpty()) throw new IllegalArgumentException(field + " cannot be blank");
        return text;
    }

    private static int validateQuantity(int value) {
        if (value <= 0) throw new IllegalArgumentException("quantity must be greater than zero");
        return value;
    }

    private static double validateUnitPrice(double value) {
        if (value <= 0) throw new IllegalArgumentException("unitPrice must be greater than zero");
        return value;
    }
}
