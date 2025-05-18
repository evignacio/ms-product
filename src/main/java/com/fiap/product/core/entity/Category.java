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
    FASHION,
    TOOLS,
    FURNITURE,
    FOOD,
    GAMES,
    OUTDOORS,
    HOME,
    OFFICE_SUPPLIES;

    public static Category fromString(String category) {
        return Category.valueOf(category.toUpperCase());
    }
}
