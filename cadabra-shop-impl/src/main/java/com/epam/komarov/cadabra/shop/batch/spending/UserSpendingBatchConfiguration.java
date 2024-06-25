package com.epam.komarov.cadabra.shop.batch.spending;

import com.epam.komarov.cadabra.shop.batch.BatchConstants;
import com.epam.komarov.cadabra.shop.batch.JobCompletionNotificationListener;
import com.epam.komarov.cadabra.shop.entity.UserSpendingEntity;
import com.epam.komarov.cadabra.shop.dto.UserSpendingDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class UserSpendingBatchConfiguration {

    @Bean
    public Job importUserSpendingJob(JobRepository jobRepository, Step importUserSpendingStep,
                                     JobCompletionNotificationListener listener) {
        return new JobBuilder(BatchConstants.IMPORT_USER_SPENDING_JOB_NAME, jobRepository)
            .listener(listener)
            .start(importUserSpendingStep)
            .build();
    }

    @Bean
    public Step importUserSpendingStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                                       ItemReader<UserSpendingDto> userSpendingDtoItemReader,
                                       ItemProcessor<UserSpendingDto, UserSpendingEntity> userSpendingDtoItemProcessor,
                                       ItemWriter<UserSpendingEntity> userSpendingEntityItemWriter) {
        return new StepBuilder(BatchConstants.IMPORT_USER_SPENDING_STEP_NAME, jobRepository)
            .<UserSpendingDto, UserSpendingEntity>chunk(3, transactionManager)
            .allowStartIfComplete(true)
            .reader(userSpendingDtoItemReader)
            .processor(userSpendingDtoItemProcessor)
            .writer(userSpendingEntityItemWriter)
            .build();
    }

    @Bean
    public ItemReader<UserSpendingDto> userSpendingDtoItemReader(
        @Value("${batch.user-spending.file-path:/data/spending/user-spending.csv}") String spendingFilePath) {

        return new FlatFileItemReaderBuilder<UserSpendingDto>()
            .name(BatchConstants.USER_SPENDING_DTO_ITEM_READER_NAME)
            .resource(new ClassPathResource(spendingFilePath))
            .linesToSkip(1)
            .delimited()
            .names("userId", "spending")
            .targetType(UserSpendingDto.class)
            .build();
    }

}
