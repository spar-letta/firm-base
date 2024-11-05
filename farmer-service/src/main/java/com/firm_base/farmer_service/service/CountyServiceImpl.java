package com.firm_base.farmer_service.service;

import com.firm_base.farmer_service.model.County;
import com.firm_base.farmer_service.model.SubCounty;
import com.firm_base.farmer_service.repository.CountyRepository;
import com.firm_base.farmer_service.repository.SubCountyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountyServiceImpl implements CountyService {

    private final CountyRepository countyRepository;
    private final SubCountyRepository subCountyRepository;


    @Override
    public void createCountyAndSubCounty(County item) {
        if (item.getName() != null && item.getCode() != null && (item.getSubCountyNames() != null)) {
            Optional<County> optionalCounty = countyRepository.findByNameIgnoreCaseAndDeletedIsFalse(item.getName());
            if (!optionalCounty.isPresent()) {
                County county = new County();
                county.setName(item.getName());
                county.setCode(item.getCode());
                County savedCounty = countyRepository.save(county);
                createSubCounty(savedCounty, item.getSubCountyNames());
            }
        }
    }

    @Override
    public Page<County> getCounties(Pageable pageable) {
        return countyRepository.findAll(pageable);
    }

    private void createSubCounty(County savedCounty, List<String> subCountyNames) {
        if (subCountyNames != null && !subCountyNames.isEmpty()) {
            subCountyNames.forEach(subCountyName -> {
                String[] names = subCountyName.split(",");
                Arrays.stream(names).forEach(name -> {
                    Optional<SubCounty> optionalSubCounty = subCountyRepository.findByNameIgnoreCaseAndCountyAndDeletedIsFalse(name, savedCounty);
                    if (!optionalSubCounty.isPresent()) {
                        SubCounty subCounty = new SubCounty();
                        subCounty.setName(name);
                        subCounty.setCounty(savedCounty);
                        subCountyRepository.save(subCounty);
                    }
                });
            });
        }
    }
}
