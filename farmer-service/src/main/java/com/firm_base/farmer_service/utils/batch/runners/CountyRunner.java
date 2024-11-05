package com.firm_base.farmer_service.utils.batch.runners;

import com.firm_base.farmer_service.service.BatchJobService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CountyRunner implements CommandLineRunner {
    final BatchJobService batchJobService;

    public CountyRunner(BatchJobService batchJobService) {
        this.batchJobService = batchJobService;
    }

    @Override
    public void run(String... args) throws Exception {
        batchJobService.processCountyImport();
    }
}
