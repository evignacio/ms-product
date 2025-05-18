package com.fiap.product.infrastructure.gateway;

import com.fiap.product.core.entity.Category;
import com.fiap.product.core.entity.Product;
import com.fiap.product.infrastructure.repository.ProductRepository;
import com.fiap.product.infrastructure.repository.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoGatewayImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductGatewayImpl productGateway;

    @Test
    void shouldProductBySku() {
        String sku = "12345";
        var productModel = ProductModel.builder()
                .id("1")
                .sku(sku)
                .name("Test Product")
                .price(BigDecimal.valueOf(10.0))
                .description("Test Description")
                .category(Category.CLOTHING)
                .build();

        when(productRepository.findBySku(sku)).thenReturn(Optional.of(productModel));
        var productOpt = productGateway.findBySku(sku);

        assertTrue(productOpt.isPresent());

        var product = productOpt.get();
        assertThat(product).isInstanceOf(Product.class);
        assertThat(product.getId()).isEqualTo(productModel.getId());
        assertThat(product.getSku()).isEqualTo(sku);
        assertThat(product.getName()).isEqualTo(productModel.getName());
        assertThat(product.getPrice()).isEqualTo(productModel.getPrice());
        assertThat(product.getDescription()).isEqualTo(productModel.getDescription());
        assertThat(product.getCategory()).isEqualTo(productModel.getCategory());
    }

    @Test
    void shouldFindAllProducts() {
        var productModel1 = ProductModel.builder()
                .id("1")
                .sku("12345")
                .name("Test Product")
                .price(BigDecimal.valueOf(10.0))
                .description("Test Description")
                .category(Category.CLOTHING)
                .build();

        var productModel2 = ProductModel.builder()
                .id("2")
                .sku("67890")
                .name("Test Product 2")
                .price(BigDecimal.valueOf(10.0))
                .description("Test Description 2")
                .category(Category.CLOTHING)
                .build();


        when(productRepository.findAll()).thenReturn(List.of(productModel1, productModel2));
        var products = productGateway.findAll();

        assertThat(products).hasSize(2);
    }

    @Test
    void shouldSaveProduct() {
        var productModel = ProductModel.builder()
                .id("1")
                .sku("12345")
                .name("Test Product")
                .price(BigDecimal.valueOf(10.0))
                .description("Test Description")
                .category(Category.CLOTHING)
                .build();

        var productEntity = new Product(
                "1",
                "12345",
                "Test Product",
                BigDecimal.valueOf(10.0),
                "Test Description",
                Category.CLOTHING
        );

        when(productRepository.save(productModel)).thenReturn(productModel);
        productGateway.save(productEntity);

        verify(productRepository, times(1)).save(productModel);
    }

    @Test
    void shouldDeleteProductBySku() {
        String sku = "12345";
        doNothing().when(productRepository).deleteBySku(sku);
        productGateway.deleteBySku(sku);
        verify(productRepository, times(1)).deleteBySku(sku);
    }
}
