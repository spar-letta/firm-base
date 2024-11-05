package com.firm_base.farmer_service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.docs.Examples;
import com.firm_base.farmer_service.model.FarmerCategory;
import com.firm_base.farmer_service.model.request.FarmerCategoryRequest;
import com.firm_base.farmer_service.service.FarmerCategoryService;
import com.firm_base.farmer_service.views.BaseView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farmerCategories")
@RequiredArgsConstructor
public class FarmerCategoryRestController {

    private final FarmerCategoryService farmerCategoryService;

    @PreAuthorize("hasAuthority('CREATE_FARMER_CATEGORY')")
    @PostMapping
    @Operation(summary = "Create Farmer Category", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.FarmerCategory_OK_RESPONSE)))
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(examples = {@ExampleObject(name = "Create product Request", value = Examples.CREATE_FarmerCategory_REQUEST)})))
    @JsonView(BaseView.CategoryView.class)
    public FarmerCategory createFarmerCategory(@RequestBody FarmerCategoryRequest farmerCategoryRequest) {
        return farmerCategoryService.createFarmerCategory(farmerCategoryRequest);
    }

    @PreAuthorize("hasAuthority('READ_FARMER_CATEGORY')")
    @GetMapping
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.Farmer_Categories_OK_RESPONSE)))})
    @JsonView(BaseView.CategoryView.class)
    public Page<FarmerCategory> getAllFarmerCategories(Pageable pageable) {
        return farmerCategoryService.fetchAll(pageable);
    }

}
