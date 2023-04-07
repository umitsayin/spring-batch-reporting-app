package com.batchapp;

import com.batchapp.service.JobService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SpringBatchApplication {
    private final JobService jobService;

    public SpringBatchApplication(JobService jobService) {
        this.jobService = jobService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }

    @Scheduled(fixedRate = 300000, initialDelay = 60000)
    public void myTask() {
        jobService.runBatchJob();
    }
}
