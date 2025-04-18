package com.fiap.product.core.usecase;

import com.fiap.product.core.entity.Category;
import com.fiap.product.core.entity.Product;
import com.fiap.product.core.gateway.ProductGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllProductsUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private FindAllProductsUseCase findAllProductsUseCase;

    @Test
    void shouldFindAllProducts() {
        var product1 = new Product("123456", "Product Name", BigDecimal.TEN, "Product Description", Category.ELECTRONICS);
        var product2 = new Product("654321", "Product Name 2", BigDecimal.TWO, "Product Description 2", Category.BOOKS);
        var products = Set.of(product1, product2);

        when(productGateway.findAll()).thenReturn(products);

        var result = findAllProductsUseCase.execute();
        verify(productGateway).findAll();
        assertThat(result).hasSize(2);
    }
}
