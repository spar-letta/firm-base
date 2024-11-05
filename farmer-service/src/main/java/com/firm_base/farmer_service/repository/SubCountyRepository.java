package com.firm_base.farmer_service.repository;

import com.firm_base.farmer_service.model.County;
import com.firm_base.farmer_service.model.SubCounty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubCountyRepository extends JpaRepository<SubCounty, Long> {
    Optional<SubCounty> findByNameIgnoreCaseAndCountyAndDeletedIsFalse(String name, County savedCounty);

    Optional<SubCounty> findByPublicId(UUID subCountyId);
}
