package com.opsbackend.infrastructure.persistence.inmemory;

import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCustomerRepositoryTest {

    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryCustomerRepository();
    }

    @Test
    void shouldSaveAndFindCustomerById() {

        // Arrange
        Customer customer = new Customer("c1", "Gian Perez", "gian@mail.com");

        // Act
        repository.save(customer);
        Optional<Customer> result = repository.findById("c1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Gian Perez", result.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenCustomerNotFound() {

        // Act
        Optional<Customer> result = repository.findById("no-existe");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindAllCustomers() {

        // Arrange
        repository.save(new Customer("c1", "Gian Perez", "gian@mail.com"));
        repository.save(new Customer("c2", "Juan Lopez", "juan@mail.com"));

        // Act
        List<Customer> customers = repository.findAll();

        // Assert
        assertEquals(2, customers.size());
    }

    @Test
    void shouldDeleteCustomerById() {

        // Arrange
        repository.save(new Customer("c1", "Gian Perez", "gian@mail.com"));

        // Act
        repository.deleteById("c1");
        Optional<Customer> result = repository.findById("c1");

        // Assert
        assertTrue(result.isEmpty());
    }
}