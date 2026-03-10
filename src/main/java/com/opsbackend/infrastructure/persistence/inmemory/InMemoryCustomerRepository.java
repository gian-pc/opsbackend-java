package com.opsbackend.infrastructure.persistence.inmemory;

import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<String, Customer> store = new HashMap<>();

    @Override
    public void save(Customer customer) {
        store.put(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }
}