package com.batchapp.service.impl;

import com.batchapp.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobLauncher jobLauncher;
    private final Job job;
    private final JobRepository jobRepository;

    @Override
    public void runBatchJob() {
        Thread thread = new Thread(()->{
            JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
            jobParametersBuilder.addString("JobID", String.valueOf(System.currentTimeMillis()));
            JobExecution lastExecution = null;
            try {
                lastExecution = jobRepository.getLastJobExecution(job.getName(), jobParametersBuilder.toJobParameters());
                if (lastExecution != null) {
                    BatchStatus status = lastExecution.getStatus();
                    if (status == BatchStatus.STARTED || status == BatchStatus.STARTING || status == BatchStatus.STOPPING) {
                        lastExecution.setStatus(BatchStatus.STOPPING);
                        jobRepository.update(lastExecution);
                    }
                }

                JobExecution execution = jobLauncher.run(job, jobParametersBuilder.toJobParameters());
                System.out.println("Job finished with status: " + execution.getStatus());
            } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                e.printStackTrace();
            }
        });

        log.info("batch process working now.");
        thread.start();
    }
}
