package com.farm.base.notification_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class MessageDto {
    private String voucherName;
    private String voucherNumber;
    private LocalDate expiryDate;
    private String collectionCentre;
    private List<FarmerDetailsDto> farmerList = new ArrayList<>();
    private List<PickItemDto> pickItemDtoList = new ArrayList<>();
}
