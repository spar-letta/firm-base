package com.firm_base.farmer_service.repository;

import com.firm_base.farmer_service.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    Optional<Farmer> findByNationalIdAndDeletedIsFalse(String nationalId);

    Farmer findByPublicIdAndDeletedIsFalse(UUID farmerId);
}
