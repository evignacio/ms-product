package com.fiap.product.infrastructure.repository.model;

import com.fiap.product.core.entity.Category;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document("products")
public class ProductModel {
    @Id
    private String id;
    private String sku;
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;
}
