package com.firm_base.farmer_service.service;

import com.firm_base.farmer_service.model.FarmerCategory;
import com.firm_base.farmer_service.model.request.FarmerCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FarmerCategoryService {
    FarmerCategory createFarmerCategory(FarmerCategoryRequest farmerCategoryRequest);

    Page<FarmerCategory> fetchAll(Pageable pageable);
}
