package com.epam.komarov.cadabra.shop.batch.spending;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.komarov.cadabra.shop.CadabraShopApplication;
import com.epam.komarov.cadabra.shop.entity.UserSpendingEntity;
import com.epam.komarov.cadabra.shop.repository.UserSpendingRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBatchTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {CadabraShopApplication.class})
@TestPropertySource("classpath:application-test.properties")
class ImportUserSpendingJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;
    @Autowired
    private Job importUserSpendingJob;
    @Autowired
    private UserSpendingRepository userSpendingRepository;

    @BeforeEach
    void setUp() {
        jobLauncherTestUtils.setJob(importUserSpendingJob);
    }

    @AfterEach
    void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
        userSpendingRepository.deleteAll();
    }

    @Test
    void importUserSpendingJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getExitStatus())
            .isEqualTo(ExitStatus.COMPLETED);

        List<UserSpendingEntity> result = userSpendingRepository.findAll();

        assertThat(result)
            .containsExactlyInAnyOrderElementsOf(getExpectedUserSpendingEntities());
    }

    private List<UserSpendingEntity> getExpectedUserSpendingEntities() {
        return List.of(
            new UserSpendingEntity().setUserId(1L).setSpending(10.50),
            new UserSpendingEntity().setUserId(2L).setSpending(50.00),
            new UserSpendingEntity().setUserId(3L).setSpending(202.12),
            new UserSpendingEntity().setUserId(4L).setSpending(34.50),
            new UserSpendingEntity().setUserId(5L).setSpending(0.0),
            new UserSpendingEntity().setUserId(6L).setSpending(4.19),
            new UserSpendingEntity().setUserId(7L).setSpending(20.50),
            new UserSpendingEntity().setUserId(8L).setSpending(17.17),
            new UserSpendingEntity().setUserId(9L).setSpending(142.99),
            new UserSpendingEntity().setUserId(10L).setSpending(12.00)
        );
    }

}