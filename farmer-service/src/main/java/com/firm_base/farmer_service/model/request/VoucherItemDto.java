package com.firm_base.farmer_service.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class VoucherItemDto {

    @NotNull
    private String name;

    @Min(1)
    private Integer quantity;

    @NotNull
    private UUID publicId;

    private String description;
}
