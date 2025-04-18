package com.fiap.product.core.gateway;

import com.fiap.product.core.entity.Product;

import java.util.Optional;

public interface ProductGateway {
    Optional<Product> findBySku(String sku);
    void save(Product product);
}
