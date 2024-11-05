package com.firm_base.farmer_service.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Setter
@Getter
public class VoucherDto {

    private String name;

    @Min(1)
    private Integer numberOfFarmers; // how many farmers

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    private List<ItemDto> voucherItemListIds = new ArrayList<>();

    @NotNull
    private String collectionCentre;

    @NotNull
    @NotEmpty
    private Set<UUID> farmerListIds = new HashSet<>();
}
