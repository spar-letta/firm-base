package com.firm_base.farmer_service.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.model.Contact;
import com.firm_base.farmer_service.model.County;
import com.firm_base.farmer_service.model.FarmerCategory;
import com.firm_base.farmer_service.model.SubCounty;
import com.firm_base.farmer_service.views.BaseView;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class FarmerDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String nationalId;

    private String otherName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    private UUID countyId;

    private UUID subCountyId;

    private Integer landSize;

    @NotNull
    private UUID institutionId;

    @NotEmpty
    private List<UUID> farmerCategoriesIds = new ArrayList<>();

}
