package com.firm_base.farmer_service.utils.batch;

import com.firm_base.farmer_service.model.County;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class CountyItemProcessor implements ItemProcessor<County, County> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public County process(County item) throws Exception {
        final String name = item.getName();
        final String code = item.getCode();
        final List<String> subCounties = item.getSubCountyNames();
        return new County(name, code, subCounties);
    }
}
