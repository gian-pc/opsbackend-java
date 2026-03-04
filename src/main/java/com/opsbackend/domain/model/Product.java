package com.opsbackend.domain.model;

public class Product {

    private final String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean active;

    // Constructor
    public Product(String id, String name, String description, double price, int stock){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
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
}
