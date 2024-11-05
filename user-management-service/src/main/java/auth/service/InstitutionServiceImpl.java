package auth.service;

import auth.dto.request.InstitutionDto;
import auth.entity.Institution;
import auth.entity.dataType.CounterType;
import auth.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final CounterService counterService;

    @Override
    public Institution createInstitution(InstitutionDto institutionDto) {
        if(institutionDto.getInstitutionName() == null || institutionDto.getInstitutionName().isEmpty()) {
            throw new IllegalArgumentException("Institution name cannot be empty");
        }

        if (institutionDto.getEmailAddress() == null || institutionDto.getEmailAddress().isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be empty");
        }
        Institution institution = new Institution();
        institution.setInstitutionName(institutionDto.getInstitutionName());
        institution.setEmailAddress(institutionDto.getEmailAddress());
        institution.setWebsite(institutionDto.getWebsite());
        institution.setPhoneNumber(institutionDto.getPhoneNumber());
        institution.setUniqueCode(String.format("INT-%d", counterService.getNextCounter(CounterType.INSTITUTION)));
        return institutionRepository.save(institution);
    }

    @Override
    public Page<Institution> fetchInstitution(Pageable pageable) {
        return institutionRepository.findAll(pageable);
    }
}
