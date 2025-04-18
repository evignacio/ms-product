package com.fiap.product.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private String id;
    private String sku;
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;

    public Product(String sku, String name, BigDecimal price, String description, Category category) {
        setId(UUID.randomUUID().toString());
        setSku(sku);
        setName(name);
        setPrice(price);
        setDescription(description);
        setCategory(category);
    }

    public Product(String id, String sku, String name, BigDecimal price, String description, Category category) {
        this(sku, name, price, description, category);
        setId(id);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        if (id == null)
            throw new IllegalStateException("Id cannot be null");

        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    private void setSku(String sku) {
        if (sku == null)
            throw new IllegalStateException("SKU cannot be null");

        if (sku.length() < 5 || sku.length() > 20)
            throw new IllegalStateException("SKU must be between 5 and 20 characters");

        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null)
            throw new IllegalStateException("Name cannot be null");

        if (name.length() < 3 || name.length() > 50)
            throw new IllegalStateException("Name must be between 3 and 50 characters");

        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        if (price == null)
            throw new IllegalStateException("Price cannot be null");

        if (price.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalStateException("Price must be greater than zero");

        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        if (description == null)
            throw new IllegalStateException("Description cannot be null");

        if (description.length() < 10 || description.length() > 200)
            throw new IllegalStateException("Description must be between 10 and 200 characters");

        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    private void setCategory(Category category) {
        if (category == null)
            throw new IllegalStateException("Category cannot be null");

        this.category = category;
    }
}
