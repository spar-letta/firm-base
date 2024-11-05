package com.firm_base.farmer_service.service;

import com.firm_base.farmer_service.model.County;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountyService {
    void createCountyAndSubCounty(County item);

    Page<County> getCounties(Pageable pageable);
}
