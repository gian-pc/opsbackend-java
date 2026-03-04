package com.opsbackend.domain.model;

import java.util.Objects;

public class Product {

    private final String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean active;

    // Constructor
    public Product(String id, String name, String description, double price, int stock){
        this.id = requireText(id, "id");
        this.name = requireText(name, "name");
        this.description = description;
        this.price = validatePrice(price);
        this.stock = validateStock(stock);
        this.active = true;
    }

    // Métodos
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public boolean isActive() {
        return active;
    }


    public void reduceStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be greater than zero");
        if (quantity > this.stock) throw new IllegalArgumentException("insufficient stock");
        this.stock -= quantity;
    }

    public void addStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be greater than zero");
        this.stock += quantity;
    }

    public void deactivate() {
        this.active = false;
    }

    // Validar id
    private static String requireText(String value, String field){
        String text = Objects.requireNonNull(value, field + " is required").trim();
        if(text.isEmpty()) throw new IllegalArgumentException(field + " cannot be blank");
        return text;
    }

    private static double validatePrice(double value) {
        if (value <= 0) throw new IllegalArgumentException("price must be greater than zero");
        return value;
    }

    private static int validateStock(int value) {
        if (value < 0) throw new IllegalArgumentException("stock cannot be negative");
        return value;
    }
}
