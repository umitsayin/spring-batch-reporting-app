package com.batchapp.api;

import com.batchapp.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class JobsController {
    private final JobService jobService;

    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<?> runJob(){
        jobService.runBatchJob();

        return ResponseEntity.ok(true);
    }
}
