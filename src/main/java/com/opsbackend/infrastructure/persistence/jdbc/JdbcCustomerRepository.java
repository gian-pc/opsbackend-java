package com.opsbackend.infrastructure.persistence.jdbc;

import com.opsbackend.domain.model.Customer;
import com.opsbackend.domain.repository.CustomerRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCustomerRepository implements CustomerRepository {

    private final DataSource dataSource;

    public JdbcCustomerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Customer customer) {
        String sql = """
                INSERT INTO customers (id, name, email, active)
                VALUES (?, ?, ?, ?)
                ON CONFLICT (id) DO UPDATE
                SET name = EXCLUDED.name,
                    email = EXCLUDED.email,
                    active = EXCLUDED.active
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getEmail());
            ps.setBoolean(4, customer.isActive());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving customer", e);
        }
    }

    @Override
    public Optional<Customer> findById(String id) {
        String sql = "SELECT id, name, email, active FROM customers WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding customer", e);
        }
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT id, name, email, active FROM customers";
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                customers.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all customers", e);
        }

        return customers;
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting customer", e);
        }
    }

    private Customer mapRow(ResultSet rs) throws SQLException {
        Customer customer = new Customer(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("email")
        );
        if (!rs.getBoolean("active")) {
            customer.deactivate();
        }
        return customer;
    }
}