package com.firm_base.farmer_service.model.request;

import com.firm_base.farmer_service.model.dataType.FarmerCategoryEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FarmerCategoryRequest {
    @NotNull
    private FarmerCategoryEnum category;

    @Min(1)
    private Integer landSize;
}
