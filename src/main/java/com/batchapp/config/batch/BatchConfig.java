package com.batchapp.config.batch;

import com.batchapp.config.batch.steps.InvoiceProcessor;
import com.batchapp.config.batch.steps.InvoiceReader;
import com.batchapp.config.batch.steps.InvoiceWriter;
import com.batchapp.dto.InvoiceDto;
import com.batchapp.model.Invoice;
import com.batchapp.repository.InvoiceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
    private final InvoiceRepository invoiceRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step invoicesStep() {
        return new StepBuilder("invoices",jobRepository).<Invoice, InvoiceDto>chunk(100,transactionManager)
                .reader(new InvoiceReader(invoiceRepository))
                .processor(new InvoiceProcessor())
                .writer(new InvoiceWriter())
                .build();
    }

    @Bean
    public Job job() {
        return new JobBuilder("invoicesJob",jobRepository)
                .flow(invoicesStep())
                .end().build();
    }
}
