package auth.service;

import auth.dto.request.InstitutionDto;
import auth.entity.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InstitutionService {
    Institution createInstitution(InstitutionDto institutionDto);

    Page<Institution> fetchInstitution(Pageable pageable);
}
