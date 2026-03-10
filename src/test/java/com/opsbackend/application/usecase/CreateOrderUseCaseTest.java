package com.opsbackend.application.usecase;

import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.model.Order;
import com.opsbackend.domain.repository.CustomerRepository;
import com.opsbackend.domain.repository.OrderRepository;
import com.opsbackend.infrastructure.persistence.inmemory.InMemoryCustomerRepository;
import com.opsbackend.infrastructure.persistence.inmemory.InMemoryOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrderUseCaseTest {

    private CreateOrderUseCase useCase;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = new InMemoryCustomerRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();
        useCase = new CreateOrderUseCase(customerRepository, orderRepository);
    }

    @Test
    void shouldCreateOrderForExistingCustomer() {

        // Arrange
        customerRepository.save(new Customer("c1", "Gian Perez", "gian@mail.com"));

        // Act
        Order order = useCase.execute("c1");

        // Assert
        assertNotNull(order);
        assertEquals("c1", order.getCustomerId());
        assertNotNull(order.getId());
    }

    @Test
    void shouldFailWhenCustomerDoesNotExist() {

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                useCase.execute("no-existe")
        );
    }
}