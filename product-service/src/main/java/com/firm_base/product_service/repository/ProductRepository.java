package com.firm_base.product_service.repository;

import com.firm_base.product_service.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameIgnoreCaseAndDeletedFalse(@NotNull String name);

    Optional<Product> findByPublicIdAndDeletedIsFalse(UUID productId);
}
