package com.fiap.product.core.usecase;

import com.fiap.product.core.entity.Product;
import com.fiap.product.core.gateway.ProductGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindProductUseCase {
    private final ProductGateway productGateway;

    public FindProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(String sku) {
        log.info("Finding product with SKU: {}", sku);
        var productOpt = productGateway.findBySku(sku);

        if (productOpt.isEmpty()) {
            log.error("Product not found with SKU: {}", sku);
            throw new IllegalStateException("Product not found with SKU: " + sku);
        }

        log.info("Product found with SKU: {}", sku);
        return productOpt.get();
    }
}
