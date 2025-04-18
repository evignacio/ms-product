package com.fiap.product.core.usecase;

import com.fiap.product.core.dto.CreateProductDTO;
import com.fiap.product.core.entity.Category;
import com.fiap.product.core.entity.Product;
import com.fiap.product.core.gateway.ProductGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateProductUseCase {

    private final ProductGateway productGateway;

    public CreateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    Product execute(CreateProductDTO input) {
        log.info("Creating product with SKU: {}", input.sku());

        var product = productGateway.findBySku(input.sku());
        if (product.isPresent()) {
            log.error("Product with SKU {} already exists", input.sku());
            throw new IllegalStateException("Product with SKU already exists");
        }

        var newProduct = new Product(input.sku(), input.name(), input.price(), input.description(), Category.fromString(input.category()));
        productGateway.save(newProduct);
        log.info("Product with SKU {} created successfully", input.sku());
        return newProduct;
    }
}
