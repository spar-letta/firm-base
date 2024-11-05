package com.firm_base.farmer_service.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PickItemDto {
    private String itemName;
    private String description;
    private Integer quantity;
}
