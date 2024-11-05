package com.firm_base.product_service.service;

import com.firm_base.product_service.model.Product;
import com.firm_base.product_service.model.dto.ProductRequest;
import com.firm_base.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createNewProduct(ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findByNameIgnoreCaseAndDeletedFalse(productRequest.getName());
        if (optionalProduct.isPresent()) {
            throw new RuntimeException("Product with name " + productRequest.getName() + " already exists");
        }

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        return productRepository.save(product);
    }

    @Override
    public Page<Product> fetchAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product fetchProduct(UUID productId) {
        return productRepository.findByPublicIdAndDeletedIsFalse(productId).orElse(null);
    }
}
