package com.opsbackend.domain.model;

import java.util.Objects;

public class Customer {
    private final String id;
    private String name;
    private String email;
    private boolean active;

    public Customer(String id, String name, String email) {
        this.id = requireText(id, "id");
        this.name = validateName(name);
        this.email = validateEmail(email);
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

    private static String validateName(String value) {
        String name = requireText(value, "name");
        if (name.length() < 3) throw new IllegalArgumentException("name must have at least 3 characters");
        return name;
    }

    private static String validateEmail(String value) {
        String email = requireText(value, "email");
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"))
            throw new IllegalArgumentException("email format is invalid");
        return email;
    }
}
