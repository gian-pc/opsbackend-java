package com.opsbackend.application.usecase;

import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.model.Order;
import com.opsbackend.domain.repository.CustomerRepository;
import com.opsbackend.domain.repository.OrderRepository;

import java.util.UUID;

public class CreateOrderUseCase {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CreateOrderUseCase(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public Order execute(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("customer not found: " + customerId));

        Order order = new Order(UUID.randomUUID().toString(), customer.getId());
        orderRepository.save(order);
        return order;
    }
}