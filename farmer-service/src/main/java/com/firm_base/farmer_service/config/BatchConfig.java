package com.firm_base.farmer_service.config;

import com.firm_base.farmer_service.model.County;
import com.firm_base.farmer_service.utils.batch.CountyItemProcessor;
import com.firm_base.farmer_service.utils.batch.CountyItemWriter;
import com.firm_base.farmer_service.utils.batch.TaskLetStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    public FlatFileItemReader<County> countyItemReader() {
        FlatFileItemReader<County> reader = new FlatFileItemReader<County>();
        reader.setResource(new ClassPathResource("county.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<County>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"code", "name", "subCountyNames"});
                setDelimiter(":");
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<County>() {{
                setTargetType(County.class);
            }});
        }});
        return reader;
    }

    @Bean
    public CountyItemProcessor countyItemProcessor() {
        return new CountyItemProcessor();
    }

    @Bean
    public CountyItemWriter countyItemWriter() {
        return new CountyItemWriter();
    }

    @Bean
    public Step stepCounty(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
        var step = new StepBuilder("stepProduct", jobRepository)
                .<County, County>chunk(10, transactionManage)
                .reader(countyItemReader())
                .processor(countyItemProcessor())
                .writer(countyItemWriter())
                .build();
        return step;
    }

    @Bean
    public Tasklet taskletTest() {
        return new TaskLetStatus();
    }


    @Bean
    public Step stepTest(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
        var step = new StepBuilder("stepTest", jobRepository)
                .tasklet(taskletTest(), transactionManage)
                .build();
        return step;
    }

    @Bean
    public Job countyJob(JobRepository jobRepository,
                          @Qualifier("stepCounty") Step step1,
                          @Qualifier("stepTest") Step step2) {
        return new JobBuilder("countyJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .build();
    }
}
