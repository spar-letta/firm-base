package com.firm_base.product_service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.product_service.docs.Examples;
import com.firm_base.product_service.model.Product;
import com.firm_base.product_service.model.dto.ProductRequest;
import com.firm_base.product_service.service.ProductService;
import com.firm_base.product_service.views.BaseView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasAuthority('CREATE_PRODUCT')")
    @PostMapping
    @Operation(summary = "Create product", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.PRODUCT_OK_RESPONSE)))
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(examples = {@ExampleObject(name = "Create product Request", value = Examples.CREATE_PRODUCT_REQUEST)})))
    @JsonView(BaseView.ProductView.class)
    public Product createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.createNewProduct(productRequest);
    }

    @PreAuthorize("hasAuthority('READ_PRODUCT')")
    @GetMapping
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.PRODUCTS_OK_RESPONSE)))})
    @JsonView(BaseView.ProductView.class)
    public Page<Product> getAllProducts(Pageable pageable) {
        return productService.fetchAllProducts(pageable);
    }
}
