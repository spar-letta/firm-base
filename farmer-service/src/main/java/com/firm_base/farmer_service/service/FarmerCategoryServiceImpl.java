package com.firm_base.farmer_service.service;

import com.firm_base.farmer_service.model.FarmerCategory;
import com.firm_base.farmer_service.model.request.FarmerCategoryRequest;
import com.firm_base.farmer_service.repository.FarmerCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FarmerCategoryServiceImpl implements FarmerCategoryService {

    private final FarmerCategoryRepository farmerCategoryRepository;

    @Override
    public FarmerCategory createFarmerCategory(FarmerCategoryRequest farmerCategoryRequest) {
        if (farmerCategoryRepository.findByCategoryAndDeletedFalse(farmerCategoryRequest.getCategory()).isPresent()) {
            throw new RuntimeException("Category exists");
        }
        FarmerCategory farmerCategory = new FarmerCategory();
        farmerCategory.setCategory(farmerCategoryRequest.getCategory());
        farmerCategory.setLandSize(farmerCategoryRequest.getLandSize());
        return farmerCategoryRepository.save(farmerCategory);
    }

    @Override
    public Page<FarmerCategory> fetchAll(Pageable pageable) {
        return farmerCategoryRepository.findAll(pageable);
    }
}
