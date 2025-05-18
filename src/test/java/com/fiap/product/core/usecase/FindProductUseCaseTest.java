package com.fiap.product.core.usecase;

import com.fiap.product.core.entity.Category;
import com.fiap.product.core.entity.Product;
import com.fiap.product.core.exception.ProductNotFoundException;
import com.fiap.product.core.gateway.ProductGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private FindProductUseCase findProductUseCase;

    @Test
    void shouldFindProduct() {
        var sku = "123456";

        var product = new Product("idProduct", sku, "Product Name", BigDecimal.TEN, "Product Description", Category.ELECTRONICS);
        when(productGateway.findBySku(sku)).thenReturn(Optional.of(product));

        findProductUseCase.execute(sku);

        verify(productGateway, times(1)).findBySku(sku);
        assertThat(product.getSku()).isEqualTo(sku);
        assertThat(product.getName()).isEqualTo("Product Name");
        assertThat(product.getPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(product.getDescription()).isEqualTo("Product Description");
        assertThat(product.getCategory()).isEqualTo(Category.ELECTRONICS);
    }


    @Test
    void shouldReturnProductNotFound() {
        var sku = "123456";

        when(productGateway.findBySku(sku)).thenReturn(Optional.empty());

        var exception = catchThrowable(() -> findProductUseCase.execute(sku));

        assertThat(exception)
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product not found");

        verify(productGateway, times(1)).findBySku(sku);
    }
}
