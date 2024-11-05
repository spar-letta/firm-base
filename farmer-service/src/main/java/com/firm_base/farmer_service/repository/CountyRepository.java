package com.firm_base.farmer_service.repository;

import com.firm_base.farmer_service.model.County;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CountyRepository extends JpaRepository<County, Long> {
    Optional<County> findByNameIgnoreCaseAndDeletedIsFalse(String name);

    Optional<County> findByPublicId(UUID countyId);
}
