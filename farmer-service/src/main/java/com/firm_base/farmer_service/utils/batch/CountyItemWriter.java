package com.firm_base.farmer_service.utils.batch;

import com.firm_base.farmer_service.model.County;
import com.firm_base.farmer_service.service.CountyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CountyItemWriter implements ItemWriter<County>, InitializingBean {

    @Autowired
    private CountyService countyService;

    @Override
    public void write(Chunk<? extends County> itemList) throws Exception {
        itemList.forEach(item -> {
            countyService.createCountyAndSubCounty(item);
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
