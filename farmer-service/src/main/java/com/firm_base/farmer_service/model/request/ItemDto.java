package com.firm_base.farmer_service.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ItemDto {

    @NotNull
    @NotNull
    private UUID itemId;
    private Integer quantity;
    private String description;
}
