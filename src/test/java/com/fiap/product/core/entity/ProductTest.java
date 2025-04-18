package com.fiap.product.core.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class ProductTest {

    @Test
    void shloudCreateProduct() {
        Product product = new Product("123456", "Product Name", BigDecimal.valueOf(99.99), "Product Description", Category.ELECTRONICS);
        assertThat(product.getId()).isNotNull();
        assertThat(product.getSku()).isEqualTo("123456");
        assertThat(product.getName()).isEqualTo("Product Name");
        assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(99.99));
        assertThat(product.getDescription()).isEqualTo("Product Description");
        assertThat(product.getCategory()).isEqualTo(Category.ELECTRONICS);
    }

    @Test
    void shloudReturnErrorSkuNull() {
        var exception = catchThrowable(() -> new Product(null, "Product Name", BigDecimal.valueOf(99.99), "Product Description", Category.ELECTRONICS));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("SKU cannot be null");
    }

    @Test
    void shloudReturnSkuInvalid() {
        var exception = catchThrowable(() -> new Product("123", "Product Name", BigDecimal.valueOf(99.99), "Product Description", Category.ELECTRONICS));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("SKU must be between 5 and 20 characters");
    }

    @Test
    void shloudReturnNameNull() {
        var exception = catchThrowable(() -> new Product("123456", null, BigDecimal.valueOf(99.99), "Product Description", Category.ELECTRONICS));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Name cannot be null");
    }

    @Test
    void shloudReturnNameInvalid() {
        var exception = catchThrowable(() -> new Product("123456", "", BigDecimal.valueOf(99.99), "Product Description", Category.ELECTRONICS));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Name must be between 3 and 50 characters");
    }

    @Test
    void shloudReturnPriceNull() {
        var exception = catchThrowable(() -> new Product("123456", "Product Name", null, "Product Description", Category.ELECTRONICS));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Price cannot be null");
    }

    @Test
    void shloudReturnPriceInvalid() {
        var exception = catchThrowable(() -> new Product("123456", "Product Name", BigDecimal.valueOf(-1), "Product Description", Category.ELECTRONICS));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Price must be greater than zero");
    }

    @Test
    void shloudReturnCategoryNull() {
        var exception = catchThrowable(() -> new Product("123456", "Product Name", BigDecimal.valueOf(99.99), "Product Description", null));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Category cannot be null");
    }
}
