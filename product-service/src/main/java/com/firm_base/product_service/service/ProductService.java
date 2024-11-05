package com.firm_base.product_service.service;

import com.firm_base.product_service.model.Product;
import com.firm_base.product_service.model.dto.ProductRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    Product createNewProduct(@Valid ProductRequest productRequest);

    Page<Product> fetchAllProducts(Pageable pageable);

    Product fetchProduct(UUID productId);
}
