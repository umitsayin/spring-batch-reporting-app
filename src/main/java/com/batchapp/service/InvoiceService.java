package com.batchapp.service;

import com.batchapp.api.request.CreateInvoiceRequest;
import com.batchapp.api.request.UpdateInvoiceRequest;
import com.batchapp.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDto> getAll();
    InvoiceDto getById(int id);
    InvoiceDto add(CreateInvoiceRequest request);
    InvoiceDto updateById(int id, UpdateInvoiceRequest request);
    void deleteById(int id);
}
