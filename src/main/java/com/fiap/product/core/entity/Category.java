package com.fiap.product.core.entity;

public enum Category {
    ELECTRONICS,
    CLOTHING,
    HOME_APPLIANCES,
    BOOKS,
    TOYS,
    SPORTS,
    BEAUTY,
    AUTOMOTIVE,
    GARDEN,
    OFFICE_SUPPLIES;

    public static Category fromString(String category) {
        return Category.valueOf(category.toUpperCase());
    }
}
