package com.opsbackend.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private final String id;
    private final String customerId;
    private OrderStatus status;
    private double total;
    private final LocalDateTime createdAt;
    private final List<OrderItem> items;

    public Order(String id, String customerId) {
        this.id = requireText(id, "id");
        this.customerId = requireText(customerId, "customerId");
        this.status = OrderStatus.PENDING;
        this.total = 0.0;
        this.createdAt = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    public String getId()            { return id; }
    public String getCustomerId()    { return customerId; }
    public OrderStatus getStatus()   { return status; }
    public double getTotal()         { return total; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<OrderItem> getItems() { return items; }

    private static String requireText(String value, String field) {
        String text = Objects.requireNonNull(value, field + " is required").trim();
        if (text.isEmpty()) throw new IllegalArgumentException(field + " cannot be blank");
        return text;
    }

    public void addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        this.items.add(item);
        this.total = this.items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }
}