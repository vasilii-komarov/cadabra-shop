package com.epam.komarov.cadabra.shop.batch.lottery;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.komarov.cadabra.shop.CadabraShopApplication;
import com.epam.komarov.cadabra.shop.entity.LotteryParticipantEntity;
import com.epam.komarov.cadabra.shop.entity.UserSpendingEntity;
import com.epam.komarov.cadabra.shop.repository.LotteryParticipantRepository;
import com.epam.komarov.cadabra.shop.repository.UserSpendingRepository;
import java.util.List;
import org.assertj.core.api.HamcrestCondition;
import org.hamcrest.core.IsNull;
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
class SelectLotteryParticipantsJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;
    @Autowired
    private Job selectLotteryParticipantsJob;
    @Autowired
    private UserSpendingRepository userSpendingRepository;
    @Autowired
    private LotteryParticipantRepository lotteryParticipantRepository;

    @BeforeEach
    void setUp() {
        jobLauncherTestUtils.setJob(selectLotteryParticipantsJob);
        userSpendingRepository.saveAll(getUserSpendingEntities());
    }

    @AfterEach
    void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
        userSpendingRepository.deleteAll();
        lotteryParticipantRepository.deleteAll();
    }

    @Test
    void selectLotteryParticipantsJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getExitStatus())
            .isEqualTo(ExitStatus.COMPLETED);

        List<LotteryParticipantEntity> result = lotteryParticipantRepository.findAll();

        assertThat(result)
            .hasSize(4)
            .extracting(LotteryParticipantEntity::getUserId)
            .containsExactlyInAnyOrder(2L, 4L, 5L, 9L);

        assertThat(result)
            .extracting(LotteryParticipantEntity::getGiftCardId)
            .areExactly(1, new HamcrestCondition<>(IsNull.notNullValue()));
    }

    private List<UserSpendingEntity> getUserSpendingEntities() {
        return List.of(
            new UserSpendingEntity().setUserId(1L).setSpending(12.00),
            new UserSpendingEntity().setUserId(2L).setSpending(20.01),
            new UserSpendingEntity().setUserId(3L).setSpending(1.00),
            new UserSpendingEntity().setUserId(4L).setSpending(100.00),
            new UserSpendingEntity().setUserId(5L).setSpending(100.00),
            new UserSpendingEntity().setUserId(6L).setSpending(0.00),
            new UserSpendingEntity().setUserId(7L).setSpending(20.00),
            new UserSpendingEntity().setUserId(8L).setSpending(null),
            new UserSpendingEntity().setUserId(9L).setSpending(32.00),
            new UserSpendingEntity().setUserId(10L).setSpending(10.00)
        );
    }

}