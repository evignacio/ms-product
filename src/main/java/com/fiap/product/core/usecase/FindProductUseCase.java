package com.fiap.product.core.usecase;

import com.fiap.product.core.entity.Product;
import com.fiap.product.core.gateway.ProductGateway;

public class FindProductUseCase {
    private final ProductGateway productGateway;

    public FindProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(String sku) {
        return productGateway.findBySku(sku)
                .orElseThrow(() -> new IllegalStateException("Product not found with SKU: " + sku));
    }
}
