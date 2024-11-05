package com.firm_base.farmer_service.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FarmerDetailsDto {
    private String nationalId;
    private String fullName;
    private String phoneNumber;
    private String emailAddress;
}
