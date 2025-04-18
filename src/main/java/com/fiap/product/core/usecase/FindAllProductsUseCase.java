package com.fiap.product.core.usecase;

import com.fiap.product.core.entity.Product;
import com.fiap.product.core.gateway.ProductGateway;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class FindAllProductsUseCase {

    private final ProductGateway productGateway;

    public FindAllProductsUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    Set<Product> execute() {
        log.info("Finding all products");
        var products = productGateway.findAll();
        log.info("Found {} products", products.size());
        return products;
    }
}
