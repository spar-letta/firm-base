package com.firm_base.farmer_service.model.request;

import com.firm_base.farmer_service.model.dataType.ActionType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FarmerContactDto {
    private String postalAddress;

    private String postalCode;

    private String emailAddress;

    @NotNull
    private String phoneNumber;

    private String location;

    @NotNull
    private ActionType actionType;
}
