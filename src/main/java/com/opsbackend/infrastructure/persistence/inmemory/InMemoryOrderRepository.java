package com.opsbackend.infrastructure.persistence.inmemory;

import com.opsbackend.domain.model.Order;
import com.opsbackend.domain.repository.OrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> store = new HashMap<>();

    @Override
    public void save(Order order) {
        store.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        return store.values().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }
}