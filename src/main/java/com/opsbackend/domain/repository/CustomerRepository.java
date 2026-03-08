package com.opsbackend.domain.repository;

import com.opsbackend.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    void save(Customer customer);
    Optional<Customer> findById(String id);
    List<Customer> findAll();
    void deleteById(String id);
}