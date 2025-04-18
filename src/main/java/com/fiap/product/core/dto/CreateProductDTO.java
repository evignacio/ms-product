package com.fiap.product.core.dto;

import java.math.BigDecimal;

public record CreateProductDTO(
        String sku,
        String name,
        String description,
        String category,
        BigDecimal price
) {
}
