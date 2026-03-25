package com.opsbackend.infrastructure.persistence.jdbc;

import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.repository.CustomerRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JdbcCustomerRepositoryTest {

    private CustomerRepository repository;
    private HikariDataSource dataSource;

    @BeforeEach
    void setUp() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/opsbackend");
        config.setUsername("opsuser");
        config.setPassword("opspass");

        dataSource = new HikariDataSource(config);
        repository = new JdbcCustomerRepository(dataSource);

        // limpiar tabla antes de cada test
        try (var conn = dataSource.getConnection();
             var ps = conn.prepareStatement("DELETE FROM customers")) {
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
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

    @Test
    void shouldUpdateCustomerOnSaveWithSameId() {

        // Arrange
        Customer customer = new Customer("c1", "Gian Perez", "gian@mail.com");
        repository.save(customer);

        // Act
        customer.changeName("Gian PC");
        repository.save(customer);
        Optional<Customer> result = repository.findById("c1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Gian PC", result.get().getName());
    }
}