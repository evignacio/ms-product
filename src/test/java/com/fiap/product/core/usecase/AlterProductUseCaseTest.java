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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlterProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private AlterProductUseCase alterProductUseCase;

    @Test
    void shouldAlterProduct() {
        var createProductDTO = new CreateProductDTO(
                "123456",
                "Video Game",
                "Playstation 5 Serie X",
                "ELECTRONICS",
                BigDecimal.TEN
        );

        var product = new Product(
                "idProduct",
                "123456",
                "Video Game",
                BigDecimal.ONE,
                "Playstation 5",
                Category.ELECTRONICS
        );

        when(productGateway.findBySku("123456")).thenReturn(Optional.of(product));

        var result = alterProductUseCase.execute(createProductDTO);

        verify(productGateway, times(1)).save(any(Product.class));
        assertThat(result.getId()).isEqualTo("idProduct");
        assertThat(result.getSku()).isEqualTo("123456");
        assertThat(result.getName()).isEqualTo("Video Game");
        assertThat(result.getPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(result.getDescription()).isEqualTo("Playstation 5 Serie X");
        assertThat(result.getCategory()).isEqualTo(Category.ELECTRONICS);
    }

    @Test
    void shouldReturnProductNotFoundException() {
        var createProductDTO = new CreateProductDTO(
                "123456",
                "Video Game",
                "Playstation 5 Serie X",
                "ELECTRONICS",
                BigDecimal.TEN
        );

        when(productGateway.findBySku("123456")).thenReturn(Optional.empty());

        var exception = catchThrowable(() -> alterProductUseCase.execute(createProductDTO));

        verify(productGateway, times(0)).save(any(Product.class));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Product not found with SKU: 123456");
    }
}
