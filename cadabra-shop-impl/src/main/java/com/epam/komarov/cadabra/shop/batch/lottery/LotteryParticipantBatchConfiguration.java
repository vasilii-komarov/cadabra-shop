package com.epam.komarov.cadabra.shop.batch.lottery;

import com.epam.komarov.cadabra.shop.batch.BatchConstants;
import com.epam.komarov.cadabra.shop.batch.JobCompletionNotificationListener;
import com.epam.komarov.cadabra.shop.entity.LotteryParticipantEntity;
import com.epam.komarov.cadabra.shop.dto.LotteryParticipantDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class LotteryParticipantBatchConfiguration {

    @Bean
    public Job selectLotteryParticipantsJob(JobRepository jobRepository, Step selectLotteryParticipantsStep,
                                            JobCompletionNotificationListener listener) {
        return new JobBuilder(BatchConstants.SELECT_LOTTERY_PARTICIPANTS_JOB_NAME, jobRepository)
            .listener(listener)
            .start(selectLotteryParticipantsStep)
            .build();
    }

    @Bean
    public Step selectLotteryParticipantsStep(
        JobRepository jobRepository, PlatformTransactionManager transactionManager,
        ItemReader<LotteryParticipantDto> lotteryParticipantEntityItemReader,
        ItemProcessor<LotteryParticipantDto, LotteryParticipantEntity> lotteryParticipantEntityItemProcessor,
        ItemWriter<LotteryParticipantEntity> lotteryParticipantEntityItemWriter) {

        return new StepBuilder(BatchConstants.SELECT_LOTTERY_PARTICIPANTS_STEP_NAME, jobRepository)
            .<LotteryParticipantDto, LotteryParticipantEntity>chunk(2, transactionManager)
            .allowStartIfComplete(true)
            .reader(lotteryParticipantEntityItemReader)
            .processor(lotteryParticipantEntityItemProcessor)
            .writer(lotteryParticipantEntityItemWriter)
            .build();
    }

}
