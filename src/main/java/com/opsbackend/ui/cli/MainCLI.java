package com.opsbackend.ui.cli;

import com.opsbackend.application.usecase.CreateCustomerUseCase;
import com.opsbackend.application.usecase.CreateOrderUseCase;
import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.model.Order;
import com.opsbackend.domain.repository.CustomerRepository;
import com.opsbackend.domain.repository.OrderRepository;
import com.opsbackend.infrastructure.persistence.inmemory.InMemoryCustomerRepository;
import com.opsbackend.infrastructure.persistence.inmemory.InMemoryOrderRepository;

public class MainCLI {

    public static void main(String[] args) {

        // Infraestructura
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();

        // Casos de uso
        CreateCustomerUseCase createCustomer = new CreateCustomerUseCase(customerRepository);
        CreateOrderUseCase createOrder = new CreateOrderUseCase(customerRepository, orderRepository);

        // Flujo
        Customer customer = createCustomer.execute("c1", "Gian Perez", "gian@mail.com");
        System.out.println("Cliente creado: " + customer.getName());

        Order order = createOrder.execute("c1");
        System.out.println("Orden creada: " + order.getId());
        System.out.println("Cliente de la orden: " + order.getCustomerId());
    }
}