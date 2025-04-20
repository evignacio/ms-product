package com.fiap.product.infrastructure.repository;

import com.fiap.product.infrastructure.repository.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {
    Optional<ProductModel> findBySku(String sku);

    void deleteBySku(String sku);
}

