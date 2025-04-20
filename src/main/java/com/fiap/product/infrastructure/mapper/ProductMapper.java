package com.fiap.product.infrastructure.mapper;

import com.fiap.product.core.entity.Product;
import com.fiap.product.infrastructure.repository.model.ProductModel;

public abstract class ProductMapper {
    private ProductMapper() {
    }

    public static Product toEntity(ProductModel productModel) {
        return new Product(
                productModel.getId(),
                productModel.getSku(),
                productModel.getName(),
                productModel.getPrice(),
                productModel.getDescription(),
                productModel.getCategory()
        );
    }

    public static ProductModel toModel(Product product) {
        return ProductModel.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory())
                .build();

    }
}
