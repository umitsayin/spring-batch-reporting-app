package com.batchapp.service.impl;

import com.batchapp.api.request.CreateInvoiceRequest;
import com.batchapp.api.request.UpdateInvoiceRequest;
import com.batchapp.constant.GlobalConstant;
import com.batchapp.dto.InvoiceDto;
import com.batchapp.exception.EntityNotFoundException;
import com.batchapp.model.Invoice;
import com.batchapp.repository.InvoiceRepository;
import com.batchapp.service.CustomerService;
import com.batchapp.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository repository;
    private final CustomerService customerService;

    public InvoiceServiceImpl(InvoiceRepository repository, CustomerService customerService) {
        this.repository = repository;
        this.customerService = customerService;
    }

    @Override
    public List<InvoiceDto> getAll() {
        return repository.findAll().stream().map(InvoiceDto::convert).toList();
    }

    @Override
    public InvoiceDto getById(int id) {
        Invoice invoice = findById(id);

        return InvoiceDto.convert(invoice);
    }

    @Override
    public InvoiceDto add(CreateInvoiceRequest request) {
        Invoice invoice = new Invoice();

        invoice.setCustomer(customerService.getCustomerById(request.getCustomerId()));
        invoice.setName(request.getName());
        invoice.setTotalPrice(request.getTotalPrice());
        invoice.setCurrency(request.getCurrency());

        return InvoiceDto.convert(repository.save(invoice));
    }

    @Override
    public InvoiceDto updateById(int id, UpdateInvoiceRequest request) {
        Invoice invoice = findById(id);

        invoice.setName(request.getName());
        invoice.setTotalPrice(request.getTotalPrice());
        invoice.setCurrency(request.getCurrency());

        return InvoiceDto.convert(repository.save(invoice));
    }

    @Override
    public void deleteById(int id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }

        throw new EntityNotFoundException(GlobalConstant.INVOICE_NOT_FOUND);
    }

    private Invoice findById(int id){
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException(GlobalConstant.INVOICE_NOT_FOUND));
    }
}
