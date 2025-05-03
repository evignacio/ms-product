package com.fiap.product.infrastructure.controller;

import com.fiap.product.core.dto.CreateProductDTO;
import com.fiap.product.core.entity.Product;
import com.fiap.product.core.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final FindProductUseCase findProductUseCase;
    private final FindAllProductsUseCase findAllProductsUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final AlterProductUseCase alterProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(FindProductUseCase findProductUseCase, FindAllProductsUseCase findAllProductsUseCase, CreateProductUseCase createProductUseCase, AlterProductUseCase alterProductUseCase, DeleteProductUseCase deleteProductUseCase) {
        this.findProductUseCase = findProductUseCase;
        this.findAllProductsUseCase = findAllProductsUseCase;
        this.createProductUseCase = createProductUseCase;
        this.alterProductUseCase = alterProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @GetMapping("{sku}")
    public ResponseEntity<Product> findProduct(@PathVariable String sku) {
        return ResponseEntity.ok(findProductUseCase.execute(sku));
    }

    @GetMapping
    public ResponseEntity<Set<Product>> findAllProducts() {
        return ResponseEntity.ok(findAllProductsUseCase.execute());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        return ResponseEntity.ok(createProductUseCase.execute(createProductDTO));
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody CreateProductDTO createProductDTO) {
        return ResponseEntity.ok(alterProductUseCase.execute(createProductDTO));
    }

    @DeleteMapping("{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String sku) {
        deleteProductUseCase.execute(sku);
        return ResponseEntity.ok()
                .build();
    }
}
