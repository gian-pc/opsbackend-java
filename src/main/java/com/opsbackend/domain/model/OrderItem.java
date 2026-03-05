package com.opsbackend.domain.model;

public class OrderItem {
    private final String id;
    private final String productId;
    private final int quantity;
    private final double unitPrice;
    private final double subtotal;

    public OrderItem(String id, String productId, int quantity, double unitPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = quantity * unitPrice;
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
}
