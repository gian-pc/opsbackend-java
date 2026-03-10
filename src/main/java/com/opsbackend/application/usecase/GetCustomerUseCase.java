package com.opsbackend.application.usecase;

import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.repository.CustomerRepository;

import java.util.Optional;

public class GetCustomerUseCase {

    private final CustomerRepository repository;

    public GetCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Optional<Customer> execute(String id) {
        return repository.findById(id);
    }
}