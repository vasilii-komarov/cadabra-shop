package com.epam.komarov.cadabra.shop.batch.user;

import com.epam.komarov.cadabra.shop.batch.BatchConstants;
import com.epam.komarov.cadabra.shop.batch.JobCompletionNotificationListener;
import com.epam.komarov.cadabra.shop.entity.UserEntity;
import com.epam.komarov.cadabra.shop.dto.UserDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class UserBatchConfiguration {

    @Bean
    public Job importUsersJob(JobRepository jobRepository, Step importUsersStep,
                              JobCompletionNotificationListener listener) {
        return new JobBuilder(BatchConstants.IMPORT_USERS_JOB_NAME, jobRepository)
            .listener(listener)
            .start(importUsersStep)
            .build();
    }

    @Bean
    public Step importUsersStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                                UserDtoItemReader reader, UserDtoItemProcessor processor,
                                UserEntityItemWriter writer) {
        return new StepBuilder(BatchConstants.IMPORT_USERS_STEP_NAME, jobRepository)
            .<UserDto, UserEntity>chunk(3, transactionManager)
            .allowStartIfComplete(true)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }

}
