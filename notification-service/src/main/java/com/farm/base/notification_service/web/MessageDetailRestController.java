package com.farm.base.notification_service.web;

import com.farm.base.notification_service.model.MessageDetail;
import com.farm.base.notification_service.model.docs.Examples;
import com.farm.base.notification_service.service.MessageDetailService;
import com.farm.base.notification_service.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequestMapping("/messages")
@RestController
public class MessageDetailRestController {

    @Autowired
    private MessageDetailService messageDetailService;

    @PreAuthorize("hasAuthority('READ_MESSAGES')")
    @GetMapping
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.MESSAGE_OK_RESPONSE)))})
    @JsonView(BaseView.Message.class)
    public Page<MessageDetail> fetchDetails(
            @RequestParam(name = "nationalId", required = false) Long nationalId,
            @RequestParam(name = "searchParam", required = false) String searchParam,
            @RequestParam(name = "voucherNumber", required = false) String voucherNumber,
            @RequestParam(value = "expiryDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") @Parameter(example = "dd-MM-yyyy") LocalDate expiryDate,
            @Parameter(hidden = true) Pageable pageable) {
        return messageDetailService.fetchAllMessages(nationalId, searchParam, voucherNumber, expiryDate, pageable);
    }
}
