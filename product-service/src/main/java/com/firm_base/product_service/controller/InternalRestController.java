package com.firm_base.product_service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.product_service.model.Product;
import com.firm_base.product_service.service.ProductService;
import com.firm_base.product_service.views.BaseView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/internal")
@RequiredArgsConstructor
@RestController
public class InternalRestController {

    private final ProductService productService;

    @GetMapping("/products/{productId}")
    @JsonView(BaseView.ProductView.class)
    public Product getFarmer(@PathVariable(name = "productId") UUID productId){
        return productService.fetchProduct(productId);
    }
}
