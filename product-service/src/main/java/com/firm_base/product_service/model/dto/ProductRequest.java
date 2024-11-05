package com.firm_base.product_service.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductRequest {

    @NotNull
    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;
}
