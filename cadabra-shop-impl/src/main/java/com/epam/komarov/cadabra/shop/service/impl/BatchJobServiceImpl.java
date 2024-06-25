package com.epam.komarov.cadabra.shop.service.impl;

import com.epam.komarov.cadabra.shop.service.BatchJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchJobServiceImpl implements BatchJobService {

    private final JobLauncher asyncJobLauncher;
    private final Job importUsersJob;
    private final Job importUserSpendingJob;
    private final Job selectLotteryParticipantsJob;

    @Override
    public String startUsersImport()  {
        return startJob(importUsersJob, new JobParameters());
    }

    @Override
    public String startUserSpendingImport() {
        return startJob(importUserSpendingJob, new JobParameters());
    }

    @Override
    public String startLotteryParticipantsSelection() {
        return startJob(selectLotteryParticipantsJob, new JobParameters());
    }

    private String startJob(Job job, JobParameters jobParameters) {
        log.debug("Starting the '{}' job.", job.getName());
        try {
            asyncJobLauncher.run(job, jobParameters);
            return "The '%s' job has been started.".formatted(job.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Error during the '%s' job launch.".formatted(job.getName());
        }
    }

}
