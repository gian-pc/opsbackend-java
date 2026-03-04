package com.opsbackend.domain.model;

public class Customer {
    private final String id;
    private String name;
    private String email;
    private boolean active;

    public Customer(String id, String name, String email) {
        this.id = id;
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

}
