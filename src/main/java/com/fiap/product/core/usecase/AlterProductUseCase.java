package com.fiap.product.core.usecase;

import com.fiap.product.core.dto.CreateProductDTO;
import com.fiap.product.core.entity.Category;
import com.fiap.product.core.entity.Product;
import com.fiap.product.core.gateway.ProductGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlterProductUseCase {
    private final ProductGateway productGateway;

    public AlterProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(CreateProductDTO input) {
        log.info("Altering product with SKU: {}", input.sku());
        var productOpt = productGateway.findBySku(input.sku());
        if (productOpt.isEmpty()) {
            log.error("Product not found with SKU: {}", input.sku());
            throw new IllegalStateException("Product not found with SKU: " + input.sku());
        }

        var product = new Product(
                productOpt.get().getId(),
                input.sku(), input.name(),
                input.price(),
                input.description(),
                Category.fromString(input.category())
        );

        productGateway.save(product);
        log.info("Product altered with SKU: {}", input.sku());
        return product;
    }
}
