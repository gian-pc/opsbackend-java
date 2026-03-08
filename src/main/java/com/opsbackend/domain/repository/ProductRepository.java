package com.opsbackend.domain.repository;

import com.opsbackend.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);
    Optional<Product> findById(String id);
    List<Product> findAll();
    void deleteById(String id);
}