package com.opsbackend.domain.model;

import java.util.Objects;

public class Customer {
    private final String id;
    private String name;
    private String email;
    private boolean active;

    public Customer(String id, String name, String email) {
        this.id = requireText(id, "id");
        this.name = name;
        this.email = email;
        this.active = true;
    }

    public String getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public boolean isActive(){
        return active;
    }

    private static String requireText(String value, String field) {
        String text = Objects.requireNonNull(value, field + " is required").trim();
        if (text.isEmpty()) throw new IllegalArgumentException(field + " cannot be blank");
        return text;
    }
}
