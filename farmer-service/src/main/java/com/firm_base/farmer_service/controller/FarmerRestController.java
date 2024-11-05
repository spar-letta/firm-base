package com.firm_base.farmer_service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.docs.Examples;
import com.firm_base.farmer_service.model.Farmer;
import com.firm_base.farmer_service.model.request.FarmerContactDto;
import com.firm_base.farmer_service.model.request.FarmerDto;
import com.firm_base.farmer_service.service.FarmerService;
import com.firm_base.farmer_service.views.BaseView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/farmers")
@RequiredArgsConstructor
public class FarmerRestController {
    private final FarmerService farmerService;

    @PreAuthorize("hasAuthority('CREATE_FARMER')")
    @PostMapping
    @Operation(summary = "Create Farmer", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.Farmer_OK_RESPONSE)))
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(examples = {@ExampleObject(name = "Create product Request", value = Examples.CREATE_Farmer_REQUEST)})))
    @JsonView(BaseView.FarmerView.class)
    public Farmer createFarmer(@RequestBody FarmerDto farmerDto, @Parameter(hidden = true) Authentication loggedInUser) {
        return farmerService.createFarmer(farmerDto, loggedInUser);
    }

    @PreAuthorize("hasAuthority('READ_FARMERS')")
    @GetMapping
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.Farmers_OK_RESPONSE)))})
    @JsonView(BaseView.FarmerView.class)
    public Page<Farmer> fetchAllFarmers(@Parameter(hidden = true) Authentication loggedInUser, Pageable pageable) {
        return farmerService.fetchAllFarmers(pageable);
    }

    @PreAuthorize("hasAuthority('READ_FARMER')")
    @GetMapping("/{farmerId}")
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.Farmer_OK_RESPONSE)))})
    @JsonView(BaseView.FarmerView.class)
    public Farmer getFarmer(@PathVariable(name = "farmerId") UUID farmerId, @Parameter(hidden = true) Authentication loggedInUser){
        return farmerService.fetchFarmer(farmerId);
    }

    @PreAuthorize("hasAuthority('CREATE_CONTACT')")
    @PatchMapping("/{farmerId}/actions")
    @Operation(summary = "Create Farmer contact",requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(examples = {@ExampleObject(name = "Create Contact Request", value = Examples.CREATE_Farmer_Contact_REQUEST)})))
    @JsonView(BaseView.FarmerView.class)
    public void farmerActions(@PathVariable(name = "farmerId") UUID farmerId, @Valid @RequestBody FarmerContactDto farmerContactDto, @Parameter(hidden = true) Authentication loggedInUser){
     farmerService.farmerContact(farmerId, farmerContactDto);
    }
}
