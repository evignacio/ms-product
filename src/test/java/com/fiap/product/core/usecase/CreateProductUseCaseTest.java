package com.fiap.product.core.usecase;

import com.fiap.product.core.dto.CreateProductDTO;
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

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private CreateProductUseCase createProductUseCase;

    @Test
    void shouldCreateProduct() {
        var createProductDTO = new CreateProductDTO(
                "123456",
                "Product Name",
                "Product Description",
                "ELECTRONICS",
                BigDecimal.TEN
        );

        when(productGateway.findBySku("123456")).thenReturn(Optional.empty());
        doNothing().when(productGateway).save(any(Product.class));

        var product = createProductUseCase.execute(createProductDTO);

        verify(productGateway, times(1)).findBySku("123456");
        assertThat(product.getSku()).isEqualTo("123456");
        assertThat(product.getName()).isEqualTo("Product Name");
        assertThat(product.getDescription()).isEqualTo("Product Description");
        assertThat(product.getCategory()).isEqualTo(Category.ELECTRONICS);
        assertThat(product.getPrice()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void shouldReturnSkuExists() {
        var createProductDTO = new CreateProductDTO(
                "123456",
                "Product Name",
                "Product Description",
                "ELECTRONICS",
                BigDecimal.TEN
        );

        var product = new Product("123456", "Product Name", BigDecimal.TEN, "Product Description", Category.ELECTRONICS);
        when(productGateway.findBySku("123456")).thenReturn(Optional.of(product));

        var exception = catchThrowable(() -> createProductUseCase.execute(createProductDTO));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Product with SKU already exists");
    }
}
