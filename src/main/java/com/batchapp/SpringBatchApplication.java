package com.batchapp;

import com.batchapp.api.request.CreateCustomerRequest;
import com.batchapp.api.request.CreateInvoiceRequest;
import com.batchapp.service.CustomerService;
import com.batchapp.service.InvoiceService;
import com.batchapp.service.JobService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SpringBatchApplication {
    private final JobService jobService;
    private final CustomerService customerService;
    private final InvoiceService invoiceService;

    public SpringBatchApplication(JobService jobService,
                                  CustomerService customerService,
                                  InvoiceService invoiceService) {
        this.jobService = jobService;
        this.customerService = customerService;
        this.invoiceService = invoiceService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }

    @Scheduled(fixedRate = 300000, initialDelay = 60000)
    public void myTask() {
        jobService.runBatchJob();
    }


    @PostConstruct
    public void setUp(){
        CreateCustomerRequest customerRequest = new CreateCustomerRequest();
        customerRequest.setFirstname("Ümit");
        customerRequest.setLastname("Sayın");
        customerRequest.setEmail("umitsayin1661@gmail.com");
        customerRequest.setPhone("111222333");

        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        invoiceRequest.setCustomerId(1);
        invoiceRequest.setName("Test invoice");
        invoiceRequest.setTotalPrice(1000);
        invoiceRequest.setCurrency("TL");

        customerService.add(customerRequest);
        invoiceService.add(invoiceRequest);
    }
}
