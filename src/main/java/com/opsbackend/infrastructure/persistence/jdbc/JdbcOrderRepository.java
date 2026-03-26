package com.opsbackend.infrastructure.persistence.jdbc;

import com.opsbackend.domain.model.Order;
import com.opsbackend.domain.model.OrderItem;
import com.opsbackend.domain.model.OrderStatus;
import com.opsbackend.domain.repository.OrderRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcOrderRepository implements OrderRepository {

    private final DataSource dataSource;

    public JdbcOrderRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Order order) {
        String sqlOrder = """
                INSERT INTO orders (id, customer_id, status, total)
                VALUES (?, ?, ?, ?)
                ON CONFLICT (id) DO UPDATE
                SET status = EXCLUDED.status,
                    total  = EXCLUDED.total
                """;

        String sqlItem = """
                INSERT INTO order_items (id, order_id, product_id, quantity, unit_price, subtotal)
                VALUES (?, ?, ?, ?, ?, ?)
                ON CONFLICT (id) DO NOTHING
                """;

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = conn.prepareStatement(sqlOrder)) {
                    ps.setString(1, order.getId());
                    ps.setString(2, order.getCustomerId());
                    ps.setString(3, order.getStatus().name());
                    ps.setBigDecimal(4, java.math.BigDecimal.valueOf(order.getTotal()));
                    ps.executeUpdate();
                }

                for (OrderItem item : order.getItems()) {
                    try (PreparedStatement ps = conn.prepareStatement(sqlItem)) {
                        ps.setString(1, item.getId());
                        ps.setString(2, order.getId());
                        ps.setString(3, item.getProductId());
                        ps.setInt(4, item.getQuantity());
                        ps.setBigDecimal(5, java.math.BigDecimal.valueOf(item.getUnitPrice()));
                        ps.setBigDecimal(6, java.math.BigDecimal.valueOf(item.getSubtotal()));
                        ps.executeUpdate();
                    }
                }

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error saving order, transaction rolled back", e);
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving order", e);
        }
    }

    @Override
    public Optional<Order> findById(String id) {
        String sql = """
                SELECT o.id, o.customer_id, o.status, o.total,
                       oi.id as item_id, oi.product_id, oi.quantity,
                       oi.unit_price, oi.subtotal
                FROM orders o
                LEFT JOIN order_items oi ON o.id = oi.order_id
                WHERE o.id = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                List<Order> orders = mapRows(rs);
                return orders.isEmpty() ? Optional.empty() : Optional.of(orders.get(0));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding order", e);
        }
    }

    @Override
    public List<Order> findAll() {
        String sql = """
                SELECT o.id, o.customer_id, o.status, o.total,
                       oi.id as item_id, oi.product_id, oi.quantity,
                       oi.unit_price, oi.subtotal
                FROM orders o
                LEFT JOIN order_items oi ON o.id = oi.order_id
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return mapRows(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all orders", e);
        }
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        String sql = """
                SELECT o.id, o.customer_id, o.status, o.total,
                       oi.id as item_id, oi.product_id, oi.quantity,
                       oi.unit_price, oi.subtotal
                FROM orders o
                LEFT JOIN order_items oi ON o.id = oi.order_id
                WHERE o.customer_id = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                return mapRows(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding orders by customer", e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sqlItems = "DELETE FROM order_items WHERE order_id = ?";
        String sqlOrder = "DELETE FROM orders WHERE id = ?";

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = conn.prepareStatement(sqlItems)) {
                    ps.setString(1, id);
                    ps.executeUpdate();
                }
                try (PreparedStatement ps = conn.prepareStatement(sqlOrder)) {
                    ps.setString(1, id);
                    ps.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Error deleting order, transaction rolled back", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting order", e);
        }
    }

    private List<Order> mapRows(ResultSet rs) throws SQLException {
        Map<String, Order> orderMap = new LinkedHashMap<>();

        while (rs.next()) {
            String orderId = rs.getString("id");

            if (!orderMap.containsKey(orderId)) {
                Order order = new Order(orderId, rs.getString("customer_id"));
                orderMap.put(orderId, order);
            }

            String itemId = rs.getString("item_id");
            if (itemId != null) {
                OrderItem item = new OrderItem(
                        itemId,
                        rs.getString("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                );
                orderMap.get(orderId).addItem(item);
            }
        }

        return new ArrayList<>(orderMap.values());
    }
}