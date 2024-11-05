package com.firm_base.farmer_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class BatchJobServiceImpl implements BatchJobService {

    private final JobLauncher jobLauncher;
    private final Job countyJob;

    public BatchJobServiceImpl(JobLauncher jobLauncher, Job countyJob) {
        this.jobLauncher = jobLauncher;
        this.countyJob = countyJob;
    }

    @Override
    public Optional<JobExecution> processCountyImport() {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", new Date());
        log.info("Start of County import Job >>>>>");

        try {
            return Optional.of(jobLauncher.run(countyJob, builder.toJobParameters()));
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
