package com.epam.komarov.cadabra.shop.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.debug("JOB FINISHED! jobId: {}, jobExecutionId: {}",
                jobExecution.getJobId(), jobExecution.getId());
            // todo add some logic
        }
    }

}
