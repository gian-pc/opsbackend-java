package com.opsbackend.application.usecase;

import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.repository.CustomerRepository;

public class CreateCustomerUseCase {

    private final CustomerRepository repository;

    public CreateCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer execute(String id, String name, String email) {
        Customer customer = new Customer(id, name, email);
        repository.save(customer);
        return customer;
    }
}