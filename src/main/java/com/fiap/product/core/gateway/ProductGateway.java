package com.fiap.product.core.gateway;

import com.fiap.product.core.entity.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductGateway {
    Optional<Product> findBySku(String sku);

    Set<Product> findAll();

    void save(Product product);

    void deleteBySku(String sku);
}
