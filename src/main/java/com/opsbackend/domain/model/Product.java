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
    public Product(String id, String name, String description, double price, int stock) {
        this.id = requireText(id, "id");
        this.name = requireText(name, "name");
        this.description = description;
        this.price = validatePrice(price);
        this.stock = validateStock(stock);
        this.active = true;
    }

    // Getters
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

    // Setters
    public void changeName(String newName) {
        this.name = requireText(newName, "name");
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void changePrice(double newPrice) {
        this.price = validatePrice(newPrice);
    }

    public void reduceStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        if (quantity > this.stock) throw new IllegalArgumentException("Stock insuficiente");
        this.stock -= quantity;
    }

    public void addStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        this.stock += quantity;
    }

    public void deactivate() {
        this.active = false;
    }

    // Métodos de validaciones
    private static String requireText(String value, String field) {
        String text = Objects.requireNonNull(value, field + " es requerido").trim();
        if (text.isEmpty()) throw new IllegalArgumentException(field + " no puede estar vacío");
        return text;
    }

    private static double validatePrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("el precio debe ser mayor que cero");
        return price;
    }

    private static int validateStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("el stock no puede ser negativo");
        return stock;
    }
}
