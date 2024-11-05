package com.firm_base.farmer_service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.docs.Examples;
import com.firm_base.farmer_service.model.Voucher;
import com.firm_base.farmer_service.model.request.VoucherDto;
import com.firm_base.farmer_service.service.VoucherService;
import com.firm_base.farmer_service.views.BaseView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/vouchers")
@RequiredArgsConstructor
@RestController
public class VoucherRestController {

    private final VoucherService voucherService;

    @PreAuthorize("hasAuthority('CREATE_VOUCHER')")
    @PostMapping
    @Operation(summary = "Create Voucher", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.Voucher_OK_RESPONSE)))
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(examples = {@ExampleObject(name = "Create Voucher Request", value = Examples.CREATE_Voucher_REQUEST)})))
    @JsonView(BaseView.VoucherView.class)
    public Voucher createVoucher(@RequestBody VoucherDto voucherDto) {
        return voucherService.createVoucher(voucherDto);
    }
}
