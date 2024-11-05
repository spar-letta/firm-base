package com.firm_base.farmer_service.service;

import com.firm_base.farmer_service.model.Farmer;
import com.firm_base.farmer_service.model.request.FarmerContactDto;
import com.firm_base.farmer_service.model.request.FarmerDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface FarmerService {
    Farmer createFarmer(FarmerDto farmerDto, Authentication loggedInUser);

    Page<Farmer> fetchAllFarmers(Pageable pageable);

    void farmerContact(UUID farmerId, @Valid FarmerContactDto farmerContactDto);

    Farmer fetchFarmer(UUID farmerId);
}
