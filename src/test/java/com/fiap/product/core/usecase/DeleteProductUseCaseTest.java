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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private DeleteProductUseCase deleteProductUseCase;

    @Test
    void shouldDeleteProduct() {
        var sku = "123456";
        var product = new Product("123456", "Product Name", BigDecimal.TEN, "Product Description", Category.ELECTRONICS);

        when(productGateway.findBySku(sku)).thenReturn(Optional.of(product));
        doNothing().when(productGateway).deleteBySku(sku);

        deleteProductUseCase.execute(sku);

        verify(productGateway, times(1)).deleteBySku(sku);
    }

    @Test
    void shouldReturnProductNotFound() {
        var sku = "123456";

        when(productGateway.findBySku(sku)).thenReturn(Optional.empty());

        var exception = catchThrowable(() -> deleteProductUseCase.execute(sku));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Product not found with SKU: " + sku);

        verify(productGateway, times(1)).findBySku(sku);
    }
}
