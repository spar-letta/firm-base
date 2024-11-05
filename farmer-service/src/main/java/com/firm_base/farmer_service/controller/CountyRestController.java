package com.firm_base.farmer_service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.docs.Examples;
import com.firm_base.farmer_service.model.County;
import com.firm_base.farmer_service.service.CountyService;
import com.firm_base.farmer_service.views.BaseView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/counties")
@RestController
@RequiredArgsConstructor
public class CountyRestController {
    private final CountyService countyService;

    @PreAuthorize("hasAuthority('READ_COUNTIES')")
    @GetMapping
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.County_OK_RESPONSE)))})
    @JsonView({BaseView.CountyView.class})
    public Page<County> getCounties(Pageable pageable) {
        return countyService.getCounties(pageable);
    }
}
