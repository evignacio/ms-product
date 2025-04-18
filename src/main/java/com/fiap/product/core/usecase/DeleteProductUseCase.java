package com.fiap.product.core.usecase;

import com.fiap.product.core.gateway.ProductGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeleteProductUseCase {

    private final ProductGateway productGateway;

    public DeleteProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public void execute(String sku) {
        log.info("Deleting product with SKU: {}", sku);
        var productOpt = productGateway.findBySku(sku);

        if (productOpt.isEmpty()) {
            log.error("Product not found with SKU: {}", sku);
            throw new IllegalStateException("Product not found with SKU: " + sku);
        }
        log.info("Product found with SKU: {}", sku);
        productGateway.deleteBySku(sku);
    }
}
