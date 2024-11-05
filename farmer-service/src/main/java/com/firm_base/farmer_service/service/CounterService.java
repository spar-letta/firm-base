package com.firm_base.farmer_service.service;

import com.firm_base.farmer_service.model.dataType.CounterType;

public interface CounterService {
    Integer getNextCounter(CounterType counterType);
}
