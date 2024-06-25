package com.epam.komarov.cadabra.shop.batch;

import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class BatchConfiguration {

    @Bean
    public JobLauncher asyncJobLauncher(JobRepository jobRepository) throws BatchConfigurationException {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        try {
            jobLauncher.afterPropertiesSet();
            return jobLauncher;
        } catch (Exception e) {
            throw new BatchConfigurationException("Unable to configure the job launcher", e);
        }
    }

}
