package com.opsbackend.domain.repository;

import com.opsbackend.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void save(Order order);
    Optional<Order> findById(String id);
    List<Order> findAll();
    List<Order> findByCustomerId(String customerId);
    void deleteById(String id);
}