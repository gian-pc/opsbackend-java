package com.opsbackend.application.usecase;

import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.repository.CustomerRepository;
import com.opsbackend.infrastructure.persistence.inmemory.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateCustomerUseCaseTest {

    private CreateCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        CustomerRepository repository = new InMemoryCustomerRepository();
        useCase = new CreateCustomerUseCase(repository);
    }

    @Test
    void shouldCreateCustomer() {

        // Act
        Customer customer = useCase.execute("c1", "Gian Perez", "gian@mail.com");

        // Assert
        assertNotNull(customer);
        assertEquals("c1", customer.getId());
        assertEquals("Gian Perez", customer.getName());
        assertTrue(customer.isActive());
    }

    @Test
    void shouldFailWhenNameIsTooShort() {

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                useCase.execute("c1", "Gi", "gian@mail.com")
        );
    }
}