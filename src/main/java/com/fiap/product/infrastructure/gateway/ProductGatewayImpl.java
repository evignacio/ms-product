package com.fiap.product.infrastructure.gateway;

import com.fiap.product.core.entity.Product;
import com.fiap.product.core.gateway.ProductGateway;
import com.fiap.product.infrastructure.mapper.ProductMapper;
import com.fiap.product.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository productRepository;

    public ProductGatewayImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return productRepository.findBySku(sku)
                .map(ProductMapper::toEntity);
    }

    @Override
    public Set<Product> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public void save(Product product) {
        productRepository.save(ProductMapper.toModel(product));
    }

    @Override
    public void deleteBySku(String sku) {
        productRepository.deleteBySku(sku);
    }
}
