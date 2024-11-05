package com.firm_base.farmer_service.repository;

import com.firm_base.farmer_service.model.vo.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Optional<Institution> findInstitutionByPublicId(UUID institutionId);
}
