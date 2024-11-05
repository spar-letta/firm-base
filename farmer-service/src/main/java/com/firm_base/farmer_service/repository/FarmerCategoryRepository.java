package com.firm_base.farmer_service.repository;

import com.firm_base.farmer_service.model.FarmerCategory;
import com.firm_base.farmer_service.model.dataType.FarmerCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FarmerCategoryRepository extends JpaRepository<FarmerCategory, Long> {
    Optional<FarmerCategory> findByCategoryAndDeletedFalse(FarmerCategoryEnum category);

    FarmerCategory findByPublicId(UUID itemId);
}
